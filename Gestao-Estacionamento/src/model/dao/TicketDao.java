package model.dao;

import java.sql.Timestamp;

import model.entities.Ticket;

//Interface used to keep the necessary methods for other types of DB connections
//It is only used by JBDC in this project,
public interface TicketDao {

	void insert(int vehicleId, String plate, Timestamp entryTime, int entryBarrier, String numberValue);
	void updateTicket(Timestamp exitTime, int exitBarrier, double amountDue, int vehicleId);
	Ticket findEntryTicketByVehicleId(int vehicleId);
	Ticket findExitTicketByVehicleId(int vehicleId);

}
