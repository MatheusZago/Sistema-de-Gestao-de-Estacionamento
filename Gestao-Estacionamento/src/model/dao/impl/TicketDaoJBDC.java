package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DbException;
import model.dao.TicketDao;
import model.entities.Ticket;

//Class made to implement its superclass and adjust its methods for the desired connection,
//In this case being JBDC, this class is the Data Access Object of Ticket
public class TicketDaoJBDC implements TicketDao {

	//Creating connection
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	//Constructor with connection
	public TicketDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Method to insert a ticket 
	@Override
	public void insert(int vehicleId, String plate, Timestamp entryTime, int entryBarrier, String numberValue) {
		System.out.println("Numbers being used: ");
		System.out.println("Vehicle id: " + vehicleId);
		System.out.println("Plate: " + plate);
		System.out.println("Time: " + entryTime);
		System.out.println("Barrier: " + entryBarrier);
		System.out.println("nSlots: " + numberValue);
		
		
		try {
			st = conn.prepareStatement("INSERT INTO tickets ("
					+ "vehicleId, plate, entryTime, entryBarrier, slotNumber) " + "VALUES (?, ?, ?, ?, ?);");
			st.setInt(1, vehicleId);
			st.setString(2, plate);
			st.setTimestamp(3, entryTime);
			st.setInt(4, entryBarrier);
			st.setString(5, numberValue);

			
			st.executeUpdate();
//			System.out.println("Insert de Ticket feito com sucesso.");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	//Method to update a ticket upon exit
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

	//Method to find a ticket by its vehicle id and construct it with the available fields at the time of entry
	@Override
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
	
	//Find a ticket by its vehicle id and constructs it with all fields
	@Override
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
