package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

//Classe para representar o Caminhão que entrega, que implementa veiculos e herda de veiculos cadastrados
public class DeliveryTruck extends Vehicle implements EnrolleedVehicle{


	public DeliveryTruck(String plate, VehicleCategory category) {
		super(plate, category);
		super.setSize(4);
	}
	
	public DeliveryTruck(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		super.setSize(4);
	}

	@Override
	public void enter(Vehicle vehicle, Timestamp entryTimeStamp) {
		super.enter(vehicle, entryTimeStamp);
		//Pegando isso estatico da superclass
		accessRegister.insert(entryTimeStamp, vehicle.getId());		
		System.out.println("Chamou o do Caminhão Vehicle");
		
	}
	
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
	public double charge(int vehicleId) {
		
		System.out.println("Delivery trucks are not charged.");
		
		return 0;
	}


	

//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

}
