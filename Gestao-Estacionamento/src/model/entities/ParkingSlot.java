package model.entities;

import model.enums.SlotType;

//Entity that represents a ParkingSlot 
public class ParkingSlot {
	
	//Variables
	private int id;
	private SlotType type;
	private boolean occupied;
	private int occupiedBy;
	
	public ParkingSlot(int id, SlotType type, boolean occupied, int occupiedBy) {
		this.id = id;
		this.type = type;
		this.occupied = occupied;
		this.occupiedBy = occupiedBy;
	}

	public int getId() {
		return id;
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
	
	

	public int getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(int occupiedBy) {
		this.occupiedBy = occupiedBy;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(SlotType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String free;
		if(occupied == true) {
			free = "occupied";
			return "Slot: " + id + ", for " + type + ", " + free + " by id:" + occupiedBy;
		} else {
			free = "free";
			return "Slot: " + id + ", for " + type + ", " + free ;
		}
		
	}
	
	
	
	
	
	

}
