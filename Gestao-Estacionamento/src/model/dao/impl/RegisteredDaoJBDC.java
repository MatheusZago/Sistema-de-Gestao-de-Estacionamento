package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.RegisteredDao;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

//Usado para Implementar o acesso de dados comJBDC para Veiculos
public class RegisteredDaoJBDC implements RegisteredDao {

	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	// Construtor pra pegar a conexão.
	public RegisteredDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	// Para inserir
	@Override
	public void insert(Vehicle vehicle) {
		try {
			st = conn.prepareStatement("INSERT INTO registered (plate, category) VALUES (?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setString(2, vehicle.getCategory().name()); // Esse .name ta transformando o enum em String

			st.executeUpdate();

			System.out.println("Vehicle registered with success!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	// USAR ISSO PRA VER SE EXISTE NA TABELA CADASTRADA
	@Override
	public Vehicle FindRegisteredByPlate(String plate) {
		try {
			st = conn.prepareStatement("SELECT * FROM registered WHERE plate = ?");

			st.setString(1, plate);
			rs = st.executeQuery();

			// Transformando o resultSet em objeto java
			if (rs.next()) {

				int returnedId = rs.getInt("id");
				String returnedPlate = rs.getString("plate");
				String stringCategory = rs.getString("category");
//				//Transformando a String em Enum
				VehicleCategory returnedCategory = VehicleCategory.valueOf(stringCategory.toUpperCase());

				Vehicle registered;

				// De acordo com a categoria retornada ele devolve um objeto diferente
				if (returnedCategory == VehicleCategory.TRUCK) {
					registered = new DeliveryTruck(returnedId, returnedPlate, returnedCategory);
					return registered;
				} else if (returnedCategory == VehicleCategory.CAR || returnedCategory == VehicleCategory.MOTORCYCLE) {
					registered = new MonthlySubscriber(returnedId, returnedPlate, returnedCategory);
					return registered;
				} else {
					return null;
				}

			}

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

		return null;
	}
	
	public boolean isRegistered(String plate) {
		Vehicle vehicle = DaoFactory.createRegisteredDaoJBDC().FindRegisteredByPlate(plate);
		
		if(vehicle != null) {
			return true;
		} else {
			return false;
		}
		
		
	}

}
