package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Roupa;

public class RegisterRoupaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Roupa.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorUnitario", "field.required");
	}

}
