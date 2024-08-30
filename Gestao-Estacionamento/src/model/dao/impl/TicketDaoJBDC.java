package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DbException;
import model.dao.TicketDao;
import model.entities.Ticket;

public class TicketDaoJBDC implements TicketDao{
	
	Connection conn = null;
	PreparedStatement st = null;

	public TicketDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(String plate, Timestamp entryTime, int entryBarrier, int slotNumber) {
		try {
			st = conn.prepareStatement("INSERT INTO tickets (plate, entryTime, entryBarrier, slotNumber) "
					+ "VALUES (?, ?, ?, ?);");
			
			st.setString(1, plate);
			st.setTimestamp(2, entryTime);
			st.setInt(3, entryBarrier);
			st.setInt(4, slotNumber);
			
			st.executeUpdate();
			System.out.println("Insert de Ticket feito com sucesso.");
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void updateTicket(Timestamp exitTime, int exitBarrier, double amountDue, String plate ) {
		try {
			st = conn.prepareStatement("UPDATE tickets SET exitTime = ?, exitBarrier = ?, amountDue = ? "
					+ "WHERE plate = ?;");
			
			st.setTimestamp(1, exitTime);
			st.setInt(2, exitBarrier);
			st.setDouble(3, amountDue);
			st.setString(4, plate);
			
			st.executeUpdate();
			System.out.println("Update de ticket feito com sucesso.");
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	public Ticket instantiateTicket() {
		
		
		
		return null;
		
	}

}
