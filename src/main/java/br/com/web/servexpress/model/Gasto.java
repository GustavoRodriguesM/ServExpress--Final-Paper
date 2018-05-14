package br.com.web.servexpress.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.web.servexpress.enums.Classificacao;

@Entity(name = "gasto")
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recurso;
	private Double valor;

	@Enumerated(EnumType.STRING)
	private Classificacao classificacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar deletadoEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Calendar getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(Calendar deletadoEm) {
		this.deletadoEm = deletadoEm;
	}

}
