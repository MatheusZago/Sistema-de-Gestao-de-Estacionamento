package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import db.DbException;
import model.dao.ParkedDao;
import model.entities.Parked;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

public class ParkedDaoJBDC implements ParkedDao {

	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	VehicleCategory category;

	public ParkedDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vehicle vehicle, LocalDateTime date, int num) {
		try {

			// Transformando date em timestamp para o banco de dados
			Timestamp timeStamp = Timestamp.valueOf(date);

			st = conn.prepareStatement(
					"INSERT INTO parked (plate, dateOfEntry, numberSlot, category) VALUES (?, ?, ?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setTimestamp(2, timeStamp);
			st.setInt(3, num);
			st.setString(4, vehicle.getCategory().name());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	// TODO adicionar loop paa repetirteste sem qubrar
	@Override
	public Parked findByPlate(String plate) {
		try {

			st = conn.prepareStatement("SELECT * FROM parked WHERE plate = ?;");
			st.setString(1, plate);

			rs = st.executeQuery();
			String plateVehicle = null;
			Timestamp dateTime = null;
			int numberSlot = 0;
			String category = null;

			// Se ele tiver um resultado
			if (rs.next()) {
				plateVehicle = rs.getString("plate");
				dateTime = rs.getTimestamp("dateOfEntry");
				numberSlot = rs.getInt("numberSlot");
				category = rs.getString("category");
			}

			VehicleCategory vehicleCategory = VehicleCategory.valueOf(category);

			// Posso usar um achar um por placa dos cadastrados, se ele tiver um ele cria
			// como se fosse cadastrado
			// Se não cria avulso ou público

//			Vehicle vehicle = new MonthlySubscriber(plateVehicle, vehicleCategory);
//			System.out.println(vehicle + " " + dateTime + " "  + numberSlot);
//			
//			return vehicle;

			Parked parkedVehicle = new Parked(plateVehicle, dateTime, numberSlot, vehicleCategory);

//			System.out.println(parkedVehicle);

			return parkedVehicle;

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	@Override
	public void remove(Vehicle vehicle) {
		try {
			
			st = conn.prepareStatement("DELETE FROM parked WHERE plate = ?;");
			st.setString(1, vehicle.getPlate());
			
			st.executeUpdate();
			System.out.println("Delete feito com sucesso!");
			
		}catch(SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}

	
}
