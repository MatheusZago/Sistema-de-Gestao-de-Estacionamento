package model.entities;

import java.sql.Timestamp;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.RegistersDao;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Classe para representar os mensalistas, que implmeneta veiculos e herda de Veiculos Cadastrados
public class MonthlySubscriber extends Vehicle implements EnrolleedVehicle{
	
	RegistersDao register = DaoFactory.createRegisterDaoJBDC();
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
	public void enter(Vehicle vehicle, Timestamp dateTime) {
		//Chamou a catraca, não precisa retornar ela pq o monthly nn usa
		BarrierService.validateEntryBarriers(vehicle);
		//Chamou o processo de entrada
		super.enter(vehicle, dateTime);
		

		register.insert(dateTime, vehicle.getPlate());
		
		System.out.println("Chamou o da Monthly Subscriber Vehicle");
		
	}


	
	
	
	
	@Override
	public void exit(Vehicle vehicle, Timestamp time) {
		
		BarrierService.validateExitBarriers(vehicle);
		super.exit(vehicle, time);
		register.update(time, vehicle.getPlate());
		
		System.out.println("Chamou o exit do montly subscriber");
		
		
	}


	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
//	@Override
//	public void cadastrar(Veiculo veiculo) {
//		DaoFactory.criarVeiculoDao().insert(veiculo);
//		
//	}







}
