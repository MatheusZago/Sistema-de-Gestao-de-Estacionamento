package model.dao;

import model.entities.Vehicle;

//Interface used to keep the necessary methods for other types of DB connections
//It is only used by JBDC in this project,
public interface EnrolleesDao {
	
	void insert(Vehicle vehicle);
	Vehicle FindEnrolleesByPlate(String plate);
	boolean isEnrolleed(String plate);
	
}
