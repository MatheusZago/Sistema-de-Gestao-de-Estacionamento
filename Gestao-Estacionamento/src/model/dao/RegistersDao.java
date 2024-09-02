package model.dao;

import java.sql.Timestamp;
import model.entities.Register;
//Interface used to keep the necessary methods for other types of DB connections
//It is only used by JBDC in this project,
public interface RegistersDao {

	void insert(Timestamp dateOfEntry, int vehicleId);
	void update(Timestamp dateOfExit, int vehicleId);
	Register findRegisterByVehicleId(int vehicleId);

}
