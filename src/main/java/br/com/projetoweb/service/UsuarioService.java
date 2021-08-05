package br.com.projetoweb.service;

import java.util.ArrayList;

import br.com.projetoweb.dao.UsuarioDao;
import br.com.projetoweb.model.Usuario;
import br.com.projetoweb.util.MensagemUtil;

public class UsuarioService {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioService() {
		usuarioDao = new UsuarioDao();
	}
	
	public ArrayList<Usuario> listarTodosUsuarios() {
		return usuarioDao.listarTodosUsuarios();
		 
	}
	public boolean adicionarUsuario(Usuario usuario) {
		return usuarioDao.adicinarUsuario(usuario);
	}
	public boolean alterarUsuario(Usuario usuario, String emailAlteracao) {
		return usuarioDao.alterarUsuario(usuario, emailAlteracao);
	}
	public boolean deletarUsuario(String email) {
		return usuarioDao.deletarUsuario(email);
	}
	public void verificarEmailUsuario(Usuario usuario) {
		if(usuarioDao.verificarEmail(usuario)) {
			MensagemUtil.erro("Usuário já Cadastrado");
		}else 
			adicionarUsuario(usuario);		
	}

}
