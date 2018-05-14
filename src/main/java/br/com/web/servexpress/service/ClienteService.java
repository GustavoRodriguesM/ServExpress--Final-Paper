package br.com.web.servexpress.service;

import java.util.List;

import br.com.web.servexpress.helper.TopClienteAdminDashboard;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Feedback;
import br.com.web.servexpress.model.Usuario;

public interface ClienteService extends AbstractService<Cliente>{
	Cliente busca(Usuario user);
	void registraFeedback(Feedback feedback);
	List<TopClienteAdminDashboard> top5PorNumeroPedidos();
}
