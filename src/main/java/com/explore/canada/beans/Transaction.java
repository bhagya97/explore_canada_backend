package com.explore.canada.beans;

public class Transaction {
    String busId;
    String customerName;
    String numOfAdults;
    String numOfChildren;
    float payment;
    String transactionDate;

    public Transaction() {
    }

    public Transaction(String busId, String customerName, String numOfAdults, String numOfChildren, float payment, String transactionDate) {
        this.busId = busId;
        this.customerName = customerName;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.payment = payment;
        this.transactionDate = transactionDate;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(String numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public String getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(String numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
