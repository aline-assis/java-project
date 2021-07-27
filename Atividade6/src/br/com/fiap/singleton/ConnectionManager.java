package br.com.fiap.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionManager {
//atributo estatico pra salvar a conexao	
	private static ConnectionManager connectionManager;
	
	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		if(connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM85236", "190597");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return connection;
		
	}

}
