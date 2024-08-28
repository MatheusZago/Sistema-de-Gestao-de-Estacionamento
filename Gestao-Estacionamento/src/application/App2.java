package application;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;

public class App2 {
	
	public static void main(String[] args) {
		
		//Para iniciar as vagas
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();
//		slot.createTable();
		//Quero achar todos os que não estão ocupados
		slot.findByOccupied(false);
		
		
		
	}

}
