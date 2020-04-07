package com.explore.canada.dao;

import com.explore.canada.beans.UserInfo;
import com.explore.canada.beans.UserOTP;
import com.explore.canada.config.Configuration;
import com.explore.canada.security.IPasswordEncryption;

public class UserLogin implements IUserLogin {

    private UserOTP userOTP;

    public UserLogin(){
        userOTP = new UserOTP();
    }

    @Override
    public boolean authenticate(String userEmail, String userPassword, IPasswordEncryption encryption,IUserDAO userDAO, UserInfo userInfo) {
        userDAO.loadUserByEmail(userEmail,userInfo);
        return encryption.matches(userPassword,userInfo.getUserPassword());
    }

    @Override
    public void setOneTimePassword(String userEmail, String oneTimePassword) {
            this.userOTP.setUserEmail(userEmail);
            this.userOTP.setUserOTP(oneTimePassword);
    }

    @Override
    public String getOneTimePassword() {
        return this.userOTP.getUserOTP();
    }

    @Override
    public String getUserEmail() {
        return this.userOTP.getUserEmail();
    }
}
