package model.entities;

import model.enums.SlotType;

public class ParkingSlot {
	
	private int id;
	private int number;
	private SlotType type;
	private boolean occupied;
	
	public ParkingSlot(int id, int number, SlotType type, boolean occupied) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.occupied = occupied;
	}

	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}


	public SlotType getType() {
		return type;
	}


	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	@Override
	public String toString() {
		String free;
		if(occupied == true) {
			free = "free";
		} else {
			free = "occupied";
		}
		
		return "Slot: " + number + ", for " + type + ", " + free ;
	}
	
	
	
	
	
	

}
