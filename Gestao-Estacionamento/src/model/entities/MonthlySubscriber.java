package model.entities;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.enums.VehicleCategory;

//Classe para representar os mensalistas, que implmeneta veiculos e herda de Veiculos Cadastrados
public class MonthlySubscriber extends Vehicle implements RegisteredVehicle{
	
	Scanner sc = new Scanner(System.in);

	public MonthlySubscriber(String plate) {
		super(plate);
		if(super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if(super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
		
	}

	public MonthlySubscriber(String plate, VehicleCategory category) {
		super(plate, category);
		System.out.println("Construtor Mensalista");
		if(super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if(super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}
	
	public MonthlySubscriber(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		if(super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if(super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}



	@Override
	public void enter(Vehicle vehicule, LocalDateTime dateTime) {
		super.enter(vehicule, dateTime);
		
		System.out.println("Chamou o da Monthly Subscriber Vehicle");
		
	}


	
	
	
	
//	@Override
//	public void exit(Vehicle veiculo,) {
//		// TODO Auto-generated method stub
//		
//	}


	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
//	@Override
//	public void cadastrar(Veiculo veiculo) {
//		DaoFactory.criarVeiculoDao().insert(veiculo);
//		
//	}







}
