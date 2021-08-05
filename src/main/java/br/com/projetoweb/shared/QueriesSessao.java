package br.com.projetoweb.shared;

public class QueriesSessao {
	
	public static final String QUERY_VERIFICAR_USUARIO = "SELECT u.name, u.email, u.password FROM usuarios as u WHERE"
			+ " u.email like ? and u.password like ? and ativo = true";

}
