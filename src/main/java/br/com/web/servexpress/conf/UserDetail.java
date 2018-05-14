package br.com.web.servexpress.conf;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.web.servexpress.model.Usuario;
import br.com.web.servexpress.repository.UsuarioRepository;


@Repository
@Transactional
public class UserDetail implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = this.repository.findByEmail(email);
		if (user == null)
			throw new RuntimeException("Usuario " + email + " não encontrado!");

		return user;
	}

}
