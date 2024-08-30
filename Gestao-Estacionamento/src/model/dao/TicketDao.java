package model.dao;

import java.sql.Timestamp;

public interface TicketDao {
	

	void insert(int vehicleId, String plate, Timestamp entryTime, int entryBarrier, String numberValue);
	void updateTicket(Timestamp exitTime, int exitBarrier, double amountDue, int vehicleId);

}
