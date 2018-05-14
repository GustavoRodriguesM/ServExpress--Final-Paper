package br.com.web.servexpress.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findByEmail(String email);

	Usuario findByToken(String token);

	Long countByEmail(String email);

}
