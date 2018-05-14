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

import br.com.web.servexpress.enums.StatusEntregador;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.service.EnderecoService;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.UsuarioService;
import br.com.web.servexpress.validator.RegisterEntregadorValidator;

@Controller
public class EntregadorController extends AbstractController {
	
	private static final String PAGINA_ENTREGADOR = "/entregador/admin/lista";
	private static final String PAGINA_ENTREGADOR_FORM = "/entregador/admin/form";
	private static final String PAGINA_ENTREGADOR_DETAIL = "/entregador/admin/detalhe";

	@Autowired
	private EntregadorService entregadorService;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private EnderecoService enderecoService;

	@InitBinder(value = { "entregador" })
	protected void initEntregadorBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterEntregadorValidator());
	}

	@GetMapping(MAPPING_ADMIN + "entregador")
	public ModelAndView entregador() {
		List<Entregador> entregadores = this.entregadorService.lista();
		return this.modelAndView(PAGINA_ENTREGADOR).addObject("entregadores", entregadores);
	}

	@PostMapping(MAPPING_ADMIN + "entregador")
	public ModelAndView entregadorPersistUpdate(@Valid Entregador entregador, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			if (entregador.getId() == null)
				return this.entregadorForm(entregador);
			else
				return this.entregadorUpdateForm(entregador.getId());

		}
		String mensagem = null;

		try {
			this.userService.persiste(entregador.getUsuario(), "ROLE_ENTREGADOR");
			this.enderecoService.persiste(entregador.getEndereco());
			this.entregadorService.persiste(entregador);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/admin/entregador");
	}

	@GetMapping(MAPPING_ADMIN + "entregador/add")
	public ModelAndView entregadorForm(Entregador entregador) {
		return this.modelAndView(PAGINA_ENTREGADOR_FORM).addObject("statusEntregadorLista", StatusEntregador.values());
	}

	@GetMapping(MAPPING_ADMIN + "entregador/{id}/editar")
	public ModelAndView entregadorUpdateForm(@PathVariable Long id) {
		Entregador entregador = this.entregadorService.busca(id);
		return this.modelAndView(PAGINA_ENTREGADOR_FORM).addObject("statusEntregadorLista", StatusEntregador.values())
				.addObject("entregador", entregador);
	}

	@GetMapping(MAPPING_ADMIN + "entregador/{id}")
	public ModelAndView entregadorDetail(@PathVariable Long id) {
		Entregador entregador = this.entregadorService.busca(id);
		return this.modelAndView(PAGINA_ENTREGADOR_DETAIL).addObject("entregador", entregador);
	}

}
