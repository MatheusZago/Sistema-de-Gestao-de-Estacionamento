package model.entities;

import model.enums.VehicleCategory;

//Classe para representar o Caminhão que entrega, que implementa veiculos e herda de veiculos cadastrados
public class DeliveryTruck extends Vehicle implements RegisteredVehicle{


	public DeliveryTruck(String plate, VehicleCategory category) {
		super(plate, category);
	}

	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
////	@Override
//	public void cadastrar(Veiculo veiculo) {
//		
//		DaoFactory.criarVeiculoDao().insert(veiculo);;
//		
//	}	

//	@Override Está comentado pq o método entrar se tornou concreto de Veiculo
//	public void entrar(String placa) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

}
