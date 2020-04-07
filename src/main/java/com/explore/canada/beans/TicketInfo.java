package com.explore.canada.beans;

public class TicketInfo {

    private String busId;
    private String company;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;
    private int no_of_adults;
    private int no_of_children;
    private int availableSeats;
    private float adult_fare;
    private float child_fare;
    private float total;

    public TicketInfo(){

    }

    public TicketInfo(String busId, String company, String source, String destination, String departureTime, String arrivalTime, String departureDate, String arrivalDate, int no_of_adults, int no_of_children, int availableSeats, float adult_fare, float child_fare, float total) {
        this.busId = busId;
        this.company = company;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.no_of_adults = no_of_adults;
        this.no_of_children = no_of_children;
        this.availableSeats = availableSeats;
        this.adult_fare = adult_fare;
        this.child_fare = child_fare;
        this.total = total;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getNo_of_adults() {
        return no_of_adults;
    }

    public void setNo_of_adults(int no_of_adults) {
        this.no_of_adults = no_of_adults;
    }

    public int getNo_of_children() {
        return no_of_children;
    }

    public void setNo_of_children(int no_of_children) {
        this.no_of_children = no_of_children;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public float getAdult_fare() {
        return adult_fare;
    }

    public void setAdult_fare(float adult_fare) {
        this.adult_fare = adult_fare;
    }

    public float getChild_fare() {
        return child_fare;
    }

    public void setChild_fare(float child_fare) {
        this.child_fare = child_fare;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
