package com.explore.canada.dao;

import com.explore.canada.beans.Transaction;

public interface ITransaction {
    public boolean recordTransaction(Transaction transaction);
}
