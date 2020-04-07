package com.explore.canada.dao;

import com.explore.canada.beans.Transaction;
import com.explore.canada.database.CallStoredProcedure;
import com.explore.canada.database.ICallStoredProcedure;

import java.sql.SQLException;

public class TransactionDAO implements ITransaction {
    @Override
    public boolean recordTransaction(Transaction transaction) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spInsertIntoTransaction(?, ?, ?, ?, ?, ?)");
            proc.setParameter(1, transaction.getBusId());
            proc.setParameter(2, transaction.getCustomerName());
            proc.setParameter(3, transaction.getNumOfAdults());
            proc.setParameter(4, transaction.getNumOfChildren());
            proc.setParameter(5, transaction.getPayment());
            proc.setParameter(6, transaction.getTransactionDate());
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
