package com.explore.canada.beans;

public class UserOTP {
    String userEmail;
    String userOTP;

    public UserOTP() {
    }

    public UserOTP(String userEmail, String userOTP) {
        this.userEmail = userEmail;
        this.userOTP = userOTP;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserOTP() {
        return userOTP;
    }

    public void setUserOTP(String userOTP) {
        this.userOTP = userOTP;
    }
}
