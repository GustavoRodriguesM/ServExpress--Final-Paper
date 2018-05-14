package br.com.web.servexpress.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Bairro")
public class Bairro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar deletadoEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(Calendar deletadoEm) {
		this.deletadoEm = deletadoEm;
	}

}
