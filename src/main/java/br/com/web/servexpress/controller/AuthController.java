package br.com.web.servexpress.controller;

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

import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.service.BairroService;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.EnderecoService;
import br.com.web.servexpress.service.UsuarioService;
import br.com.web.servexpress.util.EnviaEmail;
import br.com.web.servexpress.validator.RegisterClienteValidator;
import br.com.web.servexpress.validator.ResetPasswordValidator;

@Controller
public class AuthController extends AbstractController {

	private static final String PAGE_EMAIL = "/auth/resetpassword/sendemail";
	private static final String RESET_PAGE = "/auth/resetpassword/reset";
	private static final String PAGINA_LOGIN = "/auth/login";
	private static final String PAGINA_REGISTRO = "/auth/register";

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private BairroService bairroService;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnviaEmail sendEmail;

	@InitBinder("user")
	protected void initResetPasswordBinder(WebDataBinder binder) {
		binder.addValidators(new ResetPasswordValidator());
	}

	@InitBinder(value = "cliente")
	protected void initClienteBinder(WebDataBinder binder) {
		binder.addValidators(new RegisterClienteValidator());
	}

	@GetMapping("/login")
	public ModelAndView login() {
		return this.modelAndView(PAGINA_LOGIN);
	}

	@GetMapping("/registrar")
	public ModelAndView formRegister(Cliente cliente) {
		return this.modelAndView(PAGINA_REGISTRO).addObject("bairros", bairroService.lista());
	}

	@PostMapping("/registrar")
	public ModelAndView register(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return formRegister(cliente);

		if (userService.countByEmail(cliente.getUsuario().getEmail()) > 0) {
			cliente.getUsuario().setEmail("");
			return this.formRegister(cliente).addObject("message", "Email já cadastrado em nossa base de dados!");
		}

		String mensagem = null;
		try {
			userService.persiste(cliente.getUsuario(), ROLE_PREFIX + "CLIENTE".toUpperCase());
			enderecoService.persiste(cliente.getEndereco());
			clienteService.persiste(cliente);
			mensagem = this.successMessage("Cadastro realizado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);

		return redirect("/login");
	}

	@GetMapping("/reset")
	public ModelAndView emailPage(Usuario user) {
		return this.modelAndView(PAGE_EMAIL);
	}

	@PostMapping("/reset")
	public ModelAndView sendEmail(String email, RedirectAttributes attributes) {
		String mensagem = null;

		try {
			Usuario user = userService.findByEmail(email);
			sendEmail.resetPassword(user);
			mensagem = this.successMessage(
					"Uma mensagem foi enviada para seu email com as informações de como recuperar sua senha, caso o mesmo seja encontrado em nossa base de dados.");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage("Erro ao tentar enviar email.");
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/login");
	}

	@GetMapping("/reset/token/{token}")
	public ModelAndView resetPasswordPage(@PathVariable String token, Usuario user) {

		return this.modelAndView(RESET_PAGE).addObject("token", token);
	}

	@PostMapping("/reset/password")
	public ModelAndView resetPassword(@Valid Usuario user, BindingResult result, String token,
			RedirectAttributes attributes) {
		if (result.hasErrors())
			return resetPasswordPage(token, user);
		String mensagem = null;

		try {
			this.userService.alteraSenha(user.getSenha(), token);
			mensagem = this.successMessage("Senha alterada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage();
		}
		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/login");
	}
}
