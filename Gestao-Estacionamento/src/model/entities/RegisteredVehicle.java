package model.entities;

import model.dao.DaoFactory;

//Classe abstrata para os Veiculos Cadastrados, implementa Veiculo e adiciona metos que todos terão.
public interface RegisteredVehicle {
	
	//Dps vê se é possível
	public default void register(Vehicle vehicle) {
		DaoFactory.createVehicleDao().insert(vehicle);
	}
	
	
}
