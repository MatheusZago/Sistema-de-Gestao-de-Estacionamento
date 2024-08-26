package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbException;
import model.dao.VeiculoDao;
import model.entities.VeiculoCadastrado;

public class VeiculoDaoJBDC implements VeiculoDao{
	
	private Connection conn = null;
	
	//Construtor pra pegar a conexão.
	public VeiculoDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(VeiculoCadastrado veiculo) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO cadastrados (placa, categoria) VALUES (?, ?); ", Statement.RETURN_GENERATED_KEYS );
			
			st.setString(1, veiculo.getPlaca());
			st.setString(2, veiculo.getModelo().name()); //Esse .name ta transformando o enum em String
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}

}
