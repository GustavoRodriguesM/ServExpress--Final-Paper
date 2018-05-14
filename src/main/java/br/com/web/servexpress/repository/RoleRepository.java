package br.com.web.servexpress.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

	
}
