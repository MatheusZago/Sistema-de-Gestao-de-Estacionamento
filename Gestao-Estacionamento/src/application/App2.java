package application;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;

public class App2 {
	
	public static void main(String[] args) {
		
		//Para iniciar as vagas
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDaoJBDC();
//		slot.createTable();
		
		int valor = 1;
		
		Vehicle vehicle = new MonthlySubscriber("123"); 
		
		MonthlySubscriber sub = new MonthlySubscriber("123");
		//
		if(vehicle instanceof MonthlySubscriber) {
			System.out.println("Sim");
		} else {
			System.out.println("Não");
		}
		
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
