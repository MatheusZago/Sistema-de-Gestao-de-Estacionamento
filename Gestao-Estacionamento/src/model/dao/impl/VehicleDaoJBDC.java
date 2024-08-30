package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.VehicleDao;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.PublicService;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

public class VehicleDaoJBDC implements VehicleDao {

	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public VehicleDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vehicle vehicle) {
		try {
			st = conn.prepareStatement("INSERT INTO vehicles (plate, category) VALUES (?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setString(2, vehicle.getCategory().name());

			st.executeUpdate();

			System.out.println("Vehicle created with success!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	// USAR ISSO PRA VER SE EXISTE NA TABELA CADASTRADA
	@Override
	public Vehicle findVehicleByPlate(String plate) {
		try {
			st = conn.prepareStatement("SELECT * FROM vehicles WHERE plate = ?");

			st.setString(1, plate);
			rs = st.executeQuery();

			// Transformando o resultSet em objeto java
			if (rs.next()) {

				int id = rs.getInt("id");
				String returnedPlate = rs.getString("plate");
				String stringCategory = rs.getString("category");
//					//Transformando a String em Enum
				VehicleCategory returnedCategory = VehicleCategory.valueOf(stringCategory.toUpperCase());

				Vehicle registered;

				// De acordo com a categoria retornada ele devolve um objeto diferente
				if (returnedCategory == VehicleCategory.TRUCK) {
					registered = new DeliveryTruck(id, returnedPlate, returnedCategory);
					return registered;
				} else if(returnedCategory == VehicleCategory.PUBLIC) {
					registered = new PublicService(id, returnedPlate, returnedCategory);
					return registered;
				} else if (returnedCategory == VehicleCategory.CAR || returnedCategory == VehicleCategory.MOTORCYCLE) {
					registered = new MonthlySubscriber(id, returnedPlate, returnedCategory);
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

	public void deleteVehicle(int id) {
		System.out.println("id: " + id);
		
		try {
			st = conn.prepareStatement("DELETE FROM vehicles WHERE id = ?;");
			st.setInt(1, id);

			st.executeUpdate();

			System.out.println("Veiculo deletado com sucesso.");
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

}
