package application;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.RegisteredDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;
import model.entities.Vehicle;

public class App2 {
	
	public static void main(String[] args) {
		
		//Para iniciar as vagas
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();
		slot.createTable();
//		slot.findByOccupied(true);
//		slot.freeSlot(1);
//		slot.occupieSlot(10, 40);
//		
//		slot.findByOccupied(true);
//		VehicleDaoJBDC vehicle = DaoFactory.createVehicleDaoJBDC();
//		Vehicle test = vehicle.findVehicleByPlate("ZAO123");
//		System.out.println(test);
		 
//		RegisteredDaoJBDC register = DaoFactory.createRegisteredDao();
//		Vehicle reg = register.FindRegisteredByPlate("YES123");
//		System.out.println(reg);
		
		
		
		
	}

}
