package br.com.projetoweb.service;

import br.com.projetoweb.dao.SessaoDao;
import br.com.projetoweb.model.Usuario;

public class SessaoService {
	
	private SessaoDao sessao;
	
	public SessaoService() {
		sessao = new SessaoDao();
	}
	
	public Boolean verificarLogin(Usuario usuario) {
		return sessao.verificarCredenciais(usuario);
	}

}
