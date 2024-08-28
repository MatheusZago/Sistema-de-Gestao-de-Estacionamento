package model.dao;

import model.entities.Vehicle;

//Interface para a implementação de possíveis modosDAO, no caso do projeto só tem JBDC mas ta aqui por boa prática
public interface VehicleDao {
	
	void insert(Vehicle vehicle);
	Vehicle FindRegisteredByPlate(String plate);
//	void update();
//	void deleteByPlaca();
	//DPS BOAAR OS OUTROS
	
}
