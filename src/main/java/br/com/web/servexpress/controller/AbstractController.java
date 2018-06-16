package br.com.web.servexpress.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.web.servexpress.alertmessage.RapidMessageException;
import br.com.web.servexpress.alertmessage.SweetAlertMessage;
import br.com.web.servexpress.alertmessage.TypeMessage;
import br.com.web.servexpress.model.Usuario;

public abstract class AbstractController {

	protected static final String MENSAGEM_SUCESSO = "Ação realizada com sucesso!";
	protected static final String MENSAGEM_ERRO = "Erro ao executar ação!";
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);
	protected static final String CAMPO_SWEETMESSAGE = "sweetAlert";

	protected static final String MAPPING_ADMIN = "/admin/";
	protected static final String MAPPING_ENTREGADOR = "/entregador/";
	protected static final String MAPPING_CLIENTE = "/cliente/";

	static final String ROLE_PREFIX = "ROLE_";

	private final SweetAlertMessage sweetAlertMessage = new SweetAlertMessage();

	protected String successMessage() {
		try {
			return this.sweetAlertMessage.showMessage(MENSAGEM_SUCESSO, TypeMessage.SUCCESS);
		} catch (RapidMessageException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String errorMessage() {
		try {
			return this.sweetAlertMessage.showMessage(MENSAGEM_ERRO, TypeMessage.DANGER);
		} catch (RapidMessageException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String successMessage(String mensagem) {
		try {
			return this.sweetAlertMessage.showMessage(mensagem, TypeMessage.SUCCESS);
		} catch (RapidMessageException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String errorMessage(String mensagem) {
		try {
			return this.sweetAlertMessage.showMessage(mensagem, TypeMessage.DANGER);
		} catch (RapidMessageException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected ModelAndView modelAndView(String view) {

		final ModelAndView modelAndView = new ModelAndView(view);

		return modelAndView;
	}

	protected ModelAndView redirect(String url) {

		ModelAndView modelAndView = new ModelAndView(new RedirectView(url));

		return modelAndView;
	}

	protected Usuario getUser() {
		LOGGER.debug("obtendo usuario autenticado");

		final Authentication authentication = this.getAuthentication();

		if (authentication != null) {
			final Object principal = authentication.getPrincipal();

			LOGGER.debug(String.format("objeto user principal obtido [%s]", principal));

			if (principal instanceof Usuario) {
				LOGGER.debug(String.format("usuario autenticado [%s]", authentication.getName()));

				return ((Usuario) principal).getUser();
			}

			LOGGER.debug(String.format("objeto de autenticacao [principal] nao e do tipo esperado", principal,
					principal.getClass().getCanonicalName()));

			return null;
		}

		LOGGER.debug("objeto de autenticacao esta nulo. nao sera possivel obter usuario logado.");

		return null;
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	protected boolean hasRole(String role) {
		for (final GrantedAuthority g : this.getAuthentication().getAuthorities()) {
			if (g.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
}