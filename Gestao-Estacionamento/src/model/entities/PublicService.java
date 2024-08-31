package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Classe para implementar Veiculos de Serviço Publico, que não são cadastrados.
public class PublicService extends Vehicle {


	public PublicService(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(0);
	}
	
	public PublicService(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(0);
	}

	@Override
	public void enter(Vehicle vehicle, Timestamp entryTimeStamp) {
		super.enter(vehicle, entryTimeStamp);
		
		//Ta pegando estaic da super class
		accessRegister.insert(entryTimeStamp, vehicle.getId());;
		
		System.out.println("Chamou o da Public Service Vehicle");
		
	}
//
	@Override
	public void exit(Vehicle vehicle, Timestamp exitTimeStamp) {
		super.exit(vehicle, exitTimeStamp);
		String formattedAmountDue = String.format("%.2f", charge(vehicle.getId()));
		
		accessRegister.update(exitTimeStamp, vehicle.getId());
		
		Register register = accessRegister.findRegisterByVehicleId(vehicle.getId());
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
