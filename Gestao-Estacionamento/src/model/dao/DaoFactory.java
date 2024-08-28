package model.dao;

import db.DB;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;

public class DaoFactory {
	
	//A Fabrica Dao serve par criar conexões especificas, caso quisesse outra conexão fora JBDC seria aqui
	public static VehicleDaoJBDC createVehicleDao() {
		return new VehicleDaoJBDC(DB.getConnection());
	}
	
	public static ParkingSlotDaoJBDC createParkingSlotDao() {
		return new ParkingSlotDaoJBDC(DB.getConnection());
	}

}
