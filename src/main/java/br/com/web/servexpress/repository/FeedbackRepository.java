package br.com.web.servexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long>{

	@Query(value="select f.* from feedback f order by f.id desc limit 5", nativeQuery=true)
	List<Feedback> findTop5();

}
