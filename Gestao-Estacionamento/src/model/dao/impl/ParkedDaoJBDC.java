package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import db.DbException;
import model.dao.ParkedDao;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

public class ParkedDaoJBDC implements ParkedDao{
		
	Connection conn = null;
	PreparedStatement st = null; 
	VehicleCategory category;
	
		

	public ParkedDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vehicle vehicle, LocalDateTime date, int num) {
		try {
			
//			switch (vehicle.getCategory().name()) {
//			case "CAR": {
//				System.out.println("o peso é 2");
//				
//				break;
//			} case "MOTORCYCLE": {
//				System.out.println("O peso é 1");
//			} case "TRUCK": {
//				System.out.println("O peso é 4");
//			} case "PUBLIC": {
//				System.out.println("O peso é 0");
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + vehicle.getCategory());
//			}
			
			//Transformando date em timestamp para o banco de dados
			Timestamp timeStamp = Timestamp.valueOf(date);
			
			st = conn.prepareStatement("INSERT INTO parked (plate, dateOfEntry, numberSlot, category) VALUES (?, ?, ?, ?); ");
			
			st.setString(1, vehicle.getPlate());
			st.setTimestamp(2, timeStamp);
			st.setInt(3, num);
			st.setString(4, vehicle.getCategory().name() );
			
			st.executeUpdate();
			System.out.println("Insert in Parked worked!");
			
		}catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}

}
