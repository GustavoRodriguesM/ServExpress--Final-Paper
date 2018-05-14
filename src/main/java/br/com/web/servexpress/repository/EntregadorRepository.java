package br.com.web.servexpress.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Usuario;

@Repository
public interface EntregadorRepository extends CrudRepository<Entregador, Long>{

	Entregador findByUsuario(Usuario user);

	List<Entregador> findByDemitidoEm(Calendar calendar);

}
