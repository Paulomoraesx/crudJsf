package br.com.projetoweb.controller;

import static br.com.projetoweb.util.constantes.Constantes.CONDICAO_ALTERAR;
import static br.com.projetoweb.util.constantes.Constantes.CONDICAO_CADASTRAR;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.projetoweb.model.Usuario;
import br.com.projetoweb.service.UsuarioService;
import br.com.projetoweb.util.MensagemUtil;
import br.com.projetoweb.util.PagesUtil;
import br.com.projetoweb.util.VerificadorUtil;


@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private ArrayList<Usuario> listaUsuarios;
	private UsuarioService usuarioService;
	private String emailAlteracao;
	private String condicaoParaTelaCadastrarAtualizar;

	public UsuarioController() {
		usuario = new Usuario();
		usuarioService = new UsuarioService();
	}

	public ArrayList<Usuario> listarTodosUsuarios() {
		return listaUsuarios = usuarioService.listarTodosUsuarios();
	}

	public void gravarUsuario() throws IOException {
		if (usuarioService.adicionarUsuario(usuario)) {
			if (VerificadorUtil.estaNulo(
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_session"))) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user_session", usuario);
			}
			MensagemUtil.sucesso("Usuário cadastrado com sucesso");
			PagesUtil.redirectPage("todosOsUsuarios");
		} else {
			MensagemUtil.erro("Erro ao cadastrar usuário");
		}
	}

	public void alterarUsuario() throws IOException {
		if (usuarioService.alterarUsuario(usuario, emailAlteracao)) {
			PagesUtil.redirectPage("todosOsUsuarios");
			MensagemUtil.sucesso("Usuário cadastrado com sucesso");
		} else {
			MensagemUtil.erro("Erro ao alterar usuário");
		}
	}

	public void deletarUsuario() throws IOException {
		if (usuarioService.deletarUsuario(usuario.getEmail())) {
			PagesUtil.redirectPage("todosOsUsuarios");
			MensagemUtil.sucesso("Usuário Deletado com sucesso");
		} else {
			MensagemUtil.erro("Erro ao deletar Usuário");
		}
	}

	public void iniciarDadosPaginaPrincipal() {
		listarTodosUsuarios();
	}

	public void validarUsuarioCadastrado() {
		usuarioService.verificarEmailUsuario(usuario);
	}

	public void definirCadastrarOuAtualizar() {
		if (ehParaAtualizar()) {
			usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("usuario_alteracao");
			emailAlteracao = usuario.getEmail();
			condicaoParaTelaCadastrarAtualizar = CONDICAO_ALTERAR;
		} else {
			condicaoParaTelaCadastrarAtualizar = CONDICAO_CADASTRAR;
		}
	}

	private String retornarCondicaoParaTelaCadastrarAtualizar() {
		if (ehParaAtualizar()) {
			return CONDICAO_ALTERAR;
		} else {
			return CONDICAO_CADASTRAR;
		}
	}

	public void validarCondicoesParaCadastro() throws IOException {
		if (retornarCondicaoParaTelaCadastrarAtualizar().equals("Cadastrar")) {
			validarUsuarioCadastrado();
		} else {
			alterarUsuario();
		}
	}
	public void realizarLogout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("", null);
		PagesUtil.redirectPage("login");
	}

	public boolean ehParaAtualizar() {
		return VerificadorUtil.naoEstaNulo(
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario_alteracao"));
	}
	
	public void redirecionarParaAtualizar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario_alteracao", usuario);
		PagesUtil.redirectPage("cadastrarUsuarios");
	}
	
	public void redirecionarParaCadastrar() throws IOException {
		PagesUtil.redirectPage("cadastrarUsuarios");
	}
	
	public void redirecionarParaPrincipal() throws IOException {
		PagesUtil.redirectPage("todosOsUsuarios");
	}
		
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getcondicaoParaTelaCadastrarAtualizar() {
		return condicaoParaTelaCadastrarAtualizar;
	}

	public void setcondicaoParaTelaCadastrarAtualizar(String condicaoParaTelaCadastrarAtualizar) {
		this.condicaoParaTelaCadastrarAtualizar = condicaoParaTelaCadastrarAtualizar;
	}

}
