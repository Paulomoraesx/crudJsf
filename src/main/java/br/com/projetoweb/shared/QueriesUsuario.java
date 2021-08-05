package br.com.projetoweb.shared;

public class QueriesUsuario {
	
	public static final String QUERY_CONSULTAR_TODOS_USUARIOS = "SELECT usuarios.name, usuarios.email FROM USUARIOS WHERE usuarios.ativo != false";
	
	public static final String QUERY_CONSULTAR_EMAIL_USUARIO = "SELECT usuarios.email FROM USUARIOS WHERE usuarios.email like ? and usuarios.ativo != false";
	

	public static final String QUERY_SALVAR_USUARIO = "INSERT INTO USUARIOS (name, email, password, ativo) VALUES (?,?,?,true)";
	

	public static final String QUERY_ALTERAR_USUARIO = "UPDATE USUARIOS SET name=?, email=?, password=?, "
			+ "ultima_data_hora_modificacao=CURRENT_TIMESTAMP WHERE email like ?";
	

	public static final String QUERY_DELETAR_USUARIO = "UPDATE USUARIOS SET ativo=false, ultima_data_hora_modificacao=CURRENT_TIMESTAMP"
			+ " WHERE email like ?";
	
	

}
