package com.explore.canada.beans;

public class ShoppingCart {
    Card cardInfo;
    TicketInfo bookingInfo;
    UserInfo userInfo;

    public ShoppingCart() {
    }

    public ShoppingCart(Card cardInfo, TicketInfo bookingInfo, UserInfo userInfo) {
        this.cardInfo = cardInfo;
        this.bookingInfo = bookingInfo;
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Card getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(Card cardInfo) {
        this.cardInfo = cardInfo;
    }

    public TicketInfo getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(TicketInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }
}
