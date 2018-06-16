package br.com.web.servexpress.service;

import java.util.List;

import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Usuario;

public interface EntregadorService extends AbstractService<Entregador> {
	Long conta();

	Entregador busca(Usuario user);

	List<Entregador> listaComDeletados();

	void desativa(Entregador entregador);

	void ativa(Entregador entregador);
}
