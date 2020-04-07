package com.explore.canada.dao;

import com.explore.canada.beans.Card;

public interface ICardDAO {
    public boolean updateCardBalance(String cardNumber, float cardBalance);
    public Card loadCardDetailsById(String cardNumber,String name);
}
