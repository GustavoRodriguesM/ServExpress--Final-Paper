package br.com.web.servexpress.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Endereco;
import br.com.web.servexpress.repository.EnderecoRepository;
import br.com.web.servexpress.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	public EnderecoRepository enderecoRepository;

	@Override
	public void persiste(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	@Override
	public Endereco busca(Long id) {
		return this.enderecoRepository.findById(id).get();
	}

	@Override
	public List<Endereco> lista() {
		return (List<Endereco>) this.enderecoRepository.findAll();
	}

	@Override
	public void altera(Endereco endereco) {
		this.enderecoRepository.save(endereco);
	}

}
