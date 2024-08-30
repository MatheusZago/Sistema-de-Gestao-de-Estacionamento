package model.dao;

import db.DB;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.EnrolleesDaoJBDC;
import model.dao.impl.TicketDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;

public class DaoFactory {

	// A Fabrica Dao serve par criar conexões especificas, caso quisesse outra
	// conexão fora JBDC seria aqui
	public static VehicleDaoJBDC createVehicleDaoJBDC() {
		return new VehicleDaoJBDC(DB.getConnection());
	}

	public static EnrolleesDaoJBDC createEnrolleesDaoJBDC() {
		return new EnrolleesDaoJBDC(DB.getConnection());
	}

	public static ParkingSlotDaoJBDC createParkingSlotDaoJBDC() {
		return new ParkingSlotDaoJBDC(DB.getConnection());
	}

	public static TicketDaoJBDC createTicketDaoJBDC() {
		return new TicketDaoJBDC(DB.getConnection());
	}

}
