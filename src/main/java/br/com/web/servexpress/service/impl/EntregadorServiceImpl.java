package br.com.web.servexpress.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.enums.StatusEntregador;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.repository.EntregadorRepository;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.UsuarioService;

@Service
public class EntregadorServiceImpl implements EntregadorService {

	@Autowired
	private EntregadorRepository entregadorRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Entregador busca(Usuario user) {
		return this.entregadorRepository.findByUsuario(user);
	}

	@Override
	public Long conta() {
		return this.entregadorRepository.count();
	}

	@Override
	public void persiste(Entregador entregador) {
		this.entregadorRepository.save(entregador);
	}

	@Override
	public Entregador busca(Long id) {
		return this.entregadorRepository.findById(id).get();
	}

	@Override
	public List<Entregador> lista() {
		return (List<Entregador>) this.entregadorRepository.findByDemitidoEm(null);
	}

	@Override
	public void altera(Entregador entregador) {
		this.entregadorRepository.save(entregador);
	}

	@Override
	public List<Entregador> listaComDeletados() {
		return (List<Entregador>) this.entregadorRepository.findAll();
	}

	@Override
	public void desativa(Entregador entregador) {
		entregador.setStatus(StatusEntregador.DEMITIDO);
		entregador.setDemitidoEm(Calendar.getInstance());
		this.usuarioService.desativa(entregador.getUsuario());
		
		this.altera(entregador);
	}
	
	@Override
	public void ativa(Entregador entregador) {
		entregador.setStatus(StatusEntregador.EMPREGADO);
		entregador.setDemitidoEm(null);
		
		this.usuarioService.ativa(entregador.getUsuario());
		
		this.altera(entregador);
	}

}
