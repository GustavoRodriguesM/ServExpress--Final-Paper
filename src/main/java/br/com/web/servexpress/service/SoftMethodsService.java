package br.com.web.servexpress.service;

import java.util.List;

public interface SoftMethodsService<T> {

	void desativa(T arg);
	void ativa(T arg);
	List<T> listaComDeletados();
	
}
