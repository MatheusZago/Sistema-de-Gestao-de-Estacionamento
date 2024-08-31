package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.EnrolleesDao;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

//Class made to implement its superclass and adjust its methods for the desired connection,
//In this case being JBDC, this class is the Data Access Object of Enrolless
public class EnrolleesDaoJBDC implements EnrolleesDao {

	//Creating connection and sql statement and resultset
	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	// Constructor to create a connection
	public EnrolleesDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Method to insert a vehicle on the enroless table
	@Override
	public void insert(Vehicle vehicle) {
		try {
			st = conn.prepareStatement("INSERT INTO enrollees (plate, category) VALUES (?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setString(2, vehicle.getCategory().name()); // Esse .name ta transformando o enum em String

			st.executeUpdate();

			System.out.println("Vehicle enrolleed with success!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	//Vehicle to find one instance of enrollees using it's plate
	@Override
	public Vehicle FindEnrolleesByPlate(String plate) {
		try {
			st = conn.prepareStatement("SELECT * FROM enrollees WHERE plate = ?");

			st.setString(1, plate);
			rs = st.executeQuery();

			//This is transfoming a query consult in a java object
			if (rs.next()) {
				int returnedId = rs.getInt("id");
				String returnedPlate = rs.getString("plate");
				String stringCategory = rs.getString("category");
				//Transfoming String in enum
				VehicleCategory returnedCategory = VehicleCategory.valueOf(stringCategory.toUpperCase());

				Vehicle registered;

				//It uses a diferent constructor according with the category 	
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
	
	//Simple method to see if a vehicle is enrolleed
	@Override
	public boolean isEnrolleed(String plate) {
		Vehicle vehicle = DaoFactory.createEnrolleesDaoJBDC().FindEnrolleesByPlate(plate);
		
		if(vehicle != null) {
			return true;
		} else {
			return false;
		}
		
		
	}

}
