package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Peso;
import br.com.fiap.singleton.ConnectionManager;
import fiap.com.br.dao.ChartsDAO;

public class OracleChartsDAO implements ChartsDAO{
	private Connection conexao;
	
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
