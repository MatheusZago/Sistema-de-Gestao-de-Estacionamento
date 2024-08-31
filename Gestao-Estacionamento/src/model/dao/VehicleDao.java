package model.dao;

import model.entities.Vehicle;

//Para usar os eiculos que estão no estacionamento.
public interface VehicleDao {

	void insert(Vehicle vehicle);
	Vehicle findVehicleByPlate(String plate);

}
