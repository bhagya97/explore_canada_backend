package com.explore.canada.dao;

import com.explore.canada.beans.TicketInfo;

import java.util.List;

public interface IBusDAO {
    public List<TicketInfo> loadBusDetailsBySourceAndDestinations(String source, String destination);
    public TicketInfo loadBusDetailsById(String busID);
    public boolean updateSeatDetails(String busID, int noOfReservedSeats);
}
