package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.VeiculoDao;
import model.entities.VeiculoCadastrado;
import model.enums.TipoVeiculo;

//Usado para Implementar o acesso de dados comJBDC para Veiculos
public class VeiculoDaoJBDC implements VeiculoDao{
	
	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	//Construtor pra pegar a conexão.
	public VeiculoDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Para inserir 
	@Override
	public void insert(VeiculoCadastrado veiculo) {
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

	//USAR ISSO PRA VER SE EXISTE NA TABELA CADASTRADA
	@Override
	public VeiculoCadastrado searchCadastroByPlaca(String placa) {
		try {
			System.out.println("Até aqui veio");
			st = conn.prepareStatement("SELECT * FROM cadastrados WHERE placa = '?';");
			
			st.setString(1, placa);
			rs = st.executeQuery();
			
			//Transformando o resultSet em objeto java
			if(rs.next()) {
				
				System.out.println("ENTROU!");
				String placaRetornada = rs.getString("placa");
				String categoriaString = rs.getString("categoria");
				//Transformando a String em Enum
				TipoVeiculo categoriaRetornada = TipoVeiculo.valueOf(categoriaString.toUpperCase());
				
				System.out.println("TESTE: placa) " + placaRetornada + " Categoria)" + categoriaRetornada);
				
//				VeiculoCadastrado cadastrado = new vEC
				

				
			} else {
				
				System.out.println("Não entrou");
			}
			
			
			
		} catch (SQLException e) {
			throw new DbException("Error: "  + e.getMessage());
		}
		
		
		
		
		return null;
	}

}
