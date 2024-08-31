package model.entities;

import java.sql.Timestamp;
import java.util.Scanner;

import model.enums.VehicleCategory;

//Classe para representar os mensalistas, que implmeneta veiculos e herda de Veiculos Cadastrados
public class MonthlySubscriber extends Vehicle implements EnrolleedVehicle {

	Scanner sc = new Scanner(System.in);

	public MonthlySubscriber(String plate) {
		super(plate);
		if (super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if (super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}

	}

	public MonthlySubscriber(String plate, VehicleCategory category) {
		super(plate, category);
		if (super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if (super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}

	public MonthlySubscriber(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		if (super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if (super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}

	@Override
	public void enter(Vehicle vehicle, Timestamp dateTime) {
		super.enter(vehicle, dateTime);

		accessRegister.insert(dateTime, vehicle.getId());

		System.out.println("Chamou o da Monthly Subscriber Vehicle");

	}

	@Override
	public void exit(Vehicle vehicle, Timestamp time) {
		super.exit(vehicle, time);
		String formattedAmountDue = String.format("%.2f", charge(vehicle.getId()));
		
		
		accessRegister.update(time, vehicle.getId());
		
		Register register = accessRegister.findRegisterByVehicleId(vehicle.getId());
		System.out.println("Here is the register of the visit: ");
		System.out.println(register.printRegisterExit()); 
		System.out.println("Free of charge because of monthly subscribed plan worth: R$" + formattedAmountDue);

	}

	@Override
	// Taxa mensal de 250 o mês.
	public double charge(int vehicleId) {
		return 250.00;
	}



}
