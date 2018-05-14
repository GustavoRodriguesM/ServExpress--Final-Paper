package br.com.web.servexpress.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Bairro;
import br.com.web.servexpress.repository.BairroRepository;
import br.com.web.servexpress.service.BairroService;

@Service
public class BairroServiceImpl implements BairroService {

	@Autowired
	private BairroRepository bairroRepository;

	@Override
	public void persiste(Bairro bairro) {
		bairroRepository.save(bairro);
	}

	@Override
	public void desativa(Bairro bairro) {
		bairro.setDeletadoEm(Calendar.getInstance());
		bairroRepository.save(bairro);
	}

	@Override
	public void ativa(Bairro bairro) {
		bairro.setDeletadoEm(null);
		bairroRepository.save(bairro);

	}

	@Override
	public Bairro busca(Long id) {
		return bairroRepository.findById(id).get();
	}

	@Override
	public List<Bairro> lista() {
		return (List<Bairro>) this.bairroRepository.findAllByDeletadoEm(null);
	}

	@Override
	public List<Bairro> listaComDeletados() {
		return (List<Bairro>) this.bairroRepository.findAll();
	}

	@Override
	public void altera(Bairro bairro) {
		this.bairroRepository.save(bairro);
	}
}
