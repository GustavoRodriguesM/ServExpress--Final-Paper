package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Cliente;

public class ClienteProfileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		

	}

}
