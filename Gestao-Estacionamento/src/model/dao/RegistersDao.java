package model.dao;

import java.sql.Timestamp;

public interface RegistersDao {

	void insert(Timestamp dateOfEntry, String vehiclePlate);
	void update(Timestamp dateOfExit, String vehiclePlate);

}
