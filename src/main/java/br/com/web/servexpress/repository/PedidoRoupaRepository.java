package br.com.web.servexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Pedido;
import br.com.web.servexpress.model.PedidoRoupa;

public interface PedidoRoupaRepository extends CrudRepository<PedidoRoupa, Long> {

	@Query("select pr from PedidoRoupa pr where pr.pedido.cliente = ?1 order by pr.pedido.dataEmissao desc")
	List<PedidoRoupa> findLastPedido(Cliente cliente);

	@Query("select new br.com.web.servexpress.model.PedidoRoupa(pr.id,pr.roupa,pr.quantidade) from PedidoRoupa pr where pr.pedido = ?1")
	List<PedidoRoupa> findRoupasFromPedido(Pedido pedido);

	
}
