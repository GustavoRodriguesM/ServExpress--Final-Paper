package br.com.web.servexpress.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "roupa")
public class Roupa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valorUnitario;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar deletadoEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Calendar getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(Calendar deletadoEm) {
		this.deletadoEm = deletadoEm;
	}

}
