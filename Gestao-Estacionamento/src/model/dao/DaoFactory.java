package model.dao;

import db.DB;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.RegistersDaoJBDC;
import model.dao.impl.EnrolleesDaoJBDC;
import model.dao.impl.TicketDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;

//Class used to centralize DAO object creating and grant access to method that acces the DB
public class DaoFactory {

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
	
	public static RegistersDaoJBDC createRegisterDaoJBDC() {
		return new RegistersDaoJBDC(DB.getConnection());
	}

}
