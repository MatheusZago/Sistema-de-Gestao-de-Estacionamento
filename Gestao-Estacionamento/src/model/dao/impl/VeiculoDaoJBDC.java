package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DbException;
import model.dao.VeiculoDao;
import model.entities.VeiculoCadastrado;

//Usado para Implementar o acesso de dados comJBDC para Veiculos
public class VeiculoDaoJBDC implements VeiculoDao{
	
	private Connection conn = null;
	
	//Construtor pra pegar a conexão.
	public VeiculoDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Para inserir 
	@Override
	public void insert(VeiculoCadastrado veiculo) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO cadastrados (placa, categoria) VALUES (?, ?); ");
			
			st.setString(1, veiculo.getPlaca());
			st.setString(2, veiculo.getModelo().name()); //Esse .name ta transformando o enum em String
			
			st.executeUpdate();
			
			System.out.println("Veiculo cadastrado com sucesso!");
			
		}catch(SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}

}
