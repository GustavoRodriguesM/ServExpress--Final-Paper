package br.com.web.servexpress.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Bairro;

@Repository
@Transactional
public interface BairroRepository extends CrudRepository<Bairro, Long>{

	List<Bairro> findAllByDeletadoEm(Calendar calendar);

}
