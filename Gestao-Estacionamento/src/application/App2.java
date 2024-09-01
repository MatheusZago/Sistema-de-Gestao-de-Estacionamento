package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.entities.ParkingSlot;

public class App2 {
	//This method is used solely to create the ParkingSlot Table
	public static void main(String[] args) {
		
		//This method creates parkingSlot tables with all the slots free for use
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDaoJBDC();
//		slot.createTable();	
		
		List<Integer> availableSlotIds = new ArrayList<>(); // Para armazenar IDs de slots disponíveis
		
		List<ParkingSlot> list = slot.findByOccupied(false);
		
		list.forEach(slotX -> {
//			System.out.println(slotX);
			availableSlotIds.add(slotX.getId());
		});
		
		
		
		availableSlotIds.forEach(System.out::println);
		
		
//		slot.findByOccupied(true);
		
		
	}

}
