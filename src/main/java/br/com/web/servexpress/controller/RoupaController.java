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

import br.com.web.servexpress.model.Roupa;
import br.com.web.servexpress.service.RoupaService;
import br.com.web.servexpress.validator.RegisterRoupaValidator;

@Controller
public class RoupaController extends AbstractController {

	private static final String PAGINA_ROUPA = "/admin/roupa/allAndForm";

	@Autowired
	private RoupaService roupaService;

	@InitBinder(value = { "roupa" })
	protected void initRoupaBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterRoupaValidator());
	}

	@GetMapping(MAPPING_ADMIN + "roupa")
	public ModelAndView roupa(Roupa roupa) {
		List<Roupa> roupas = roupaService.listaComDeletados();
		return this.modelAndView(PAGINA_ROUPA).addObject("roupas", roupas);
	}

	@PostMapping(MAPPING_ADMIN + "roupa")
	public ModelAndView roupaPersistUpdate(@Valid Roupa roupa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors())
			return this.roupa(roupa);
		String mensagem = null;

		try {
			this.roupaService.persiste(roupa);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/roupa");
	}

	@GetMapping(MAPPING_ADMIN + "roupa/{id}/editar")
	public ModelAndView roupaUpdateForm(@PathVariable Long id) {
		List<Roupa> roupas = roupaService.listaComDeletados();
		Roupa roupa = roupaService.busca(id);
		return this.modelAndView(PAGINA_ROUPA).addObject("roupas", roupas).addObject("roupa", roupa);
	}

	@PostMapping(MAPPING_ADMIN + "roupa/{id}/delete")
	public ModelAndView roupaDelete(@PathVariable Long id, RedirectAttributes attributes) {
		Roupa roupa = roupaService.busca(id);
		String mensagem = null;

		try {
			roupaService.desativa(roupa);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/roupa");
	}

	@PostMapping(MAPPING_ADMIN + "roupa/{id}/reactivate")
	public ModelAndView roupaReactivate(@PathVariable Long id, RedirectAttributes attributes) {
		Roupa roupa = roupaService.busca(id);
		String mensagem = null;

		try {
			roupaService.ativa(roupa);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/roupa");
	}

}
