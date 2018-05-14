package br.com.web.servexpress.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.web.servexpress.enums.Pagamento;
import br.com.web.servexpress.enums.StatusPedido;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	List<Pedido> findAllByClienteOrderByDataEmissaoDesc(Cliente cliente);

	List<Pedido> findAllByStatusPedido(StatusPedido statusPedido);

	Long countByStatusPedido(StatusPedido status);

	Long countByPagamento(Pagamento pagamento);

	List<Pedido> findByDataEmissaoBetweenAndPagamento(Calendar c1, Calendar c2, Pagamento pagamento);

	@Query(value = "select sum(p.total) from pedido as p")
	Double getTotalSum();

	@Query(value = "select sum(p.total) from pedido as p where p.pagamento = ?1 and p.dataEmissao >= ?2")
	Double getTotalSumByPagamentoByDataEmissao(Pagamento pagamento, Calendar dataEmissao);

	@Query(value = "select * from pedido where id = ?1 and cliente_id = ?2", nativeQuery = true)
	Pedido findByIdByCliente(Long id, Long cliente_id);

	Long countByDataEmissaoBetween(Calendar calendar, Calendar calendar1);

	@Query(value="select p.* from pedido p order by p.id desc limit 5", nativeQuery=true)
	List<Pedido> findTop5();

	Long countByCliente(Cliente cliente);

	// Pedido findByIdByCliente(Long id, Cliente cliente);
}
