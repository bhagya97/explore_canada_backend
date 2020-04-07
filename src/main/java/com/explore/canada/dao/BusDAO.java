package com.explore.canada.dao;

import com.explore.canada.beans.TicketInfo;
import com.explore.canada.database.CallStoredProcedure;
import com.explore.canada.database.ICallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO implements IBusDAO {
    @Override
    public List<TicketInfo> loadBusDetailsBySourceAndDestinations(String source, String destination) {
        ICallStoredProcedure proc = null;
        TicketInfo ticketInfo = null;
        List<TicketInfo> busList = new ArrayList<>();
        try
        {
            proc = new CallStoredProcedure("sploadBusDetailsBySourceAndDestinations(?,?)");
            proc.setParameter(1, source);
            proc.setParameter(2, destination);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    ticketInfo = new TicketInfo();
                    ticketInfo.setBusId(results.getString(1));
                    ticketInfo.setCompany(results.getString(2));
                    ticketInfo.setSource(results.getString(3));
                    ticketInfo.setDestination(results.getString(4));
                    ticketInfo.setDepartureTime(results.getString(5));
                    ticketInfo.setArrivalTime(results.getString(6));
                    ticketInfo.setDepartureDate(results.getString(7));
                    ticketInfo.setArrivalDate(results.getString(8));
                    ticketInfo.setAdult_fare(results.getFloat(9));
                    ticketInfo.setChild_fare(results.getFloat(10));
                    ticketInfo.setAvailableSeats(results.getInt(11));
                    busList.add(ticketInfo);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //logger.error(e);
        }
        catch (Exception genericException){
            //logger.error(genericException);
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return busList;
    }

    @Override
    public TicketInfo loadBusDetailsById(String busID) {
        ICallStoredProcedure proc = null;
        TicketInfo ticketInfo = null;
        try
        {
            proc = new CallStoredProcedure("spLoadBusDetailsById(?)");
            proc.setParameter(1, busID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    ticketInfo = new TicketInfo();
                    ticketInfo.setBusId(results.getString(1));
                    ticketInfo.setCompany(results.getString(2));
                    ticketInfo.setSource(results.getString(3));
                    ticketInfo.setDestination(results.getString(4));
                    ticketInfo.setDepartureTime(results.getString(5));
                    ticketInfo.setArrivalTime(results.getString(6));
                    ticketInfo.setDepartureDate(results.getString(7));
                    ticketInfo.setArrivalDate(results.getString(8));
                    ticketInfo.setAdult_fare(results.getFloat(9));
                    ticketInfo.setChild_fare(results.getFloat(10));
                    ticketInfo.setAvailableSeats(results.getInt(11));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //logger.error(e);
        }
        catch (Exception genericException){
            //logger.error(genericException);
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return ticketInfo;
    }

    @Override
    public boolean updateSeatDetails(String busID, int noOfReservedSeats) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spUpdateSeatDetails(?,?)");
            proc.setParameter(1, busID);
            proc.setParameter(2, noOfReservedSeats);
            proc.execute();
        }
        catch (SQLException sqlException)
        {
            String errorMessage = String.format("Sql exception occurred: %s",sqlException.getMessage());
            //logger.error(errorMessage);
            return false;
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }
}
