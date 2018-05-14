package br.com.web.servexpress.enums;

public enum StatusPedido {
	PENDENTE(1, "Pendente"), ENTREGADOR_ENVIADO_A_RESIDENCIA(2, "Entregador enviado à residência"), LAVANDO(3,
			"Lavando"), SECANDO(4, "Secando"), ENTREGANDO(5, "Entregando"), ENTREGUE(6, "Entregue");

	public int id;
	public String descricao;

	private StatusPedido(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
