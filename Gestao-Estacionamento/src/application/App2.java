package application;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.entities.IndividualVehicle;
import model.enums.VehicleCategory;

public class App2 {
	
	public static void main(String[] args) {
		
		//Para iniciar as vagas
//		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDaoJBDC();
//		slot.createTable();
//		
		
		IndividualVehicle vehicle = new IndividualVehicle(1, "OBA123", VehicleCategory.MOTORCYCLE);
////		
		LocalDateTime localDateTime = LocalDateTime.of(2024, 8, 30, 22, 0);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		
		System.out.println(vehicle.charge(20, timestamp));
//		
//		TicketDaoJBDC ticket = DaoFactory.createTicketDaoJBDC();
//		Ticket ticketTeste =  ticket.findEntryTicketByVehicleId(20);
//		
//		
//		
//		System.out.println(ticketTeste.printTicketEntry());
		
		
		
		
	}

}
