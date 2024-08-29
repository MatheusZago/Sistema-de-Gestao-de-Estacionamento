package model.dao;

import java.time.LocalDateTime;

import model.entities.Parked;
import model.entities.Vehicle;

public interface ParkedDao {
	
	void insert(Vehicle vehicle, LocalDateTime date, int num);
	Parked findByPlate(String plate);
	void remove(Vehicle vehicle);


}
