package br.com.web.servexpress.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Usuario;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	Cliente findByUsuario(Usuario usuario);
	
}
