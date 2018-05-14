package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Gasto;

public class RegisterGastoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Gasto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recurso", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valor", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "classificacao", "field.required");
	}

}
