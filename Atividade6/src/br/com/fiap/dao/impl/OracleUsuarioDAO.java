package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.model.*;
import br.com.fiap.exception.*;
import br.com.fiap.singleton.*;
import fiap.com.br.dao.UsuarioDAO;

public class OracleUsuarioDAO implements UsuarioDAO {
	private Connection conexao;
	public void cadastrar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			java.sql.Date dataSql = new java.sql.Date(usuario.getDatanascimento().getTime());
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_USUARIO (id_usuario, pw_usuario, nm_usuario, dt_nascimento, ds_email) VALUES (ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getPassword());
			stmt.setString(2, usuario.getNome());
			//usuario digitar a data que nasceu
			stmt.setDate(3, dataSql);
			stmt.setString(4, usuario.getDsemail());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void atualizar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_USUARIO SET pw_usuario = ?, nm_usuario = ?, dt_nascimento = ? WHERE id_usuario = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getPassword());
			stmt.setString(2, usuario.getNome());
			//usuario digitar a data que nasceu
			//stmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt.setString(4, usuario.getDsemail());
			stmt.setInt(5, usuario.getCdusuario());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void remover(int cdusuario) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_USUARIO WHERE ID_USUARIO = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, cdusuario);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	@Override
	public Usuario buscar(int id) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE ID_USUARIO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int cdusuario = rs.getInt("id_usuario");
				String password = rs.getString("pw_usuario");
				String nome = rs.getString("nm_usuario");
				//arrumar data pra nao ser a data atual
				java.sql.Date datanascimento = rs.getDate("dt_nascimento");
				String dsemail = rs.getString("ds_email");
				
				usuario = new Usuario(cdusuario, password, nome, datanascimento, dsemail);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	public Usuario logar(String email, String password) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND PW_USUARIO = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()){
				int cdusuario = rs.getInt("id_usuario");
				String passw = rs.getString("pw_usuario");
				String nome = rs.getString("nm_usuario");
				//arrumar data pra nao ser a data atual
				java.sql.Date datanascimento = rs.getDate("dt_nascimento");
				String dsemail = rs.getString("ds_email");
				
				usuario = new Usuario(cdusuario, passw, nome, datanascimento, dsemail);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int cdusuario = rs.getInt("id_usuario");
				String password = rs.getString("pw_usuario");
				String nome = rs.getString("nm_usuario");
				java.sql.Date datanascimento = rs.getDate("dt_nascimento");
				String dsemail = rs.getString("ds_email");
				
				Usuario usuario = new Usuario(cdusuario, password, nome, datanascimento, dsemail);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}



}
