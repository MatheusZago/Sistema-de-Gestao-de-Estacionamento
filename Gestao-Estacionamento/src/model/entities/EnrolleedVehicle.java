package model.entities;

import java.sql.Timestamp;

import model.dao.DaoFactory;

//Interface for EnrolledVehicle, for all the enrolledVehicles to be different and have the method register as default
public interface EnrolleedVehicle {
	
	//Dps vê se é possível
	public default void register(Vehicle vehicle) {
		DaoFactory.createEnrolleesDaoJBDC().insert(vehicle);
	}

	void enter(Vehicle vehicle, Timestamp entryTimeStamp);
	double charge(int vehicleId);
	void exit(Vehicle vehicle, Timestamp exitTimeStamp);
	
	
}
