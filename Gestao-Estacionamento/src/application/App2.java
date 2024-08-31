package application;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;

public class App2 {
	//This method is used solely to create the ParkingSlot Table
	public static void main(String[] args) {
		
		//This method creates parkingSlot tables with all the slots free for use
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDaoJBDC();
		slot.createTable();	
	}

}
