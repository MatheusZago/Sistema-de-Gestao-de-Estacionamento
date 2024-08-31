package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DbException;
import model.dao.RegistersDao;
import model.entities.Register;

//Class made to implement its superclass and adjust its methods for the desired connection,
//In this case being JBDC, this class is the Data Access Object of Register
public class RegistersDaoJBDC implements RegistersDao{
	
	//Creating connection
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	//Constructor with connection
	public RegistersDaoJBDC(Connection conn) {
		this.conn = conn;
	}
	
	//Method to insert register into the table
	@Override
	public void insert(Timestamp dateOfEntry, int vehicleId) {
		System.out.println("Chamou o insert do register");
		
		try {
			st = conn.prepareStatement("INSERT INTO registers (dateOfEntry, vehicleId)"
					+ "VALUES (?, ?);" );
			
			st.setTimestamp(1, dateOfEntry);
			st.setInt(2, vehicleId);
			
			st.executeUpdate();
			
//			System.out.println("Insert em registers feito com sucesso.");
			
			
		}catch(SQLException e ) {
			throw new DbException("Error: " + e.getMessage());
		}
	}
	
	//Method to update the register when the create leave
	@Override
	public void update(Timestamp dateOfExit, int vehicleId) {
		try {
			st = conn.prepareStatement("UPDATE registers SET dateOfExit = ? WHERE vehicleId = ?;");
			
			st.setTimestamp(1, dateOfExit);
			st.setInt(2, vehicleId);
			
			st.executeUpdate();
//			System.out.println("Update em register feito com sucesso!");
			
		}catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}
	
	//Method that find a register by its vehicle id
	@Override
	public Register findRegisterByVehicleId(int vehicleId) {
		 int id = 0;
		 Timestamp dateOfEntry = null;
		 Timestamp dateOfExit = null;
		 int id_vehicle = 0;
		try {
			st = conn.prepareStatement("SELECT * FROM registers WHERE vehicleId = ?;");
			
			st.setInt(1, vehicleId);

			rs = st.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
				dateOfEntry = rs.getTimestamp("dateOfEntry");
				dateOfExit = rs.getTimestamp("dateOfExit");
				id_vehicle = rs.getInt("vehicleId");
				
			}
			
			
			return new Register(id, dateOfEntry, dateOfExit, id_vehicle);
			
		}catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
		
	}
	

}
