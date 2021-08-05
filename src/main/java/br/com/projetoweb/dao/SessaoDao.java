package br.com.projetoweb.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projetoweb.factory.ConnectionFactory;
import br.com.projetoweb.model.Usuario;
import br.com.projetoweb.shared.QueriesSessao;

public class SessaoDao implements Serializable{

	private static final long serialVersionUID = 1L;

	public Boolean verificarCredenciais(Usuario usuario) {
		Boolean verificou = false;
		Connection conexao = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conexao.prepareStatement(QueriesSessao.QUERY_VERIFICAR_USUARIO);
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				verificou = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conexao.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return verificou;
	} 

}
