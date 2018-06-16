package br.com.web.servexpress.conf;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import br.com.web.servexpress.enums.StatusEntregador;
import br.com.web.servexpress.model.Endereco;
import br.com.web.servexpress.model.Entregador;
import br.com.web.servexpress.model.Role;
import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.repository.RoleRepository;
import br.com.web.servexpress.service.EnderecoService;
import br.com.web.servexpress.service.EntregadorService;
import br.com.web.servexpress.service.UsuarioService;

@Component
public class InitData implements ApplicationListener<ApplicationContextEvent> {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EntregadorService entregadorService;

	@Override
	public void onApplicationEvent(ApplicationContextEvent context) {
		List<Role> roles = (List<Role>) roleRepository.findAll();
		if (roles.isEmpty()) {
			roleRepository.save(new Role("ROLE_CLIENTE"));
			roleRepository.save(new Role("ROLE_ADMINISTRADOR"));
			roleRepository.save(new Role("ROLE_ENTREGADOR"));
			roleRepository.save(new Role("ROLE_DISABLE"));

			Usuario user = new Usuario();
			user.setEmail("admin@servexpress.com.br");
			user.setNome("Administrador");
			user.setSenha("1234567");
			user.setConfirmarSenha("1234567");
			user.setDataCadastro(Calendar.getInstance());
			user.setToken();
			userService.persiste(user, "ROLE_ADMINISTRADOR");
			
			Usuario user1 = new Usuario();
			user1.setEmail("entregador@servexpress.com.br");
			user1.setNome("Entregador");
			user1.setSenha("1234567");
			user1.setConfirmarSenha("1234567");
			user1.setDataCadastro(Calendar.getInstance());
			user1.setToken();
			
			Entregador entregador = new Entregador();
			entregador.setCnh("0000000000");
			entregador.setCpf("000.000.000-00");
			entregador.setRg("00.000.000-0");
			entregador.setStatus(StatusEntregador.EMPREGADO);
			
			Endereco endereco = new Endereco();
			endereco.setBairro("Carrão");
			endereco.setCep("00000-000");
			endereco.setRua("Rua X");
			endereco.setNumero("1547");
			
			enderecoService.persiste(endereco);
			userService.persiste(user1, "ROLE_ENTREGADOR");
			
			entregador.setUsuario(user1);
			entregador.setEndereco(endereco);
			entregadorService.persiste(entregador);
		}

	}

}
