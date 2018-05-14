package br.com.web.servexpress.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.web.servexpress.enums.StatusEntregador;

@Entity(name = "entregador")
public class Entregador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Usuario usuario;

	@OneToOne
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private StatusEntregador status;

	private String rg;

	private String cpf;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar demitidoEm;

	private String cnh;

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Calendar getDemitidoEm() {
		return demitidoEm;
	}

	public void setDemitidoEm(Calendar demitidoEm) {
		this.demitidoEm = demitidoEm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public StatusEntregador getStatus() {
		return status;
	}

	public void setStatus(StatusEntregador status) {
		this.status = status;
	}

}
