package model.dao;

import model.entities.Vehicle;

//Interface used to keep the necessary methods for other types of DB connections
//It is only used by JBDC in this project,
public interface VehicleDao {

	void insert(Vehicle vehicle);
	Vehicle findVehicleByPlate(String plate);
	void deleteVehicle(int id);

}
