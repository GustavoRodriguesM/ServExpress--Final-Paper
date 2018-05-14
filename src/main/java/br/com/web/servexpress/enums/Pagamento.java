package br.com.web.servexpress.enums;

public enum Pagamento {
	PENDENTE(1, "Pendente"), PAGO(2, "Pago");

	public int id;
	public String descricao;

	private Pagamento(int id, String descricao) {
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
