package br.com.fiap.dao.impl;
import java.math.BigDecimal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.*;
import br.com.fiap.exception.*;
import br.com.fiap.singleton.*;
import fiap.com.br.dao.PressaoDAO;


public class OraclePressaoDAO implements PressaoDAO {
	
	private Connection conexao;
	public void cadastrar(Pressao pressao, int id) throws DBException {
        PreparedStatement stmt = null;

        // Objeto que manterá o valor das duas PKs
        BigDecimal nextVal = BigDecimal.ZERO;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            // Consulta sequence para obter próximo valor
            String sql0 = "select CD_PRESSAO_SEQ.NEXTVAL from T_PRESSAO";
            PreparedStatement estrutura;

            estrutura = conexao.prepareStatement(sql0);
            ResultSet rs = estrutura.executeQuery();

            while (rs.next()) {
                nextVal = rs.getBigDecimal(1);
            }
            estrutura.close(); 

            String sql = "INSERT INTO T_PRESSAO ( cd_pressao, T_USUARIO_id_usuario, pressao_d, pressao_s, dT_PRESSAO_usuario) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setBigDecimal(1, nextVal); // Seta o valor obtido na consulta a sequence
            stmt.setInt(2, id); // Seta o valor do ID do usuário
            stmt.setInt(3, pressao.getPressaod());
            stmt.setInt(4, pressao.getPressaos());
            stmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));

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
	public void atualizar(Pressao pressao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PRESSAO SET pressao_d = ?, pressao_s = ?, dT_PRESSAO_usuario = ? WHERE cd_pressao = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, pressao.getPressaod());
			stmt.setInt(2, pressao.getPressaos());
			stmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt.setInt(4, pressao.getCdpressao());

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
	public void remover(int cdpressao) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PRESSAO WHERE CD_PRESSAO = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, cdpressao);
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
	public Pressao buscar(int id) {
		Pressao pressao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PRESSAO WHERE CD_PRESSAO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int cdpressao = rs.getInt("CD_PRESSAO");
				int pressaod = rs.getInt("pressao_d");
				int pressaos = rs.getInt("pressao_s");
				java.sql.Date pressaodata = rs.getDate("dT_PRESSAO_usuario");
				
				pressao = new Pressao(cdpressao, pressaod, pressaos, pressaodata);
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
		return pressao;
	}
	@Override
	public List<Pressao> listar() {
		List<Pressao> lista = new ArrayList<Pressao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PRESSAO ORDER BY DT_PRESSAO_USUARIO ASC");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int cdpressao = rs.getInt("CD_PRESSAO");
				int pressaod = rs.getInt("pressao_d");
				int pressaos = rs.getInt("pressao_s");
				java.sql.Date datapressao = rs.getDate("DT_PRESSAO_USUARIO");
				
				Pressao pressao = new Pressao(cdpressao, pressaod, pressaos, datapressao);
				lista.add(pressao);
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
