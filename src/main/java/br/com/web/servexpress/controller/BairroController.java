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

import br.com.web.servexpress.model.Bairro;
import br.com.web.servexpress.service.BairroService;
import br.com.web.servexpress.validator.BairroValidator;

@Controller
public class BairroController extends AbstractController {

	private static final String PAGINA_BAIRRO = "bairro/listaEFormulario";

	@Autowired
	private BairroService bairroService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new BairroValidator());
	}

	@GetMapping(MAPPING_ADMIN + "bairro")
	public ModelAndView bairro(Bairro bairro) {
		List<Bairro> bairros = bairroService.listaComDeletados();
		return this.modelAndView(PAGINA_BAIRRO).addObject("bairros", bairros);
	}

	@PostMapping(MAPPING_ADMIN + "bairro")
	public ModelAndView bairroPersistUpdate(@Valid Bairro bairro,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors())
			return this.bairro(bairro);

		String mensagem = null;

		try {
			this.bairroService.persiste(bairro);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/bairro");
	}

	@GetMapping(MAPPING_ADMIN + "bairro/{id}/editar")
	public ModelAndView bairroUpdateForm(@PathVariable Long id) {
		List<Bairro> bairros = bairroService.listaComDeletados();
		Bairro bairro = bairroService.busca(id);
		return this.modelAndView(PAGINA_BAIRRO).addObject("bairros", bairros)
				.addObject("bairro", bairro);
	}

	@PostMapping(MAPPING_ADMIN + "bairro/{id}/delete")
	public ModelAndView bairroDelete(@PathVariable Long id,
			RedirectAttributes attributes) {
		Bairro bairro = bairroService.busca(id);
		String mensagem = null;
		try {
			bairroService.desativa(bairro);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/bairro");
	}

	@PostMapping(MAPPING_ADMIN + "bairro/{id}/reactivate")
	public ModelAndView bairroReactivate(@PathVariable Long id,
			RedirectAttributes attributes) {
		Bairro bairro = bairroService.busca(id);
		String mensagem = null;
		try {
			bairroService.ativa(bairro);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/bairro");
	}

}
