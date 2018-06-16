package br.com.web.servexpress.alertmessage;


import org.springframework.stereotype.Component;

@Component
public class SweetAlertMessage implements RapidMessage {

	public String showMessage(String message, TypeMessage typeMessage) throws RapidMessageException {
		switch (typeMessage) {
		case DANGER:
			return String.format("swal({title: 'Ops!', text: '%s', icon: 'error', button: 'Sair!',})", message);
		case SUCCESS:
			return String.format("swal({title: 'Feito!', text: '%s', icon: 'success', button: 'Sair!',})", message);
		default:
			throw new RapidMessageException("Type of the message can't be null or this type.");
		}
	}

}
