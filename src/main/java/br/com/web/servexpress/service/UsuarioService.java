package br.com.web.servexpress.service;

import br.com.web.servexpress.model.Usuario;

public interface UsuarioService extends AbstractService<Usuario> {

	Usuario findByToken(String token);

	Long countByEmail(String email);

	void alteraSenha(String senha, String token);

	Usuario findByEmail(String email);

	void persiste(Usuario usuario, String role);

}
