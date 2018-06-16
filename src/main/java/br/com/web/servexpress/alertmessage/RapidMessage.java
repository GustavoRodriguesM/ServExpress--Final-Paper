package br.com.web.servexpress.alertmessage;

public interface RapidMessage {

	String showMessage(String message, TypeMessage typeMessage) throws RapidMessageException;

}
