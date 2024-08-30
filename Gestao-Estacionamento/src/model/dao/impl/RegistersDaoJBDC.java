package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DbException;
import model.dao.RegistersDao;

public class RegistersDaoJBDC implements RegistersDao{
	
	Connection conn = null;
	PreparedStatement st = null;
	
	public RegistersDaoJBDC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Timestamp dateOfEntry, String vehiclePlate) {
		try {
			st = conn.prepareStatement("INSERT INTO registers (dateOfEntry, vehiclePlate)"
					+ "VALUES (?, ?);" );
			
			st.setTimestamp(1, dateOfEntry);
			st.setString(2, vehiclePlate);
			
			st.executeUpdate();
			
			System.out.println("Insert em register feito com sucesso.");
			
			
		}catch(SQLException e ) {
			throw new DbException("Error: " + e.getMessage());
		}
	}
	
	@Override
	public void update(Timestamp dateOfExit, String vehiclePlate) {
		try {
			st = conn.prepareStatement("UPDATE registers SET dateOfExit = ? WHERE vehiclePlate = ?;");
			
			st.setTimestamp(1, dateOfExit);
			st.setString(2, vehiclePlate);
			
			st.executeUpdate();
			System.out.println("Update em register feito com sucesso!");
			
		}catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
	}
	

}
