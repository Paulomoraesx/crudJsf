package br.com.projetoweb.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.projetoweb.model.Usuario;
import br.com.projetoweb.service.SessaoService;
import br.com.projetoweb.util.MensagemUtil;
import br.com.projetoweb.util.PagesUtil;
import br.com.projetoweb.util.VerificadorUtil;

@ManagedBean(name = "sessaoMB")
@ViewScoped
public class SessaoController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioAcesso;
	private SessaoService sessaoService;
	
	
	public SessaoController() {
		usuarioAcesso = new Usuario();
		sessaoService = new SessaoService();
	}
	
	public void validarAcesso() throws IOException {
		if(VerificadorUtil.estaNulo(usuarioAcesso.getEmail()) || VerificadorUtil.estaNulo(usuarioAcesso.getPassword())) {
			MensagemUtil.erro("Senha e/ou Email não preenchidos!!");
		}else if(sessaoService.verificarLogin(usuarioAcesso)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user_session", usuarioAcesso);
            redirecionarParaPrincipal();
        } else {
            MensagemUtil.erro("Senha e Email Incorretos!");
        }
	}
	
	public void controlarSessao() throws IOException {
		if(VerificadorUtil.estaNulo(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_session"))) {
			PagesUtil.redirectPage("login");
		}
	}
	
	public void redirecionarParaLogin() throws IOException {
		PagesUtil.redirectPage("login");
	}

	private void redirecionarParaPrincipal() throws IOException {
		PagesUtil.redirectPage("todosOsUsuarios");
	}
	
	

	public Usuario getUsuarioAcesso() {
		return usuarioAcesso;
	}

	public void setUsuarioAcesso(Usuario usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}

	public SessaoService getSessaoService() {
		return sessaoService;
	}

	public void setSessaoService(SessaoService sessaoService) {
		this.sessaoService = sessaoService;
	}
	
	
	

}
