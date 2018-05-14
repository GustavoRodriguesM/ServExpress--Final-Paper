package br.com.web.servexpress.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.servexpress.enums.Classificacao;
import br.com.web.servexpress.model.Gasto;
import br.com.web.servexpress.service.GastoService;
import br.com.web.servexpress.validator.RegisterGastoValidator;

@Controller
public class GastoController extends AbstractController {

	private static final String PAGINA_GASTO = "/admin/gasto/allAndForm";
	
	@Autowired
	private GastoService gastoService;

	@InitBinder(value = { "gasto" })
	protected void initGastoBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterGastoValidator());
	}

	@GetMapping(MAPPING_ADMIN + "gasto")
	public ModelAndView gasto(Gasto gasto) {
		List<Gasto> gastos = gastoService.listaComDeletados();
		return this.modelAndView(PAGINA_GASTO).addObject("gastos", gastos).addObject("classificacoes",
				Classificacao.values());
	}

	@PostMapping(MAPPING_ADMIN + "gasto")
	public ModelAndView gastoPersistUpdate(@Valid Gasto gasto, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors())
			return this.gasto(gasto);

		String mensagem = null;

		try {
			this.gastoService.persiste(gasto);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/gasto");
	}

	@GetMapping(MAPPING_ADMIN + "gasto/{id}/editar")
	public ModelAndView gastoUpdateForm(@PathVariable Long id) {
		List<Gasto> gastos = gastoService.listaComDeletados();
		Gasto gasto = gastoService.busca(id);
		return this.modelAndView(PAGINA_GASTO).addObject("gastos", gastos).addObject("gasto", gasto)
				.addObject("classificacoes", Classificacao.values());
	}

	@PostMapping(MAPPING_ADMIN + "gasto/{id}/delete")
	public ModelAndView gastoSoftDelete(@PathVariable Long id, RedirectAttributes attributes) {
		Gasto gasto = gastoService.busca(id);

		String mensagem = null;

		try {
			gastoService.desativa(gasto);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/gasto");
	}

	@PostMapping(MAPPING_ADMIN + "gasto/{id}/reactivate")
	public ModelAndView gastoReactivate(@PathVariable Long id, RedirectAttributes attributes) {

		String mensagem = null;

		try {
			Gasto gasto = gastoService.busca(id);
			gastoService.ativa(gasto);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/gasto");
	}

}
