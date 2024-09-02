package model.entities;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.dao.DaoFactory;
import model.dao.impl.TicketDaoJBDC;
import model.enums.VehicleCategory;

//Entity that represents a IndividualVehicle
public class IndividualVehicle extends Vehicle {
	
	//Instantiate Ticket for use, it is the only one that uses tickets
	TicketDaoJBDC ticketDao = DaoFactory.createTicketDaoJBDC();

	//Using 2 constructors for diferent momens
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

	//Override of method enter of superclass to add more
	@Override
	public void enter(Vehicle vehicle, Timestamp dateTime) {
		
		String numberValue;
		//Using the enter method from superclass and add the last parts
		super.enter(vehicle, dateTime);
		//Geting the entryBarrier from super for latter use
		int entryBarrier = super.getEntryBarrier();
		
		//Transforming choices into string array to use on ticket, since it needs to have 2 different slots
		int[] choices = getChoices();
		if(choices.length == 1) {
	        numberValue = String.valueOf(choices[0]);
		} else {
			//In case it hs more than 1 number it creates a String unifying them
			numberValue = Arrays.stream(choices)
						.mapToObj(String::valueOf)
						.collect(Collectors.joining(", "));
		}
		//Creates a ticket
		ticketDao.insert(vehicle.getId(), vehicle.getPlate(), dateTime, entryBarrier, numberValue);
		System.out.println("Here is the ticket for your entry: ");
		Ticket ticket = ticketDao.findEntryTicketByVehicleId(vehicle.getId());
		System.out.println(ticket.printTicketEntry());        
		}
	
	//Override of exit method of superclass to add more
	@Override
	public void exit(Vehicle vehicle ,  Timestamp exitTimeStamp) {
		//Using the enter method from superclass and add the last parts
		super.exit(vehicle, exitTimeStamp);
		//getting exitBarrier from super for latter use
		int exitBarrier = super.getExitBarrier();
		
		//Formatting the carge method and using it to updateTicket and print it
		double charge = charge(vehicle.getId(), exitTimeStamp);
		//Updating Ticket
		ticketDao.updateTicket(exitTimeStamp, exitBarrier, charge, vehicle.getId());;
		//Creating a new ticket to receive the updated one
		Ticket ticket = ticketDao.findExitTicketByVehicleId(vehicle.getId());
		System.out.println(ticket.printTicketExit());
	}

	//Method to charge and return the value
	public double charge(int vehicleId, Timestamp exitStamp) {
		//Variables for calculating the charge
		double chargePerMin = 0.10;
		double mininumCharge = 5.00;
		
		//Instantiating ticket and returing 
		Ticket returnedTicket = ticketDao.findEntryTicketByVehicleId(vehicleId);
		
		//Getting timestamps 
		Timestamp dateOfEntry = returnedTicket.getEntryTime();
		Timestamp dateOfExit = exitStamp;
		
        //Calculationg diference
        long millisecondsDifference = dateOfExit.getTime() - dateOfEntry.getTime();
        Duration duration = Duration.ofMillis(millisecondsDifference);


        //Transformation difference into minutes from milliseconds
        long minutes = duration.toMinutes();
        double charge = minutes * chargePerMin;
        
        //If the value is inferior to the minimumCharge it becomes a minimun charge
        if(charge < mininumCharge) {
        	charge = mininumCharge;
        } 

		return charge;
	}


}
