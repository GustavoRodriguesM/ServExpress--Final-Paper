package br.com.web.servexpress.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.web.servexpress.model.Roupa;

public interface RoupaRepository extends CrudRepository<Roupa, Long>{

	List<Roupa> findAllByDeletadoEm(Calendar deletadoEm);

}
