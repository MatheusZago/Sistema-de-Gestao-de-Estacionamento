package model.dao;

import java.sql.Timestamp;

import model.entities.Register;

public interface RegistersDao {

	void insert(Timestamp dateOfEntry, int vehicleId);
	void update(Timestamp dateOfExit, int vehicleId);
	Register findRegisterByVehicleId(int vehicleId);

}
