package br.com.web.servexpress.service;

import java.util.List;

public interface AbstractService<T> {

	void persiste(T arg);
	void altera(T arg);
	T busca(Long id);
	List<T> lista();

	
}
