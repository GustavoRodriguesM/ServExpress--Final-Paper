package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Entregador;

public class RegisterEntregadorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Entregador.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.nome", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.senha", "field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnh", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cep", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.rua", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.numero", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "field.required");

		Entregador entregador = (Entregador) target;

		if (entregador.getUsuario().getSenha().length() > 6) {
			if (!entregador.getUsuario().getSenha().equals(entregador.getUsuario().getConfirmarSenha())) {
				errors.rejectValue("usuario.confirmarSenha", "confirmPassword");
			}
		} else
			errors.rejectValue("usuario.senha", "minLength");
	}

}
