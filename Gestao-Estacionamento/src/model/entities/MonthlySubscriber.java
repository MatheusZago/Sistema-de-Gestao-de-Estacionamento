package model.entities;

import java.sql.Timestamp;
import java.util.Scanner;

import model.enums.VehicleCategory;

//Entity that represents a MontlhySubscriber that implements a EnrolleedVehicle
public class MonthlySubscriber extends Vehicle implements EnrolleedVehicle {

	Scanner sc = new Scanner(System.in);

	//Using 3 constructors for diferent momens
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

	//Override of method enter of superclass to add more
	@Override
	public void enter(Vehicle vehicle, Timestamp dateTime) {
		//Using the enter method from superclass and add the last parts
		super.enter(vehicle, dateTime);

		//inserting that insert
		accessRegister.insert(dateTime, vehicle.getId());

//		System.out.println("Chamou o da Monthly Subscriber Vehicle");

	}

	//Override of exit method of superclass to add more
	@Override
	public void exit(Vehicle vehicle, Timestamp time) {
		//Using the exit method from superclass and add the exit parts
		super.exit(vehicle, time);
		
		//Receiving the value from charge and format it
		String formattedAmountDue = String.format("%.2f", charge(vehicle.getId()));
		
		//Updating the register with the new timeStamp
		accessRegister.update(time, vehicle.getId());
		
		//Creating a new Register to receive the updated one
		Register register = accessRegister.findRegisterByVehicleId(vehicle.getId());
		//Here it prints the register of the visit
		System.out.println("Here is the register of the visit: ");
		System.out.println(register.printRegisterExit()); 
		System.out.println("Free of charge because of monthly subscribed plan worth: R$" + formattedAmountDue);

	}

	@Override
	//Monthly tax of 250.
	public double charge(int vehicleId) {
		return 250.00;
	}



}
