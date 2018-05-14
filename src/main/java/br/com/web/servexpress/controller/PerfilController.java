package br.com.web.servexpress.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.servexpress.model.Bairro;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.service.BairroService;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.EnderecoService;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.UsuarioService;
import br.com.web.servexpress.validator.RegisterClienteValidator;
import br.com.web.servexpress.validator.RegisterEntregadorValidator;

@Controller
public class PerfilController extends AbstractController {

	private static final String PAGINA_ENTREGADOR_PERFIL = "/entregador/perfil";
	private static final String PAGINA_CLIENTE_PERFIL = "/cliente/perfil";

	@Autowired
	private EntregadorService entregadorService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@InitBinder(value = { "entregador" })
	protected void initEntregadorBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterEntregadorValidator());
	}

	@InitBinder(value = { "cliente" })
	protected void initProfileBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterClienteValidator());
	}
	
	
	/*
	 * 
	 * ENTREGADOR
	 * 
	 */
	
	@GetMapping("entregador/perfil")
	public ModelAndView perfilEntregador() {
		Entregador entregador = this.entregadorService.busca(getUser());
		return this.modelAndView(PAGINA_ENTREGADOR_PERFIL).addObject("entregador", entregador);
	}

	@PostMapping("entregador/perfil")
	public ModelAndView perfilEntregadorPOST(@Valid Entregador entregador, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return this.perfilEntregador();
		
		String mensagem = null;

		try {
			this.entregadorService.persiste(entregador);
			mensagem = this.successMessage();
		}catch(Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/entregador/perfil");
	}

	/*
	 * 
	 * CLIENTE
	 * 
	 */
	
	@GetMapping("cliente/perfil")
	public ModelAndView perfilCliente() {
		Cliente cliente = this.clienteService.busca(getUser());
		List<Bairro> bairros = this.bairroService.lista();
		return this.modelAndView(PAGINA_CLIENTE_PERFIL).addObject("cliente", cliente).addObject("bairros", bairros);
	}

	@PostMapping("cliente/perfil")
	public ModelAndView perfilClientePOST(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return this.perfilCliente();

		String mensagem = null;
		
		try {
			userService.persiste(cliente.getUsuario(), ROLE_PREFIX + "CLIENTE".toUpperCase());
			enderecoService.persiste(cliente.getEndereco());
			clienteService.persiste(cliente);
			mensagem = this.successMessage();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/cliente/perfil");
	}

}
