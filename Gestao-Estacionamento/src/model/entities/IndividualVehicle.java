package model.entities;

import model.enums.VehicleCategory;

//Classe para representar os veiculos avulsos, não cadastrados.
public class IndividualVehicle extends Vehicle{
	
	//Ppegando construtor da classe abstrata
	public IndividualVehicle(String plate, VehicleCategory category) {
		super(plate, category);
		if(super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if(super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}


	



//	@Override
//	public void entrar(Veiculo veiculo) {
//		
//		
//	}
//
//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

}
