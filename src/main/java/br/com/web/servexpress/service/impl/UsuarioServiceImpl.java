package br.com.web.servexpress.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Role;
import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.repository.RoleRepository;
import br.com.web.servexpress.repository.UsuarioRepository;
import br.com.web.servexpress.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Usuario findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public void alteraSenha(String senha, String token) {
		Usuario usuario = userRepository.findByToken(token);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		usuario.setSenha(encoder.encode(senha));
		usuario.setConfirmarSenha(usuario.getSenha());
		usuario.setToken();
		userRepository.save(usuario);

	}

	@Override
	public Long countByEmail(String email) {
		return this.userRepository.countByEmail(email);
	}

	@Override
	public Usuario findByToken(String token) {
		return this.userRepository.findByToken(token);
	}

	@Override
	public void persiste(Usuario usuario) {
		this.userRepository.save(usuario);
	}

	@Override
	public void persiste(Usuario usuario, String role) {
		usuario.setConfirmarSenha("");
		usuario.setRoles(Arrays.asList(new Role(role)));

		if (usuario.getId() == null) {
			usuario.setToken();
			usuario.setDataCadastro(Calendar.getInstance());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			usuario.setConfirmarSenha(usuario.getSenha());
		} else
			usuario.setToken();

		userRepository.save(usuario);
	}

	@Override
	public Usuario busca(Long id) {
		return this.userRepository.findById(id).get();
	}

	@Override
	public List<Usuario> lista() {
		return (List<Usuario>) this.userRepository.findAll();
	}

	@Override
	public void altera(Usuario user) {
		this.userRepository.save(user);
	}

	@Override
	public void desativa(Usuario usuario) {
		Role role = this.roleRepository.findById("ROLE_DISABLE").get();
		List<Role> roles = usuario.getRoles();
		roles.add(role);
		usuario.setRoles(roles);
		
		this.userRepository.save(usuario);
	}
	
	@Override
	public void ativa(Usuario usuario) {
		Role role = this.roleRepository.findById("ROLE_DISABLE").get();
		usuario.getRoles().remove(role);
		
		this.userRepository.save(usuario);
	}

}
