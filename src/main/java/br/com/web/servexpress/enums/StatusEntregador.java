package br.com.web.servexpress.enums;

public enum StatusEntregador {
	EMPREGADO(1, "Empregado"), DEMITIDO(2, "Demitido");

	public int id;
	public String descricao;

	private StatusEntregador(int id, String descricao) {
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
