package model.dao;

import java.sql.Timestamp;

public interface TicketDao {
	
	public void insert(String plate, Timestamp entryTime, int entryBarrier, int slotNumber);
	void updateTicket(Timestamp exitTime, int exitBarrier, double amountDue, String plate);

}
