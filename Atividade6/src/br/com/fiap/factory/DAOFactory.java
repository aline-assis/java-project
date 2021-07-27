package br.com.fiap.factory;

import br.com.fiap.dao.impl.OraclePesoDAO;
import br.com.fiap.dao.impl.OraclePressaoDAO;
import br.com.fiap.dao.impl.OracleSportsDAO;
import br.com.fiap.dao.impl.OracleUsuarioDAO;
import br.com.fiap.dao.impl.OracleChartsDAO;
import fiap.com.br.dao.PesoDAO;
import fiap.com.br.dao.PressaoDAO;
import fiap.com.br.dao.SportsDAO;
import fiap.com.br.dao.UsuarioDAO;
import fiap.com.br.dao.ChartsDAO;
public class DAOFactory {
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static PressaoDAO getPressaoDAO() {
		return new OraclePressaoDAO();
	}
	
	public static SportsDAO getSportsDAO() {
		return new OracleSportsDAO();
	}

	public static ChartsDAO getChartsDAO() {
		return new OracleChartsDAO();
	}
}
