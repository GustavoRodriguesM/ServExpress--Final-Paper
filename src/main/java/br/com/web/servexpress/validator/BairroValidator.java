package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Bairro;

public class BairroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Bairro.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
	}

}
