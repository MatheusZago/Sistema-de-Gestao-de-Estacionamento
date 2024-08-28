package model.dao;

import java.time.LocalDateTime;

import model.entities.Vehicle;

public interface ParkedDao {
	
	void insert(Vehicle vehicle, LocalDateTime date, int num);

}
