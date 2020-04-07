package com.explore.canada.dao;

import com.explore.canada.beans.Card;
import com.explore.canada.database.CallStoredProcedure;
import com.explore.canada.database.ICallStoredProcedure;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO implements  ICardDAO {
    @Override
    public boolean updateCardBalance(String cardNumber, float cardBalance) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spUpdateCardBalance(?,?)");
            proc.setParameter(1, cardNumber);
            proc.setParameter(2, cardBalance);
            proc.execute();
        }
        catch (SQLException sqlException)
        {
            String errorMessage = String.format("Sql exception occurred: %s",sqlException.getMessage());
            System.out.println(errorMessage);
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

    public Card loadCardDetailsById(String cardNumber,String name) {
        ICallStoredProcedure proc = null;
        Card cardDetails = null;
        try
        {
            proc = new CallStoredProcedure("spLoadCardDetailsById(?,?)");
            proc.setParameter(1, cardNumber);
            proc.setParameter(2, name);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    cardDetails = new Card();
                    cardDetails.setCardNumber(results.getString(1));
                    cardDetails.setCvvNumber(results.getInt(2));
                    cardDetails.setCardHolderName(results.getString(3));
                    cardDetails.setMonthExpiry(results.getInt(4));
                    cardDetails.setYearExpiry(results.getInt(5));
                    cardDetails.setCardBalance(results.getFloat(6));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
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
        return cardDetails;
    }
}
