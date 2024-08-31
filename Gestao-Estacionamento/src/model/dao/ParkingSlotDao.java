package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.entities.ParkingSlot;

//Interface used to keep the necessary methods for other types of DB connections
//It is only used by JBDC in this project,
public interface ParkingSlotDao {
	
	void createTable();
	boolean doesTableExist(Connection conn, String tableName)  throws SQLException;
	public List<ParkingSlot> findByOccupied(Boolean occupied);
	public List<ParkingSlot> findByOccupiedGeneral(Boolean occupied);
	void freeSlot(int id);
	void occupieSlot(int id, int vehicleId);
	ParkingSlot findParkingSlotById(int id);
	void fillSlots();
	

}
