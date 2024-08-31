package model.entities;

import java.sql.Timestamp;

import model.dao.DaoFactory;

//Classe abstrata para os Veiculos Cadastrados, implementa Veiculo e adiciona metos que todos terão.
public interface EnrolleedVehicle {
	
	//Dps vê se é possível
	public default void register(Vehicle vehicle) {
		DaoFactory.createEnrolleesDaoJBDC().insert(vehicle);
	}

	void enter(Vehicle vehicle, Timestamp entryTimeStamp);

	double charge(int vehicleId);

	void exit(Vehicle vehicle, Timestamp exitTimeStamp);
	
	
}
