package model.entities;

import java.sql.Timestamp;

public class Ticket {

	private int id;
	private String plate;
	private Timestamp entryTime;
	private Timestamp exitTime;
	private int entryBarrier;
	private int exitBarrier;
	private int slotNumber;
	private double amountDue;

	public Ticket(int id, String plate, Timestamp entryTime, int entryBarrier, int slotNumber) {
		this.id = id;
		this.plate = plate;
		this.entryTime = entryTime;
		this.exitTime = null;
		this.entryBarrier = entryBarrier;
		this.exitBarrier = 0;
		this.slotNumber = slotNumber;
		this.amountDue = 0.0;
	}

	public Ticket(int id, String plate, Timestamp entryTime, Timestamp exitTime, int entryBarrier, int exitBarrier,
			int slotNumber, double amountDue) {
		this.id = id;
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

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public String printTicketEntry() {
		return "Ticket:" + id + " plate: " + plate + "\n" + "Entered: " + entryTime + " Through: " + entryBarrier
				+ "Parked at: " + slotNumber;
	}

	public String printTicketExit() {
		return "Ticket:" + id + " plate: " + plate + "\n" + "Entered: " + entryTime + " Through barrier: "
				+ entryBarrier + "Left " + exitTime + " Thought barrier: " + exitBarrier + "Parked at: " + slotNumber
				+ " value: " + amountDue;
	}

}
