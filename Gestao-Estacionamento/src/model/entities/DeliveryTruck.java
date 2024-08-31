package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Entity that represents a DeliveryTruck that implements a EnrolleedVehicle
public class DeliveryTruck extends Vehicle implements EnrolleedVehicle{

	//It has 2 constructs for diferent type of constructions
	public DeliveryTruck(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(4);
	}
	
	public DeliveryTruck(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(4);
	}

	//Override of method enter of superclass to add the last part
	@Override
	public void enter(Vehicle vehicle, Timestamp entryTimeStamp) {
		//Using the enter method from superclass and add the last parts
		super.enter(vehicle, entryTimeStamp);
		accessRegister.insert(entryTimeStamp, vehicle.getId());		
		System.out.println("Chamou o do Caminhão Vehicle");
	}
	
	//Override of exit method of superclass to add more
	@Override
	public void exit(Vehicle vehicle, Timestamp exitTimeStamp) {
		//Using the exit method from superclass and add the exit parts
		super.exit(vehicle, exitTimeStamp);
		//Receiving the value from charge and format it
		String formattedAmountDue = String.format("%.2f", charge(vehicle.getId()));
		//Updating the register with the new timeStamp
		accessRegister.update(exitTimeStamp, vehicle.getId());
		
		//Creating a new Register to receive the updated one
		Register register = accessRegister.findRegisterByVehicleId(vehicle.getId());
		//Here it prints the register of the visit
		System.out.println("Here is the register of the visit: ");
		System.out.println(register.printRegisterExit()); 
		System.out.println("Carge of the visite: R$" + formattedAmountDue);
		
	}

	//Method to charge and return the value
	@Override
	public double charge(int vehicleId) {
		System.out.println("Delivery trucks are not charged.");
		return 0;
	}

}
