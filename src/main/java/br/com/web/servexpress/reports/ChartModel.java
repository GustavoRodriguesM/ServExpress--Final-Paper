package br.com.web.servexpress.reports;

public class ChartModel {

	private String descricao;
	private Double valor;
	private String mes;


	public ChartModel(String descricao, Double valor) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public ChartModel(String descricao, Double valor, Integer mes) {
		this.descricao = descricao;
		this.valor = valor;
		this.setMes(mes);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		if (mes == 0)
			this.mes = "Janeiro";
		else if (mes == 1)
			this.mes = "Fevereiro";
		else if (mes == 2)
			this.mes = "Março";
		else if (mes == 3)
			this.mes = "Abril";
		else if (mes == 4)
			this.mes = "Maio";
		else if (mes == 5)
			this.mes = "Junho";
		else if (mes == 6)
			this.mes = "Julho";
		else if (mes == 7)
			this.mes = "Agosto";
		else if (mes == 8)
			this.mes = "Setembro";
		else if (mes == 9)
			this.mes = "Outubro";
		else if (mes == 10)
			this.mes = "Novembro";
		else if (mes == 11)
			this.mes = "Dezembro";
	}


}
