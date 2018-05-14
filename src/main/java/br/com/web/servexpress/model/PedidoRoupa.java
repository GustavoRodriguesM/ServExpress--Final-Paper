package br.com.web.servexpress.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido_roupas")
public class PedidoRoupa {
	
	@Id
	@GeneratedValue
	@Column(name = "pedido_roupa_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roupa_id")
	private Roupa roupa;

	private Integer quantidade;

	
	public PedidoRoupa() { //JPA
	}
	
	public PedidoRoupa(Long id,Roupa roupa, Integer quantidade) {
		this.id = id;
		this.roupa = roupa;
		this.quantidade = quantidade;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Roupa getRoupa() {
		return this.roupa;
	}

	public void setRoupa(Roupa roupa) {
		this.roupa = roupa;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
