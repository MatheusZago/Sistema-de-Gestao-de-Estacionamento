package model.entities;

import java.sql.Timestamp;

public class Register {
	
	private int id;
	private Timestamp dateOfEntry;
	private Timestamp dateOfExit;
	private int id_vehicle;
	
	public Register(int id, Timestamp dateOfEntry, int id_vehicle) {
		this.id = id;
		this.dateOfEntry = dateOfEntry;
		this.id_vehicle = id_vehicle;
	}
	
	public Register(int id, Timestamp dateOfEntry, Timestamp dateOfExit, int id_vehicle) {
		this.id = id;
		this.dateOfEntry = dateOfEntry;
		this.dateOfExit = dateOfExit;
		this.id_vehicle = id_vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(Timestamp dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public Timestamp getDateOfExit() {
		return dateOfExit;
	}

	public void setDateOfExit(Timestamp dateOfExit) {
		this.dateOfExit = dateOfExit;
	}

	public int getId_vehicle() {
		return id_vehicle;
	}

	public void setId_vehicle(int id_vehicle) {
		this.id_vehicle = id_vehicle;
	}

	public String printRegisterEntry() {
		return "-----------------------------"
				+ "Register id:" + id +  ", id_vehicle: " + id_vehicle
				+ "\n dateOfEntry: " + dateOfEntry 
				+"-----------------------------";
	}
	
	public String printRegisterExit() {
		return "-----------------------------"
				+ "\nRegister id:" + id +   ", id_vehicle: " + id_vehicle 
				+ "\ndate of entry: " + dateOfEntry 
				+ "\ndate of exit: " + dateOfExit 
				+ "\n-----------------------------";
	}

}
