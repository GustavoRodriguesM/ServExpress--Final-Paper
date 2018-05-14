package br.com.web.servexpress.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.enums.Classificacao;
import br.com.web.servexpress.model.Gasto;

@Repository
public interface GastoRepository extends CrudRepository<Gasto, Long>{
	
	List<Gasto> findAllByDeletadoEm(Calendar calendar);

	List<Gasto> findAllByClassificacao(Classificacao fixo);
	
}
