package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	//Classe para criar conexão com banco de dados e fechar essas conexões.
	private static Connection conn = null;
	
	//Criando conexão com o banco de dados
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				
			} catch(SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
		return conn;
	}
	
	//Para carregar as informações do db.properties
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch(IOException e) {
			throw new DbException("Error: " + e.getMessage()); 
		}
	}
	
	//Metodos usados para fechar conexões, são feitos aqui para que tenham try catch imbutido
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
	}

}
