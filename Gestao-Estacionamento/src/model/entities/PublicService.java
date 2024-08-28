package model.entities;

import model.enums.VehicleCategory;

//Classe para implementar Veiculos de Serviço Publico, que não são cadastrados.
public class PublicService extends Vehicle {

	public PublicService(String plate, VehicleCategory category) {
		super(plate, category);
	}

//	@Override
//	public void entrar(Veiculo veiculo) {
//		DaoFactory.criarVeiculoDao();
//		
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
