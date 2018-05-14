package br.com.web.servexpress.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Feedback;
import br.com.web.servexpress.service.ClienteService;

@Controller
public class ClienteController extends AbstractController {

	private static final String PAGINA_CLIENTES = "cliente/admin/lista";
	private static final String PAGINA_DETALHE = "cliente/admin/detalhe";

	@Autowired
	private ClienteService clienteService;

	@PostMapping("cliente/feedback")
	public ModelAndView register(@Valid Feedback feedback, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return this.redirect("/cliente/dashboard");

		String mensagem = null;

		try {
			this.clienteService.registraFeedback(feedback);
			mensagem = this
					.successMessage("Obrigado pelo feedback! Isso ajuda a melhorar cada dia mais nossos serviços");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = this.errorMessage("Falha ao registrar feedback. :(");
		}

		attributes.addFlashAttribute(CAMPO_SWEETMESSAGE, mensagem);
		return this.redirect("/cliente/dashboard");
	}

	@GetMapping(MAPPING_ADMIN + "cliente")
	public ModelAndView clientes() {
		ModelAndView view = this.modelAndView(PAGINA_CLIENTES);
		List<Cliente> clientes = this.clienteService.lista();
		view.addObject("clientes", clientes);
		return view;
	}

	@GetMapping(MAPPING_ADMIN + "cliente/{id}")
	public ModelAndView cliente(@PathVariable Long id) {
		ModelAndView view = this.modelAndView(PAGINA_DETALHE);
		Cliente cliente = this.clienteService.busca(id);

		view.addObject("cliente", cliente);
		return view;

	}

}
