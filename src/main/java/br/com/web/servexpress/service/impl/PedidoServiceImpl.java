package br.com.web.servexpress.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.enums.Pagamento;
import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.PedidoRoupa;
import br.com.web.servexpress.model.Roupa;
import br.com.web.servexpress.repository.PedidoRepository;
import br.com.web.servexpress.repository.PedidoRoupaRepository;
import br.com.web.servexpress.service.PedidoService;
import br.com.web.servexpress.service.RoupaService;
import br.com.web.servexpress.wrapper.PedidoRoupaWrapper;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private RoupaService roupaService;

	@Autowired
	private PedidoRoupaRepository pedidoRoupaRepository;

	@Override
	public void persiste(PedidoRoupaWrapper pedidoRoupaWrapper) {
		Double total = 0.0;

		List<Roupa> roupas = new ArrayList<>();
		List<Integer> quantidades = new ArrayList<>();

		pedidoRoupaWrapper.getPedidoRoupas().forEach(s -> {
			Roupa roupa = roupaService.busca(s.getRoupa().getId());
			roupas.add(roupa);
			quantidades.add(s.getQuantidade());
		});

		Pedido pedido = pedidoRoupaWrapper.getPedidoRoupas().get(0).getPedido();

		pedido.setPagamento(Pagamento.PENDENTE);
		pedido.setStatusPedido(StatusPedido.PENDENTE);
		pedido.setDataEmissao(Calendar.getInstance());

		pedido = this.pedidoRepository.save(pedido);
		for (int i = 0; i < roupas.size(); i++) {
			pedidoRoupaWrapper.getPedidoRoupas().get(i).setPedido(pedido);
			pedidoRoupaWrapper.getPedidoRoupas().get(i).setRoupa(roupas.get(i));
			pedidoRoupaWrapper.getPedidoRoupas().get(i).setQuantidade(quantidades.get(i));
			total += (roupas.get(i).getValorUnitario() * quantidades.get(i));
			pedidoRoupaRepository.save(pedidoRoupaWrapper.getPedidoRoupas().get(i));
		}

		pedidoRoupaWrapper.getPedidoRoupas().get(0).getPedido().setTotal(total);
		pedidoRepository.save(pedidoRoupaWrapper.getPedidoRoupas().get(0).getPedido());
	}

	@Override
	public PedidoRoupa buscaUltimo(Cliente cliente) {
		List<PedidoRoupa> pedidoRoupas = this.pedidoRoupaRepository.findLastPedido(cliente);
		if (pedidoRoupas.isEmpty())
			return null;
		else
			return pedidoRoupas.get(0);
	}

	@Override
	public List<PedidoRoupa> buscaRoupas(Pedido pedido) {
		return this.pedidoRoupaRepository.findRoupasFromPedido(pedido);
	}

	@Override
	public List<Pedido> listaDataEmissaoDesc(Cliente cliente) {
		return this.pedidoRepository.findAllByClienteOrderByDataEmissaoDesc(cliente);
	}

	@Override
	public Pedido busca(Long id, Cliente cliente) {
		return this.pedidoRepository.findByIdByCliente(id, cliente.getId());
	}

	@Override
	public List<Pedido> lista(StatusPedido statusPedido) {
		return this.pedidoRepository.findAllByStatusPedido(statusPedido);
	}

	@Override
	public void sendABiker(Pedido pedido, Entregador entregador, StatusPedido statusPedido) {
		try {
			if (pedido.getStatusPedido() == StatusPedido.ENTREGADOR_ENVIADO_A_RESIDENCIA)
				throw new Exception();

			pedido.setStatusPedido(statusPedido);
			this.pedidoRepository.save(pedido);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delivering(Pedido pedido) throws Exception {

		if (pedido.getStatusPedido() == StatusPedido.ENTREGUE)
			throw new Exception();

		pedido.setStatusPedido(StatusPedido.ENTREGUE);
		this.pedidoRepository.save(pedido);

	}

	@Override
	public Double contaPorDataEntre(Calendar c1, Calendar c2) {

		List<Pedido> pedidos = this.pedidoRepository.findByDataEmissaoBetweenAndPagamento(c1, c2, Pagamento.PAGO);
		double total = 0;
		for (Pedido pedido : pedidos) {
			total += pedido.getTotal();
		}
		return total;
	}

	@Override
	public Long conta(StatusPedido status) {
		return this.pedidoRepository.countByStatusPedido(status);
	}

	@Override
	public Double somaTotal() {
		Double t = this.pedidoRepository.getTotalSum();
		if (t == null)
			return 0.0;
		else
			return t;
	}

	@Override
	public Double vendasPorMesDinheiro(Pagamento pagamento) {
		Calendar calendar = new GregorianCalendar();
		calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		Double t = this.pedidoRepository.getTotalSumByPagamentoByDataEmissao(pagamento, calendar);
		if (t == null)
			return 0.0;
		else
			return t;
	}

	@Override
	public Long conta(Pagamento pagamento) {
		return this.pedidoRepository.countByPagamento(pagamento);
	}

	@Override
	public void mudaStatus(Pedido pedido) {
		switch (pedido.getStatusPedido()) {
		case PENDENTE:
			pedido.setStatusPedido(StatusPedido.ENTREGADOR_ENVIADO_A_RESIDENCIA);
			break;
		case ENTREGADOR_ENVIADO_A_RESIDENCIA:
			pedido.setStatusPedido(StatusPedido.LAVANDO);
			break;
		case LAVANDO:
			pedido.setStatusPedido(StatusPedido.SECANDO);
			break;
		case SECANDO:
			pedido.setStatusPedido(StatusPedido.ENTREGANDO);
			break;
		case ENTREGANDO:
			pedido.setStatusPedido(StatusPedido.ENTREGUE);
			pedido.setDataEntrega(Calendar.getInstance());
			break;
		default:
			;
		}

		this.pedidoRepository.save(pedido);
	}

	@Override
	public void pagamento(Pedido pedido) {
		if (pedido.getPagamento() == Pagamento.PENDENTE) {
			pedido.setPagamento(Pagamento.PAGO);
		} else {
			pedido.setPagamento(Pagamento.PENDENTE);
		}
		this.pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> buscaTop5() {
		return this.pedidoRepository.findTop5();
	}

	@Override
	public void persiste(Pedido arg) {
	}

	@Override
	public void altera(Pedido pedido) {
		this.pedidoRepository.save(pedido);
	}

	@Override
	public Pedido busca(Long id) {
		return this.pedidoRepository.findById(id).get();
	}

	@Override
	public List<Pedido> lista() {
		return (List<Pedido>) this.pedidoRepository.findAll();
	}

	@Override
	public Long conta(Cliente cliente) {
		return this.pedidoRepository.countByCliente(cliente);
	}

}
