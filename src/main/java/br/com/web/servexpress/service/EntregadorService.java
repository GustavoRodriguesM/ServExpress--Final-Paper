package br.com.web.servexpress.service;

import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Usuario;

public interface EntregadorService extends AbstractService<Entregador> {
	Long conta();

	Entregador busca(Usuario user);
}
