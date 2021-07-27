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
import fiap.com.br.dao.SportsDAO;

public class OracleSportsDAO implements SportsDAO {
	private Connection conexao;
	public void cadastrar(Sports sports, int id) throws DBException {
        PreparedStatement stmt = null;

        // Objeto que manterá o valor das duas PKs
        BigDecimal nextVal = BigDecimal.ZERO;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            // Consulta sequence para obter próximo valor
            String sql0 = "select CD_ATIVIDADE_SEQ.NEXTVAL from T_ATIVIDADE";
            PreparedStatement estrutura;

            estrutura = conexao.prepareStatement(sql0);
            ResultSet rs = estrutura.executeQuery();

            while (rs.next()) {
                nextVal = rs.getBigDecimal(1);
            }
            estrutura.close(); 

            String sql = "INSERT INTO T_ATIVIDADE (cd_atividade, ds_atividade, dt_atividade, ds_caloria_gasta_hr, T_USUARIO_id_usuario ) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setBigDecimal(1, nextVal); // Seta o valor obtido na consulta a sequence
            stmt.setString(2, sports.getDsatividade());
            stmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            stmt.setInt(4, sports.getCaloriagasta());
            stmt.setInt(5, id); // Seta o valor do ID do usuário

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
	public void atualizar(Sports sports) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_ATIVIDADE SET ds_atividade = ?, dt_atividade = ?, ds_caloria_gasta_hr = ? WHERE cd_atividade = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, sports.getDsatividade());
			stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt.setInt(3, sports.getCaloriagasta());
			stmt.setInt(4, sports.getCdatividade());

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
	public void remover(int cdatividade) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_ATIVIDADE WHERE CD_ATIVIDADE = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, cdatividade);
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
	public Sports buscar(int id) {
		Sports sports = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_ATIVIDADE WHERE CD_ATIVIDADE = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int cdatividade = rs.getInt("CD_ATIVIDADE");
				String dsatividade = rs.getString("ds_atividade");
				java.sql.Date dtatividade = rs.getDate("dt_atividade");
				int caloriagasta = rs.getInt("ds_caloria_gasta_hr");
				
				sports = new Sports(cdatividade, dsatividade, dtatividade, caloriagasta);
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
		return sports;
	}
	@Override
	public List<Sports> listar() {
		List<Sports> lista = new ArrayList<Sports>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_ATIVIDADE ORDER BY DT_ATIVIDADE ASC");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int cdatividade = rs.getInt("CD_ATIVIDADE");
				String dsatividade = rs.getString("ds_atividade");
				java.sql.Date dtatividade = rs.getDate("DT_ATIVIDADE");
				int caloriagasta = rs.getInt("ds_caloria_gasta_hr");
				Sports sports = new Sports(cdatividade, dsatividade, dtatividade, caloriagasta);
				lista.add(sports);
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
