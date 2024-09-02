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

//Class made to implement its superclass and adjust its methods for the desired connection,
//In this case being JBDC, this class is the Data Access Object of Vehicle
public class VehicleDaoJBDC implements VehicleDao {

	//Creating connection and variables
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	//Constructor with Connection
	public VehicleDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Method to insert a vehicle into the vehicle table
	@Override
	public void insert(Vehicle vehicle) {
		try {
			st = conn.prepareStatement("INSERT INTO vehicles (plate, category) VALUES (?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setString(2, vehicle.getCategory().name());

			st.executeUpdate();

//			System.out.println("Vehicle created with success!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	//Method to find a vehicle and instantiate it
	@Override
	public Vehicle findVehicleByPlate(String plate) {
		try {
			st = conn.prepareStatement("SELECT * FROM vehicles WHERE plate = ?");

			st.setString(1, plate);
			rs = st.executeQuery();

			//Transform result set into java object
			if (rs.next()) {

				int id = rs.getInt("id");
				String returnedPlate = rs.getString("plate");
				String stringCategory = rs.getString("category");
				VehicleCategory returnedCategory = VehicleCategory.valueOf(stringCategory.toUpperCase());

				Vehicle registered;

				//According to category it creates a different type of vehicle
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

	//Method to delete a vehicle by its id
	@Override
	public void deleteVehicle(int id) {
		
		try {
			st = conn.prepareStatement("DELETE FROM vehicles WHERE id = ?;");
			st.setInt(1, id);

			st.executeUpdate();

//			System.out.println("Veiculo deletado com sucesso.");
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

}
