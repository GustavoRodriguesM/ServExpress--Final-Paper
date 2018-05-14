package br.com.web.servexpress.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Gasto;
import br.com.web.servexpress.repository.GastoRepository;
import br.com.web.servexpress.service.GastoService;

@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	@Override
	public Double valorTotal() {
		List<Gasto> gastos = this.gastoRepository.findAllByDeletadoEm(null);
		Double valor = 0.0;

		for (Gasto gasto : gastos) {
			valor += gasto.getValor();
		}

		return valor;
	}

	@Override
	public void persiste(Gasto gasto) {
		gastoRepository.save(gasto);
	}

	@Override
	public void desativa(Gasto gasto) {
		gasto.setDeletadoEm(Calendar.getInstance());
		gastoRepository.save(gasto);
	}

	@Override
	public void ativa(Gasto gasto) {
		gasto.setDeletadoEm(null);
		gastoRepository.save(gasto);
	}

	@Override
	public Gasto busca(Long id) {
		return this.gastoRepository.findById(id).get();
	}

	@Override
	public List<Gasto> lista() {
		return this.gastoRepository.findAllByDeletadoEm(null);
	}

	@Override
	public List<Gasto> listaComDeletados() {
		return (List<Gasto>) this.gastoRepository.findAll();
	}

	@Override
	public void altera(Gasto gasto) {
		this.gastoRepository.save(gasto);
	}

}
