package model.entities;

import java.sql.Timestamp;

//Class to represent a Ticket
public class Ticket {

	private int id;
	private int vehicleId;
	private String plate;
	private Timestamp entryTime;
	private Timestamp exitTime;
	private int entryBarrier;
	private int exitBarrier;
	private String slotNumber;
	private double amountDue;

	//Multiple Constructors for multiple uses
	public Ticket(int id, int vehicleId, String plate, Timestamp entryTime, int entryBarrier, String slotNumber) {
		this.id = id;
		this.vehicleId = vehicleId;
		this.plate = plate;
		this.entryTime = entryTime;
		this.exitTime = null;
		this.entryBarrier = entryBarrier;
		this.exitBarrier = 0;
		this.slotNumber = slotNumber;
		this.amountDue = 0.0;
	}

	public Ticket(int id, int vehicleId , String plate, 
			Timestamp entryTime, Timestamp exitTime, int entryBarrier, int exitBarrier,
			String slotNumber, double amountDue) {
		this.id = id;
		this.vehicleId = vehicleId;
		this.plate = plate;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.entryBarrier = entryBarrier;
		this.exitBarrier = exitBarrier;
		this.slotNumber = slotNumber;
		this.amountDue = amountDue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Timestamp getExitTime() {
		return exitTime;
	}

	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}

	public int getEntryBarrier() {
		return entryBarrier;
	}

	public void setEntryBarrier(int entryBarrier) {
		this.entryBarrier = entryBarrier;
	}

	public int getExitBarrier() {
		return exitBarrier;
	}

	public void setExitBarrier(int exitBarrier) {
		this.exitBarrier = exitBarrier;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	
	public int getIdVehicle() {
		return vehicleId;
	}

	public void setIdVehicle(int idVehicle) {
		this.vehicleId = idVehicle;
	}

	//Different prints for different ticket forms
	public String printTicketEntry() {
		return  "-----------------------------"
				+ "\nTicket: " + id + " plate: " + plate +  " id-vehicle: " + vehicleId  
				+ "\n" + "Entered at: " + entryTime + " Through barrier: " + entryBarrier
				+ "\n" + "Parked at: " + slotNumber 
				+ "\n-----------------------------";
	}

	public String printTicketExit() {
		
		String formattedAmountDue = String.format("%.2f", amountDue);
		return "-----------------------------"
				+ "\nTicket:" + id + " plate: " + plate +  " id-vehicle: " + vehicleId  
				+ "\n" + "Entered: " + entryTime + " Through barrier: "+ entryBarrier 
				+ "\nLeft " + exitTime + " Thought barrier: " + exitBarrier 
				+ "\nParked at: " + slotNumber+ " value: R$" + formattedAmountDue
				+ "\n-----------------------------";
	}

}
