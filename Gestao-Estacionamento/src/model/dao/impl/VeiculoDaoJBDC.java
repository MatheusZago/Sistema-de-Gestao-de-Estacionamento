package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.VeiculoDao;
import model.entities.CaminhaoEntrega;
import model.entities.Mensalista;
import model.entities.Veiculo;
import model.enums.TipoVeiculo;

//Usado para Implementar o acesso de dados comJBDC para Veiculos
public class VeiculoDaoJBDC implements VeiculoDao {

	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	// Construtor pra pegar a conexão.
	public VeiculoDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	// Para inserir
	@Override
	public void insert(Veiculo veiculo) {
		try {
			st = conn.prepareStatement("INSERT INTO cadastrados (placa, categoria) VALUES (?, ?); ");

			st.setString(1, veiculo.getPlaca());
			st.setString(2, veiculo.getModelo().name()); // Esse .name ta transformando o enum em String

			st.executeUpdate();

			System.out.println("Veiculo cadastrado com sucesso!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	// USAR ISSO PRA VER SE EXISTE NA TABELA CADASTRADA
//	@Override Ajeitar Override dps
	public Veiculo FindCadastroByPlaca(String placa) {
		try {
			st = conn.prepareStatement("SELECT * FROM cadastrados WHERE placa = ?");

			st.setString(1, placa);
			rs = st.executeQuery();

			// Transformando o resultSet em objeto java
			if (rs.next()) {

				String placaRetornada = rs.getString("placa");
				String categoriaString = rs.getString("categoria");
//				//Transformando a String em Enum
				TipoVeiculo categoriaRetornada = TipoVeiculo.valueOf(categoriaString.toUpperCase());

				Veiculo cadastrado;

				// De acordo com a categoria retornada ele devolve um objeto diferente
				if (categoriaRetornada == TipoVeiculo.CAMINHAO) {
					cadastrado = new CaminhaoEntrega(placaRetornada, categoriaRetornada);
					return cadastrado;
				} else if (categoriaRetornada == TipoVeiculo.CARRO || categoriaRetornada == TipoVeiculo.MOTO) {
					cadastrado = new Mensalista(placaRetornada, categoriaRetornada);
					return cadastrado;
				} else {
					return null;
				}

			}

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

		return null;
	}

}
