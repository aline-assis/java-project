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
import fiap.com.br.dao.PesoDAO;

public class OraclePesoDAO implements PesoDAO {
	private Connection conexao;
	public void cadastrar(Peso peso, int id) throws DBException {
        PreparedStatement stmt = null;

        // Objeto que manterá o valor das duas PKs
        BigDecimal nextVal = BigDecimal.ZERO;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            // Consulta sequence para obter próximo valor
            String sql0 = "select CD_PESO_SEQ.NEXTVAL from T_PESO";
            PreparedStatement estrutura;

            estrutura = conexao.prepareStatement(sql0);
            ResultSet rs = estrutura.executeQuery();

            while (rs.next()) {
                nextVal = rs.getBigDecimal(1);
            }
            estrutura.close(); 

            String sql = "INSERT INTO T_PESO (T_USUARIO_id_usuario, cd_peso, peso_usuario, peso_ideal, dT_PESO_usuario) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id); // Seta o valor do ID do usuário
            stmt.setBigDecimal(2, nextVal); // Seta o valor obtido na consulta a sequence
            stmt.setInt(3, peso.getPesousuario());
            stmt.setInt(4, peso.getPesoideal());
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
	public void atualizar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PESO SET peso_usuario = ?, peso_ideal = ?, dT_PESO_usuario = ? WHERE cd_peso = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, peso.getPesousuario());
			stmt.setInt(2, peso.getPesoideal());
			stmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt.setInt(4, peso.getCdpeso());

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
	public void remover(int cdpeso) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PESO WHERE CD_PESO = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, cdpeso);
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
	public Peso buscar(int id) {
		Peso peso = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO WHERE CD_PESO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int cdpeso = rs.getInt("CD_PESO");
				int pesousuario = rs.getInt("peso_usuario");
				int pesoideal = rs.getInt("peso_ideal");
				java.sql.Date datapeso = rs.getDate("dT_PESO_usuario");
				
				peso = new Peso(cdpeso, pesousuario, pesoideal, datapeso);
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
		return peso;
	}
	@Override
	public List<Peso> listar() {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO ORDER BY DT_PESO_USUARIO ASC");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int cdpeso = rs.getInt("CD_PESO");
				int pesousuario = rs.getInt("peso_usuario");
				int pesoideal = rs.getInt("peso_ideal");
				java.sql.Date datapeso = rs.getDate("DT_PESO_USUARIO");
				//Calendar dataFabricacao = Calendar.getInstance();
				//dataFabricacao.setTimeInMillis(datapeso.getTime());
				
				Peso peso = new Peso(cdpeso, pesousuario, pesoideal, datapeso);
				lista.add(peso);
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
