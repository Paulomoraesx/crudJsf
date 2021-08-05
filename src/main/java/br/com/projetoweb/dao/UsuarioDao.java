package br.com.projetoweb.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.projetoweb.factory.ConnectionFactory;
import br.com.projetoweb.model.Usuario;
import br.com.projetoweb.shared.QueriesUsuario;
import br.com.projetoweb.util.VerificadorUtil;
public class UsuarioDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Connection conexao = null;

	
	public ArrayList<Usuario> listarTodosUsuarios(){
		ArrayList<Usuario> retornarListaUsuarios = new ArrayList<Usuario>();
		conexao = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conexao.prepareStatement(QueriesUsuario.QUERY_CONSULTAR_TODOS_USUARIOS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				retornarListaUsuarios.add(user);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					conexao.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return retornarListaUsuarios;
	} 
	
	public boolean adicinarUsuario(Usuario usuario) {
		boolean salvou = false;
		conexao = ConnectionFactory.getConnection();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement(QueriesUsuario.QUERY_SALVAR_USUARIO);
			mapearPreparedStatementParaSalvar(usuario, ps);
			ps.executeUpdate();
			salvou = true;
			conexao.commit();			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return salvou;
	}
	
	public boolean alterarUsuario(Usuario usuario, String emailParaAlterar) {
		boolean alterou = false;
		conexao = ConnectionFactory.getConnection();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement(QueriesUsuario.QUERY_ALTERAR_USUARIO);
			mapearPreparedStatementParaSalvar(usuario, ps);
			ps.setString(1, emailParaAlterar);
			ps.executeUpdate();
			alterou = true;
			conexao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return alterou;		
	}
	
	public boolean deletarUsuario(String emailParaDeletar) {
		boolean deletou = false;
		conexao = ConnectionFactory.getConnection();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement(QueriesUsuario.QUERY_DELETAR_USUARIO);
			ps.setString(1, emailParaDeletar);
			ps.executeUpdate();
			deletou = true;
			conexao.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return deletou;
	}
	
	
    public Boolean verificarEmail(Usuario usuario) {
        boolean retorno = false;
        conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(QueriesUsuario.QUERY_CONSULTAR_EMAIL_USUARIO);
            ps.setString(1, usuario.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                retorno = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return retorno;
    }
	
	
	
	public void mapearPreparedStatementParaSalvar(Usuario usuario, PreparedStatement ps) throws SQLException {
		if(VerificadorUtil.naoEstaNuloOuVazio(usuario.getName())) {
			ps.setString(1, usuario.getName());
		}else {
			ps.setNull(1, java.sql.Types.NULL);
		}
		
		if(VerificadorUtil.naoEstaNuloOuVazio(usuario.getEmail())) {
			ps.setString(2, usuario.getEmail());
		}else {
			ps.setNull(2, java.sql.Types.NULL);
		}
		
		if(VerificadorUtil.naoEstaNuloOuVazio(usuario.getPassword())) {
			ps.setString(3, usuario.getPassword());
		}else {
			ps.setNull(3, java.sql.Types.NULL);
		}
	}
	
	
	
}
