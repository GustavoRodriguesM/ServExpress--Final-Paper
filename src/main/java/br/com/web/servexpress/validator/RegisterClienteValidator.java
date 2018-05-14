package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Cliente;

public class RegisterClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.nome", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.senha", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.confirmarSenha", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cep", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.rua", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.numero", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairroEntity", "field.required");

		Cliente cliente = (Cliente) target;

		if (cliente.getUsuario().getSenha().length() > 6) {
			if (!cliente.getUsuario().getSenha().equals(cliente.getUsuario().getConfirmarSenha())) {
				errors.rejectValue("usuario.confirmarSenha", "confirmPassword");
			}
		} else
			errors.rejectValue("usuario.senha", "minLength");
	}

}
