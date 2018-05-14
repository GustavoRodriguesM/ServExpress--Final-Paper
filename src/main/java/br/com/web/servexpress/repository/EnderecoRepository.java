package br.com.web.servexpress.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Endereco;

@Repository
@Transactional
public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

}
