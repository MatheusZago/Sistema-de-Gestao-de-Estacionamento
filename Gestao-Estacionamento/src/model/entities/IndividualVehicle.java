package model.entities;

import java.time.LocalDateTime;

import model.dao.impl.TicketDaoJBDC;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Classe para representar os veiculos avulsos, não cadastrados.
public class IndividualVehicle extends Vehicle {
	
	TicketDaoJBDC ticketDao;

	// Ppegando construtor da classe abstrata
	public IndividualVehicle( String plate, VehicleCategory category) {
		super( plate, category);
		
		if (super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if (super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}

	public IndividualVehicle(int id, String plate, VehicleCategory category) {
		super(id, plate, category);
		
		if (super.getCategory() == VehicleCategory.CAR) {
			super.setSize(2);
		} else if (super.getCategory() == VehicleCategory.MOTORCYCLE) {
			super.setSize(1);
		}
	}

	@Override
	public void enter(Vehicle vehicle, LocalDateTime dateTime) {
		System.out.println("Enter by the barriers: ");
		int entryBarrier = BarrierService.validateEntryBarriers(vehicle);

		// Ele já colocou a vaga
		super.enter(vehicle, dateTime);

//		ParkedDaoJBDC parkedDao = DaoFactory.createParkedDaoJBDC();
//		List<Integer> chosenSlot = parkedDao.findSlotByPlate(getPlate());
		
		//Ta apenas pegando o menor e o maior número da lista
//        int minSlot = Collections.min(chosenSlot);
//        int maxSlot = Collections.max(chosenSlot);
        
//        String slotNumber = 


//		for (int i = 1; i < vehicle.getSize(); i++) {
//
//			ticketDao.insert(getPlate(), dateTime, entryBarrier, slotNumber);
//}
        
		}

	public void exit(Vehicle vehicle , LocalDateTime time) {

		System.out.println("Leaving by the barrier: ");
		int exitBarrier = BarrierService.validateExitBarriers(vehicle);

		if (vehicle.getCategory() != VehicleCategory.PUBLIC) {
			slot.freeSlot(vehicle.getId());
//			vehicle.de
//			parked.remove(vehicle);

		}
		
//		ticketDao.updateTicket();
		
		

	}
}
