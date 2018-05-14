package br.com.web.servexpress.service;

import br.com.web.servexpress.model.Gasto;

public interface GastoService extends AbstractService<Gasto>, SoftMethodsService<Gasto> {
	
	Double valorTotal();

}
