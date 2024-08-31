package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DbException;
import model.dao.TicketDao;
import model.entities.Ticket;

public class TicketDaoJBDC implements TicketDao {

	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public TicketDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(int vehicleId, String plate, Timestamp entryTime, int entryBarrier, String numberValue) {
		try {
			st = conn.prepareStatement("INSERT INTO tickets ("
					+ "vehicleId, plate, entryTime, entryBarrier, slotNumber) " + "VALUES (?, ?, ?, ?, ?);");
			st.setInt(1, vehicleId);
			st.setString(2, plate);
			st.setTimestamp(3, entryTime);
			st.setInt(4, entryBarrier);
			st.setString(5, numberValue);

			st.executeUpdate();
			System.out.println("Insert de Ticket feito com sucesso.");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void updateTicket(Timestamp exitTime, int exitBarrier, double amountDue, int vehicleId) {
		try {
			st = conn.prepareStatement(
					"UPDATE tickets SET exitTime = ?, exitBarrier = ?, amountDue = ? " + "WHERE vehicleId = ?;");

			st.setTimestamp(1, exitTime);
			st.setInt(2, exitBarrier);
			st.setDouble(3, amountDue);
			st.setInt(4, vehicleId);

			st.executeUpdate();
			System.out.println("Update de ticket feito com sucesso.");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	public Ticket findEntryTicketByVehicleId(int vehicleId) {

		int id = 0;
		int vehicleIdReturned = 0;
		String plate = null;
		Timestamp timeOfEntry = null;
		int entryBarrier = 0;
		String slotNumber = null;

		try {
			st = conn.prepareStatement("SELECT * FROM tickets WHERE vehicleId = ?");
			st.setInt(1, vehicleId);

			rs = st.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				vehicleIdReturned = rs.getInt("vehicleId");
				plate = rs.getString("plate");
				timeOfEntry = rs.getTimestamp("entryTime");
				entryBarrier = rs.getInt("entryBarrier");
				slotNumber = rs.getString("slotNumber");
			}

			return new Ticket(id, vehicleIdReturned, plate, timeOfEntry, entryBarrier, slotNumber);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}
	
	public Ticket findExitTicketByVehicleId(int vehicleId) {
		int id = 0;
		int vehicleIdReturned = 0;
		String plate = null;
		Timestamp timeOfEntry = null;
		Timestamp timeOfExit = null;
		int entryBarrier = 0;
		int exitBarrier = 0;
		String slotNumber = null;
		double amountDue = 0.0;
		
		try {
			st = conn.prepareStatement("SELECT * FROM tickets WHERE vehicleId = ?");
			st.setInt(1, vehicleId);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
				vehicleIdReturned = rs.getInt("vehicleId");
				plate = rs.getString("plate");
				timeOfEntry = rs.getTimestamp("entryTime");
				timeOfExit = rs.getTimestamp("exitTime");
				entryBarrier = rs.getInt("entryBarrier");
				exitBarrier = rs.getInt("exitBarrier");
				slotNumber = rs.getString("slotNumber");
				amountDue = rs.getDouble("amountDue");
			}		
			
			return new Ticket(id, vehicleIdReturned, plate, 
					timeOfEntry, timeOfExit, entryBarrier, exitBarrier, slotNumber, amountDue);
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

}
