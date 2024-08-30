package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Classe para representar o Caminhão que entrega, que implementa veiculos e herda de veiculos cadastrados
public class DeliveryTruck extends Vehicle implements EnrolleedVehicle{


	public DeliveryTruck(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(4);
	}
	
	public DeliveryTruck(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(4);
	}

	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
////	@Override
//	public void cadastrar(Veiculo veiculo) {
//		
//		DaoFactory.criarVeiculoDao().insert(veiculo);;
//		
//	}	

	@Override
	public void enter(Vehicle vehicule, Timestamp dateTime) {
		super.enter(vehicule, dateTime);
		
		System.out.println("Chamou o do Caminhão Vehicle");
		
	}

	@Override
	public double charge(int vehicleId) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

}
