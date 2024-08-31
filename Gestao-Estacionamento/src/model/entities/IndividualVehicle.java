package model.entities;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.dao.DaoFactory;
import model.dao.impl.TicketDaoJBDC;
import model.enums.VehicleCategory;

//Classe para representar os veiculos avulsos, não cadastrados.
public class IndividualVehicle extends Vehicle {
	
	TicketDaoJBDC ticketDao = DaoFactory.createTicketDaoJBDC();

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
	public void enter(Vehicle vehicle, Timestamp dateTime) {
		
		String numberValue;
		// Ele já colocou a vaga
		super.enter(vehicle, dateTime);
		int entryBarrier = super.getEntryBarrier();
		
		//Aqui vai transformar em String para que possa concatenar as multiplas vagas
		//Sendo ocupados por carros ou caminhões
		int[] choices = getChoices();
		if(choices.length == 1) {
			numberValue = String.valueOf(choices);
		} else {
			//Caso tenha mais de um valor ele vai juntar todos para serem colocados no ticket
			numberValue = Arrays.stream(choices)
						.mapToObj(String::valueOf)
						.collect(Collectors.joining(", "));
		}
		
		ticketDao.insert(vehicle.getId(), vehicle.getPlate(), dateTime, entryBarrier, numberValue);

		System.out.println("Chamou o enter do avulso");
		        
		}
	
	@Override
	public void exit(Vehicle vehicle ,  Timestamp exitTimeStamp) {
			super.exit(vehicle, exitTimeStamp);
		int exitBarrier = super.getExitBarrier();
		
		double charge = charge(vehicle.getId(), exitTimeStamp);
		ticketDao.updateTicket(exitTimeStamp, exitBarrier, charge, vehicle.getId());;
		Ticket ticket = ticketDao.findExitTicketByVehicleId(vehicle.getId());
		System.out.println(ticket.printTicketExit());
	}


	public double charge(int vehicleId, Timestamp exitStamp) {
		
		double chargePerMin = 0.10;
		double mininumCharge = 5.00;
				
		TicketDaoJBDC ticket = DaoFactory.createTicketDaoJBDC();
		Ticket returnedTicket = ticket.findEntryTicketByVehicleId(vehicleId);
		
		Timestamp dateOfEntry = returnedTicket.getEntryTime();
		Timestamp dateOfExit = exitStamp;
		
        // Calcula a diferença em milissegundos
        long millisecondsDifference = dateOfExit.getTime() - dateOfEntry.getTime();

        // Converte a diferença para Duration
        Duration duration = Duration.ofMillis(millisecondsDifference);

        // Obter a diferença em diferentes unidades
//        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        
        double charge = minutes * chargePerMin;
        

        if(charge < mininumCharge) {
        	charge = mininumCharge;
        } 
        

		return charge;
	}


}
