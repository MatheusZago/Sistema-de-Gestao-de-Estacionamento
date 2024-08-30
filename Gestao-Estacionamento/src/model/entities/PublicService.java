package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Classe para implementar Veiculos de Serviço Publico, que não são cadastrados.
public class PublicService extends Vehicle {

	public PublicService(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(0);
	}
	
	public PublicService(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(0);
	}

	@Override
	public void enter(Vehicle vehicule, Timestamp dateTime) {
		super.enter(vehicule, dateTime);
		
		System.out.println("Chamou o da Public Service Vehicle");
		
	}
//
//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	//Não pagam cobrança
	public double charge(int vehicleId) {
		return 0;
	}

}
