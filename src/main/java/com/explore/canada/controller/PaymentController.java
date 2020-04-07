package com.explore.canada.controller;

import com.explore.canada.accesscontrol.IUserNotifications;
import com.explore.canada.accesscontrol.SendNotification;
import com.explore.canada.beans.Card;
import com.explore.canada.beans.ShoppingCart;
import com.explore.canada.beans.TicketInfo;
import com.explore.canada.beans.Transaction;
import com.explore.canada.config.Configuration;
import com.explore.canada.dao.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class PaymentController {

    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    //after clicking payment submit button
    @PostMapping(value = "/payment")
    @ResponseBody
    public TicketInfo payment(@RequestBody ShoppingCart shoppingCart) {

        boolean validCreditCard = false;
        boolean paymentSuccess = false;
        boolean transactionSuccess = false;
        boolean ticketBooked = false;
        float cardBalance = 0.0f;
        TicketInfo bookingDetails = null;
        IUserNotifications userNotifications;
        ICardDAO cardDAO = new CardDAO();
        ITransaction transactionDAO = new TransactionDAO();
        Card creditCardData = shoppingCart.getCardInfo();
        TicketInfo ticketInfo = shoppingCart.getBookingInfo();

        if(!creditCardData.isCardExpired()){
            if(creditCardData.isCreditCardValid()){
                validCreditCard = true;
            }
        }

            if(validCreditCard) {
                float payableAmount = ticketInfo.getTotal();
                cardBalance = creditCardData.getCardBalance();
                if(cardBalance >= payableAmount) {

                    cardBalance = cardBalance - payableAmount;

                    // Update the customer account balance
                    paymentSuccess = cardDAO.updateCardBalance(creditCardData.getCardNumber(),cardBalance);

                    if(paymentSuccess){
                        Transaction txn = new Transaction();
                        txn.setBusId(ticketInfo.getBusId());
                        txn.setCustomerName(creditCardData.getCardHolderName());
                        txn.setNumOfAdults(Integer.toString(ticketInfo.getNo_of_adults()));
                        txn.setNumOfChildren(Integer.toString(ticketInfo.getNo_of_children()));
                        txn.setPayment(payableAmount);
                        String txnDate = date.format(LocalDateTime.now());
                        txn.setTransactionDate(txnDate);
                        transactionSuccess = transactionDAO.recordTransaction(txn);
                    }
                    if(transactionSuccess){
                        IBusDAO busDAO = new BusDAO();
                        ticketBooked = busDAO.updateSeatDetails(
                                shoppingCart.getBookingInfo().getBusId(),
                                (shoppingCart.getBookingInfo().getNo_of_adults() + shoppingCart.getBookingInfo().getNo_of_children()));
                    }
                }
            }
            if(ticketBooked){
                bookingDetails = shoppingCart.getBookingInfo();
                userNotifications = Configuration.instance().getUserNotifications();
                userNotifications.sendTicketConfirmationNotification(shoppingCart.getUserInfo(),bookingDetails);
            }
        return bookingDetails;
    }
}
