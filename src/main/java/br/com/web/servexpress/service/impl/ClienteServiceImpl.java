package br.com.web.servexpress.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.helper.TopClienteAdminDashboard;
import br.com.web.servexpress.model.Cliente;
import br.com.web.servexpress.model.Feedback;
import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.repository.ClienteRepository;
import br.com.web.servexpress.service.ClienteService;
import br.com.web.servexpress.service.PedidoService;

@Service
public class ClienteServiceImpl implements ClienteService {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private PedidoService pedidoService;

	@Override
	public Cliente busca(Usuario user) {
		return clienteRepository.findByUsuario(user);
	}

	@Override
	public void registraFeedback(Feedback feedback) {
		Cliente cliente = feedback.getCliente();
		cliente.setFeedback(true);
		this.clienteRepository.save(cliente);

		this.feedbackService.register(feedback);
	}

	@Override
	public void persiste(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente busca(Long id) {
		return this.clienteRepository.findById(id).get();
	}

	@Override
	public List<Cliente> lista() {
		return (List<Cliente>) this.clienteRepository.findAll();
	}

	@Override
	public void altera(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public List<TopClienteAdminDashboard> top5PorNumeroPedidos() {
		List<TopClienteAdminDashboard> top5Clientes = new ArrayList<>();
		for (Cliente cliente : this.lista()) {
			top5Clientes.add(new TopClienteAdminDashboard(cliente, this.pedidoService.conta(cliente)));
		}
		Collections.sort(top5Clientes);
		top5Clientes = top5Clientes.stream().limit(5).collect(Collectors.toList());
		Collections.reverse(top5Clientes);
		return top5Clientes;
	}

}
