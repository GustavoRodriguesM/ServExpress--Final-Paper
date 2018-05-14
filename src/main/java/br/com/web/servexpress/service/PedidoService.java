package br.com.web.servexpress.service;

import java.util.Calendar;
import java.util.List;

import br.com.web.servexpress.enums.Pagamento;
import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.PedidoRoupa;
import br.com.web.servexpress.wrapper.PedidoRoupaWrapper;

public interface PedidoService extends AbstractService<Pedido> {
	void persiste(PedidoRoupaWrapper pedidoRoupaWrapper);

	PedidoRoupa buscaUltimo(Cliente cliente);

	List<Pedido> listaDataEmissaoDesc(Cliente cliente);

	Pedido busca(Long id, Cliente cliente);

	List<Pedido> lista(StatusPedido statusPedido);

	void sendABiker(Pedido pedido, Entregador entregador, StatusPedido statusPedido);

	void delivering(Pedido pedido) throws Exception;

	Double contaPorDataEntre(Calendar c1, Calendar c2);

	Long conta(StatusPedido status);

	Double somaTotal();

	Double vendasPorMesDinheiro(Pagamento pagamento);

	Long conta(Pagamento pagamento);

	void mudaStatus(Pedido pedido);

	void pagamento(Pedido pedido);

	List<Pedido> buscaTop5();

	List<PedidoRoupa> buscaRoupas(Pedido pedido);

	Long conta(Cliente cliente);
}
