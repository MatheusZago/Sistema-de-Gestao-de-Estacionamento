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

public class ParkingSlotDaoJBDC implements ParkingSlotDao {

	private Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public ParkingSlotDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void createTable() {
		try {

			// Verificar se a tabela não existe
			if (!doesTableExist(conn, "parking_spaces")) {

				String createTableSQL = "CREATE TABLE parking_spaces (" + "    id INT AUTO_INCREMENT PRIMARY KEY, "
						+ "    number INT NOT NULL, " + "    type ENUM('GENERAL', 'MONTHLY_SUBSCRIBER') NOT NULL, "
						+ "    occupied BOOLEAN NOT NULL DEFAULT FALSE, " + "    UNIQUE (number)" + ");";

				try (PreparedStatement st = conn.prepareStatement(createTableSQL)) {
					st.executeUpdate();
					System.out.println("Table created with sucess, crating slots...");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error on creating table.");
				}

			} 
			else {
//				st = conn.prepareStatement(" DROP TABLE parking_spaces;");
//				st.executeUpdate();
				System.out.println("Table already exists, drop it to create a new one.");
				
			}

			st = conn.prepareStatement("INSERT INTO parking_spaces (number, type) VALUES (?, ?)");
			for (int i = 1; i <= 500; i++) {

				if (i <= 300) {

					st.setInt(1, i);
					st.setString(2, "GENERAL");
					st.addBatch();
				} else {
					
					st.setInt(1, i);
					st.setString(2, "MONTHLY_SUBSCRIBER");
					st.addBatch(); //Vai adicionar numa fila de comando
				}

			}

			st.executeBatch(); // Executando todas as instruções de uma vez
			System.out.println("Created 500 parking slots.");

			// TODO especificar expcetion
		} catch (Exception e) {
			e.getMessage();
		}

	}

	// Função para verificar se a tabela existe
	public  boolean doesTableExist(Connection conn, String tableName) throws SQLException {
		DatabaseMetaData metaData = conn.getMetaData();
		try (var rs = metaData.getTables(null, null, tableName, null)) {
			return rs.next(); // Se o ResultSet tiver um próximo elemento, a tabela existe
		}
	}

	@Override
	public List<ParkingSlot> findByOccupied(Boolean occupied) {
		
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		
		try {
			
			st = conn.prepareStatement("SELECT id, number, type, occupied FROM parking_spaces WHERE occupied = ?");
			
			st.setBoolean(1, occupied);
			rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int number = rs.getInt("number");
				String type = rs.getString("type");
				SlotType slotType = SlotType.valueOf(type);
				boolean isOccupied = rs.getBoolean("occupied");
				
				
				ParkingSlot slot = new ParkingSlot(id, number, slotType, isOccupied);
				parkingSlots.add(slot);
			}
			
			
			
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
		parkingSlots.forEach(System.out::println);
		return parkingSlots;
		
	}
	
	@Override
	public List<ParkingSlot> findByOccupiedGeneral(Boolean occupied) {
		
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		
		try {
			
			st = conn.prepareStatement("SELECT id, number, type, occupied FROM parking_spaces WHERE occupied = ? AND type = 'GENERAL';");
			
			st.setBoolean(1, occupied);
			rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int number = rs.getInt("number");
				String type = rs.getString("type");
				SlotType slotType = SlotType.valueOf(type);
				boolean isOccupied = rs.getBoolean("occupied");
				
				
				ParkingSlot slot = new ParkingSlot(id, number, slotType, isOccupied);
				parkingSlots.add(slot);
			}
			
			
			
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		
		parkingSlots.forEach(System.out::println);
		return parkingSlots;
		
	}
	
	@Override
	public void occupieSlot(int number) {
	    try {
	        st = conn.prepareStatement("UPDATE parking_spaces SET occupied = TRUE WHERE number = ?;");
//	        st.setBoolean(1, occupied);
	        st.setInt(1, number);

	        st.executeUpdate();
	        System.out.println("Update worked");

	    } catch (SQLException e) {
	        throw new DbException("Error: " + e.getMessage());
	    }
	}
	
	public void freeSlot(int slot) {
	    try {
	        st = conn.prepareStatement("UPDATE parking_spaces SET occupied = FALSE WHERE number = ?;");
//	        st.setBoolean(1, occupied);
	        st.setInt(1, slot);

	        st.executeUpdate();
	        System.out.println("Update worked");

	    } catch (SQLException e) {
	        throw new DbException("Error: " + e.getMessage());
	    }
	}
	
	
	
	

}
