package br.com.web.servexpress.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.web.servexpress.model.Usuario;

public class ResetPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.senha", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.confirmarSenha", "field.required");
		
		Usuario user = (Usuario) target;
		
		if(user.getSenha().length() > 6){
			if(!user.getSenha().equals(user.getConfirmarSenha())){
				errors.rejectValue("confirmarSenha", "confirmPassword");
			}
		}else
			errors.rejectValue("senha", "minLength");
	}

}
