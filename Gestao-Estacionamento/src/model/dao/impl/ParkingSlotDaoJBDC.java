package model.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.ParkingSlotDao;
import model.entities.ParkingSlot;
import model.enums.SlotType;

//Class made to implement its superclass and adjust its methods for the desired connection,
//In this case being JBDC, this class is the Data Access Object of ParkingSlots
public class ParkingSlotDaoJBDC implements ParkingSlotDao {
	//This class has a method to create and populate a table in the BD with all the parking slots

	//Creating connection 
	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	//Constructor with connection
	public ParkingSlotDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	//Method to create parkingSlot table, it is used here since it is the most complicated table
	@Override
	public void createTable() {
		try {
			// Verificar se a tabela não existe
			if (doesTableExist(conn, "parking_slots") == false) {
				String createTableSQL = "CREATE TABLE parking_slots (" + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "type ENUM('GENERAL', 'MONTHLY_SUBSCRIBER') NOT NULL, "
						+ "occupied BOOLEAN NOT NULL DEFAULT FALSE, " 
						+ "occupiedby INT NULL, "
						+ "UNIQUE (id), " + "FOREIGN KEY (occupiedby) REFERENCES vehicles(id) " + ");";

				try (PreparedStatement st = conn.prepareStatement(createTableSQL)) {
					st.executeUpdate();
					System.out.println("Table created successfully, creating slots...");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error creating table.");
				}
				
				fillSlots();

			} else {
				System.out.println("Table already exists, drop it to create a new one.");

			}

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	//Simple method to verify if the table existis
	@Override
	public boolean doesTableExist(Connection conn, String tableName) throws SQLException {
		DatabaseMetaData metaData = conn.getMetaData();
		try (var rs = metaData.getTables(null, null, tableName, null)) {
			return rs.next(); 
		}
	}

	//A method that returns all the slots according with the boolean parameter
	//if occuppied = false the slot is free, if it is true then its occupied
	@Override
	public List<ParkingSlot> findByOccupied(Boolean occupied) {
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		try {

			st = conn.prepareStatement("SELECT * FROM parking_slots WHERE occupied = ?");

			st.setBoolean(1, occupied);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");
				SlotType slotType = SlotType.valueOf(type);
				boolean isOccupied = rs.getBoolean("occupied");
				int occupiedById = rs.getInt("occupiedby");

				ParkingSlot slot;
				
				//OccupiedById can be null, if that is the case it creates like this
				if (occupiedById != 0) {
					slot = new ParkingSlot(id, slotType, isOccupied, occupiedById);
				} else {
					slot = new ParkingSlot(id, slotType, isOccupied, 0);
				}
				//Adding the slot generated in the list 
				parkingSlots.add(slot);
			}
			
			//It prints every single availible slts
//			parkingSlots.forEach(System.out::println);
			return parkingSlots;

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	//Similar to findByOccupied, but this only returns if the type is GENERAL
	//This is used when the vehicle is not a monthly subscriber
	@Override
	public List<ParkingSlot> findByOccupiedGeneral(Boolean occupied) {
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		try {

			st = conn.prepareStatement(
					"SELECT * FROM parking_slots WHERE occupied = ? AND type = 'GENERAL';");

			st.setBoolean(1, occupied);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");
				SlotType slotType = SlotType.valueOf(type);
				boolean isOccupied = rs.getBoolean("occupied");
				Integer occupiedById = rs.getObject("occupiedby", Integer.class);

				ParkingSlot slot;

				if (occupiedById != null) {
					slot = new ParkingSlot(id, slotType, isOccupied, occupiedById);
				} else {
					slot = new ParkingSlot(id, slotType, isOccupied, 0);
				}
				parkingSlots.add(slot);
			}

//			parkingSlots.forEach(System.out::println);
			return parkingSlots;

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}


	}

	//Method used to find a specif parking slot according to its id
	@Override
	public ParkingSlot findParkingSlotById(int id) {

		int returnedId = 0;
		String type = null;
		boolean occupied = false;
		int occubiedById = 0;
		try {

			st = conn.prepareStatement("SELECT * FROM parking_slots WHERE id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {

				returnedId = rs.getInt("id");
				type = rs.getString("type");
				occupied = rs.getBoolean("occupied");
				occubiedById = rs.getInt("occupiedby");
			}

			SlotType returnedType = SlotType.valueOf(type);

			return new ParkingSlot(returnedId, returnedType, occupied, occubiedById);

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	//Method used to update a slot and make it occupied
	@Override
	public void occupieSlot(int id, int vehicleId) {
		try {
			st = conn.prepareStatement("UPDATE parking_slots SET occupied = TRUE, " + " occupiedby = ?  WHERE id = ?;");
			st.setInt(1, vehicleId);
			st.setInt(2, id);

			st.executeUpdate();
//			System.out.println("Update worked");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
	}

	//Method used to update a slot and make it free
	@Override
	public void freeSlot(int id) {
		try {
			st = conn.prepareStatement(
					"UPDATE parking_slots SET occupied = FALSE," + " occupiedby = NULL WHERE occupiedby = ?;");
			st.setInt(1, id);

			st.executeUpdate();
//			System.out.println("Update worked");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
	}

	//Method used to fill the slots on the recently created table
	@Override
	public void fillSlots() {
		try {
			String insertSQL = "INSERT INTO parking_slots (type) VALUES (?)";

			try (PreparedStatement st = conn.prepareStatement(insertSQL)) {
				for (int i = 1; i <= 500; i++) {
					if (i <= 300) {
						st.setString(1, "GENERAL");
					} else {
						st.setString(1, "MONTHLY_SUBSCRIBER");
					}
					st.addBatch(); // this add a instruction to a queue 
				}

				st.executeBatch(); // Executes all the instructions on batch
				System.out.println("Created 500 parking slots.");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error inserting slots.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error preparing statement.");
		}
	}
}
