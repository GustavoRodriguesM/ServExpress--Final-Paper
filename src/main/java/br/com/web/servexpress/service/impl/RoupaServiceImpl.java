package br.com.web.servexpress.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Roupa;
import br.com.web.servexpress.repository.RoupaRepository;
import br.com.web.servexpress.service.RoupaService;

@Service
public class RoupaServiceImpl implements RoupaService {

	@Autowired
	private RoupaRepository roupaRepository;

	@Override
	public void persiste(Roupa roupa) {
		roupaRepository.save(roupa);
	}

	@Override
	public void desativa(Roupa roupa) {
		roupa.setDeletadoEm(Calendar.getInstance());
		roupaRepository.save(roupa);
	}

	@Override
	public void ativa(Roupa roupa) {
		roupa.setDeletadoEm(null);
		roupaRepository.save(roupa);
	}

	@Override
	public Roupa busca(Long id) {
		return roupaRepository.findById(id).get();
	}

	@Override
	public List<Roupa> lista() {
		return (List<Roupa>) this.roupaRepository.findAllByDeletadoEm(null);
	}

	@Override
	public List<Roupa> listaComDeletados() {
		return (List<Roupa>) this.roupaRepository.findAll();
	}

	@Override
	public void altera(Roupa roupa) {
		this.roupaRepository.save(roupa);
	}

}
