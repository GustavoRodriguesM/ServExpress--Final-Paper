package br.com.web.servexpress.helper;

import br.com.web.servexpress.model.Cliente;

public class TopClienteAdminDashboard implements Comparable<TopClienteAdminDashboard> {

	private Cliente cliente;
	private Long numeroPedidos;

	public TopClienteAdminDashboard(Cliente cliente, Long numeroPedidos) {
		this.cliente = cliente;
		this.numeroPedidos = numeroPedidos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getNumeroPedidos() {
		return numeroPedidos;
	}

	public void setNumeroPedidos(Long numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	@Override
	public int compareTo(TopClienteAdminDashboard outro) {
		if (this.numeroPedidos < outro.getNumeroPedidos())
			return -1;
		if (this.numeroPedidos > outro.getNumeroPedidos())
			return 1;
		return 0;
	}

}
