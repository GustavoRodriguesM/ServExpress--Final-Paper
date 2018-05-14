package br.com.web.servexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.servexpress.util.EnviaEmail;

@Controller
public class IndexController extends AbstractController {

	private static final String PAGINA_INDEX = "home";

	@Autowired
	private EnviaEmail enviaEmail;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(PAGINA_INDEX);
		return view;
	}

	@PostMapping("/contato")
	public ModelAndView contato(String nome, String email, String mensagem, RedirectAttributes attributes) {
		String msg = null;

		try {
			this.enviaEmail.contato(nome, email, mensagem);
			msg = this.successMessage("Mensagem enviada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			msg = this.errorMessage("Erro ao enviar mensagem");
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, msg);
		return this.redirect("/");
	}

	@GetMapping("/redirect")
	public ModelAndView redirect() {
		if (this.hasRole("ROLE_DISABLE"))
			return this.redirect("/logout");
		else if (this.hasRole("ROLE_CLIENTE"))
			return this.redirect("/cliente/dashboard");
		else if (this.hasRole("ROLE_ADMINISTRADOR"))
			return this.redirect("/admin/dashboard");
		else if (this.hasRole("ROLE_ENTREGADOR"))
			return this.redirect("/entregador/dashboard");
		return null;
	}

}
