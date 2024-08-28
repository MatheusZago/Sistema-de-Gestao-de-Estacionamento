package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.entities.ParkingSlot;

//Interface para mostrar as implementação de ParkingSlot os métodos que tem de ter para mexer com a tabela
public interface ParkingSlotDao {
	
	void createTable();
	boolean doesTableExist(Connection conn, String tableName)  throws SQLException;
	public List<ParkingSlot> findByOccupied(Boolean occupied);
	

}
