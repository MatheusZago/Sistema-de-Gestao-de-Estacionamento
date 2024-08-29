package model.entities;

import java.sql.Timestamp;

import model.enums.VehicleCategory;

public class Parked {

	private String plate;
	private Timestamp dateTime;
	private Integer numberSlot;
	private VehicleCategory category;

	public Parked(String plate, Timestamp dateTime, Integer numberSlot, VehicleCategory category) {
		this.plate = plate;
		this.dateTime = dateTime;
		this.numberSlot = numberSlot;
		this.category = category;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getNumberSlot() {
		return numberSlot;
	}

	public void setNumberSlot(Integer numberSlot) {
		this.numberSlot = numberSlot;
	}

	public VehicleCategory getCategory() {
		return category;
	}

	public void setCategory(VehicleCategory category) {
		this.category = category;
	}

//	public void exit(Parked parked) {
//		System.out.println("Leaving by the barrier: ");
//		BarrierService.validateExitBarriers(parked);
//
//	}

	@Override
	public String toString() {
		return "Parked [plate=" + plate + ", dateTime=" + dateTime + ", numberSlot=" + numberSlot + ", category="
				+ category + "]";
	}

}
