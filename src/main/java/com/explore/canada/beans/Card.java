package com.explore.canada.beans;

import com.explore.canada.dao.CardDAO;
import com.explore.canada.dao.ICardDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Card {
    String cardNumber;
    int cvvNumber;
    String cardHolderName;
    int monthExpiry;
    int yearExpiry;
    float cardBalance;

    public Card() {
    }

    public Card(String cardNumber, int cvvNumber, String cardHolderName, int monthExpiry, int yearExpiry, float cardBalance) {
        this.cardNumber = cardNumber;
        this.cvvNumber = cvvNumber;
        this.cardHolderName = cardHolderName;
        this.monthExpiry = monthExpiry;
        this.yearExpiry = yearExpiry;
        this.cardBalance = cardBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getMonthExpiry() {
        return monthExpiry;
    }

    public void setMonthExpiry(int monthExpiry) {
        this.monthExpiry = monthExpiry;
    }

    public int getYearExpiry() {
        return yearExpiry;
    }

    public void setYearExpiry(int yearExpiry) {
        this.yearExpiry = yearExpiry;
    }

    public float getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(float cardBalance) {
        this.cardBalance = cardBalance;
    }

    public boolean isCardExpired(){
        DateTimeFormatter currMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter currYear = DateTimeFormatter.ofPattern("YYYY");
        if(Integer.parseInt(currYear.format(LocalDateTime.now())) > this.yearExpiry &&
                Integer.parseInt(currMonth.format(LocalDateTime.now())) > this.monthExpiry){
            return true;
        }
        return false;
    }

    public boolean isCreditCardValid(){
        ICardDAO cardDAO = new CardDAO();
        Card cardData = cardDAO.loadCardDetailsById(this.cardNumber,this.cardHolderName);

        if(null != cardData){
            this.cardBalance = cardData.getCardBalance();
            return true;
        }
        return false;
    }

}
