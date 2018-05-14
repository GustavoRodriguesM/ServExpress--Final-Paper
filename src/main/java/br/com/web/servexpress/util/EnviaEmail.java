package br.com.web.servexpress.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.Usuario;

@Component
public class EnviaEmail {

	private String linkEmail = "lavanderia.servexpress@gmail.com";
	private String linkSenha = "servexpress123";

	String base = "http://localhost:9000/";

	public void resetPassword(Usuario user) throws EmailException {
		HtmlEmail htmlEmail = new HtmlEmail();
		htmlEmail.setSSLOnConnect(true);
		htmlEmail.setHostName("smtp.gmail.com");
		htmlEmail.setSslSmtpPort("465");
		htmlEmail.setAuthentication(linkEmail, linkSenha);
		htmlEmail.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
		htmlEmail.setFrom(linkEmail, "ServExpress");
		htmlEmail.setDebug(true);
		htmlEmail.setSubject("Redefinição de senha");
		StringBuilder builder = new StringBuilder();
		builder.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta name='viewport' content='width=device-width'/><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/> <title>Resetar senha</title></head><body style='margin:0px; background: #f8f8f8; '><div width='100%' style='background: #f8f8f8; padding: 0px 0px; font-family:arial; line-height:28px; height:100%; width: 100%; color: #514d6a;'> <div style='max-width: 700px; padding:50px 0; margin: 0px auto; font-size: 14px'> <table border='0' cellpadding='0' cellspacing='0' style='width: 100%; margin-bottom: 20px'> <tbody> <tr> <td style='vertical-align: top; padding-bottom:30px;' align='center'><a><img src='https://i.imgur.com/6TIETxI.png' alt='Logo ServExpress' style='border:none'><br/> <img src='https://i.imgur.com/2DdcLrD.png' alt='Logo ServExpress' style='border:none'></a> </td></tr></tbody> </table> <div style='padding: 40px; background: #fff;'> <table border='0' cellpadding='0' cellspacing='0' style='width: 100%;'> <tbody> <tr> <td style='border-bottom:1px solid #f6f6f6;'><h1 style='font-size:14px; font-family:arial; margin:0px; font-weight:bold;'"
						+ ">Olá " + user.getNome() + ","
						+ "</h1> <p style='margin-top:0px; color:#bbbbbb;'>Aqui estão suas instruções para restaurar sua senha.</p></td></tr><tr> <td style='padding:10px 0 30px 0;'><p>Uma chamada foi recebida pelo sistema para alterar a senha vinculada a este email. Se você não fez esta requisição, apenas ignore esta mensagem. Se você teve problemas para entrar em sua conta, por favor altere sua senha:</p></tr></tbody> </table> <center> "
						+ "<a href='" + base + "reset/token/" + user.getToken() + "' "
						+ "style='display: inline-block; padding: 11px 30px; margin: 20px 0px 30px; font-size: 15px; color: #fff; background: #20aee3; border-radius: 60px; text-decoration:none;'>Resetar senha</a> </center> <table> <tbody> <tr> <td> <b>- Obrigado pelo contato.<br/>Equipe ServExpress</b> </td></tr><tr> <td style='border-top:1px solid #f6f6f6; padding-top:20px; color:#777'>Se você está com problemas em clicar no botão/resetar sua senha, por favor, responda este email para que soluções sejam tomadas.</td></tr></tbody> </table> </div></div></div></body></html>");
		htmlEmail.setHtmlMsg(builder.toString());
		htmlEmail.addTo(user.getEmail());
		htmlEmail.send();

	}

	public void contato(String nome, String email, String mensagem) throws EmailException {

		HtmlEmail htmlEmail = new HtmlEmail();
		htmlEmail.setSSLOnConnect(true);
		htmlEmail.setHostName("smtp.gmail.com");
		htmlEmail.setSslSmtpPort("465");
		htmlEmail.setAuthentication(linkEmail, linkSenha);
		htmlEmail.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
		htmlEmail.setFrom(linkEmail, "ServExpress");
		htmlEmail.setDebug(true);
		htmlEmail.setSubject("Contato ServExpress " + nome);
		StringBuilder builder = new StringBuilder();
		builder.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta name='viewport' content='width=device-width' /><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><title>Resetar senha</title></head><body style='margin:0px; background: #f8f8f8; '><div width='100%' style='background: #f8f8f8; padding: 0px 0px; font-family:arial; line-height:28px; height:100%;  width: 100%; color: #514d6a;'><div style='max-width: 700px; padding:50px 0;  margin: 0px auto; font-size: 14px'><table border='0' cellpadding='0' cellspacing='0' style='width: 100%; margin-bottom: 20px'><tbody><tr><td style='vertical-align: top; padding-bottom:30px;' align='center'><a><img src='https://i.imgur.com/6TIETxI.png' alt='Logo ServExpress' style='border:none'><br/><img src='https://i.imgur.com/2DdcLrD.png' alt='Logo ServExpress' style='border:none'></a></td></tr></tbody></table><div style='padding: 40px; background: #fff;'><table border='0' cellpadding='0' cellspacing='0' style='width: 100%;'><tbody><tr><td style='border-bottom:1px solid #f6f6f6;'><h1 style='font-size:14px; font-family:arial; margin:0px; font-weight:bold;'>Olá,</h1><p style='margin-top:0px; color:#bbbbbb;'>Alguém lhe enviou esse email pelo sistema de contato.</p></td></tr><tr><td style='padding:10px 0 30px 0;'>"
						+ "<p>Nome: " + nome + "</p>" + "<p>Email: " + email + "</p> " + "<p>Mensagem: " + mensagem
						+ "</p></tr></tbody></table></div></div></div></body></html>");
		htmlEmail.setHtmlMsg(builder.toString());
		htmlEmail.addTo(linkEmail);
		htmlEmail.send();

	}

	public void statusAlterado(Pedido pedido) throws EmailException {
		String status = null;

		if (pedido.getStatusPedido() == StatusPedido.ENTREGADOR_ENVIADO_A_RESIDENCIA)
			status = "ENTREGADOR ENVIADO";
		else if (pedido.getStatusPedido() == StatusPedido.ENTREGANDO)
			status = "ENTREGANDO";

		HtmlEmail htmlEmail = new HtmlEmail();
		htmlEmail.setSSLOnConnect(true);
		htmlEmail.setHostName("smtp.gmail.com");
		htmlEmail.setSslSmtpPort("465");
		htmlEmail.setAuthentication(linkEmail, linkSenha);
		htmlEmail.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);

		htmlEmail.setFrom(linkEmail, "ServExpress");
		htmlEmail.setDebug(true);
		htmlEmail.setSubject(status + " Pedido: #" + pedido.getId());
		StringBuilder builder = new StringBuilder();
		builder.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta name='viewport' content='width=device-width'/><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><title>Resetar senha</title></head><body style='margin:0px; background: #f8f8f8; '><div width='100%' style='background: #f8f8f8; padding: 0px 0px; font-family:arial; line-height:28px; height:100%;  width: 100%; color: #514d6a;'><div style='max-width: 700px; padding:50px 0;  margin: 0px auto; font-size: 14px'><table border='0' cellpadding='0' cellspacing='0' style='width: 100%; margin-bottom: 20px'><tbody><tr><td style='vertical-align: top; padding-bottom:30px;' align='center'><a><img src='https://i.imgur.com/6TIETxI.png' alt='Logo ServExpress' style='border:none'><br/><img src='https://i.imgur.com/2DdcLrD.png' alt='Logo ServExpress' style='border:none'></a></td></tr></tbody></table><div style='padding: 40px; background: #fff;'><table border='0' cellpadding='0' cellspacing='0' style='width: 100%;'><tbody><tr><td style='border-bottom:1px solid #f6f6f6;'><h1 style='font-size:14px; font-family:arial; margin:0px; font-weight:bold;'>Olá "
						+ pedido.getCliente().getUsuario().getNome()
						+ ",</h1><p style='margin-top:0px; color:#bbbbbb;'>Seu pedido teve o status alterado.</p></td></tr><tr><td style='padding:10px 0 30px 0;'><p>Seu pedido acaba de ter o status alterado para( "
						+ status
						+ ").É importante que você esteja no local de entrega a partir de agora para evitar problemas com o transporte do seu pedido.</p></tr></tbody></table><table><tbody><tr><td><b>-Agradecemos pela preferência.<br/>Equipe ServExpress</b></td></tr><tr><td style='border-top:1px solid #f6f6f6; padding-top:20px; color:#777'>Se você está com problemas com este email,por favor,responda ele para que soluções sejam tomadas.</td></tr></tbody></table></div></div></div></body></html>");
		htmlEmail.setHtmlMsg(builder.toString());
		htmlEmail.addTo(pedido.getCliente().getUsuario().getEmail());
		htmlEmail.send();

	}
}