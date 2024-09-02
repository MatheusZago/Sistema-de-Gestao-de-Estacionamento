package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Entity that represents a PublicService 
public class PublicService extends Vehicle {

	//2 COnstructors for different uses
	public PublicService(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(0);
	}
	
	public PublicService(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(0);
	}

	//Override of method enter of superclass to add more
	@Override
	public void enter(Vehicle vehicle, Timestamp entryTimeStamp) {
		//Using the enter method from superclass and add the last parts
		super.enter(vehicle, entryTimeStamp);
		
		//Using this static from the superclass to insert
		accessRegister.insert(entryTimeStamp, vehicle.getId());;
		
//		System.out.println("Chamou o da Public Service Vehicle");
		
	}

	//Override of exit method of superclass to add more
	@Override
	public void exit(Vehicle vehicle, Timestamp exitTimeStamp) {
		//Using the exit method from superclass and add the exit parts
		super.exit(vehicle, exitTimeStamp);
		//Receiving the value from charge and format it
		String formattedAmountDue = String.format("%.2f", charge(vehicle.getId()));
		
		//Using this static from the superclass to update
		accessRegister.update(exitTimeStamp, vehicle.getId());
		
		//Creating a new Register to receive the updated one
		Register register = accessRegister.findRegisterByVehicleId(vehicle.getId());
		//Here it prints the register of the visit
		System.out.println("Here is the register of the visit: ");
		System.out.println(register.printRegisterExit()); 
		System.out.println("Carge of the visite: R$" + formattedAmountDue);

		
	}

	@Override
	//Não pagam cobrança
	public double charge(int vehicleId) {
		
		System.out.println("Public service vehicles are not charged.");
		return 0;
	}

}
