package com.explore.canada.dao;

import com.explore.canada.beans.UserInfo;
import com.explore.canada.config.Configuration;
import com.explore.canada.security.IPasswordEncryption;

public class UserLogin implements IUserLogin {
    @Override
    public boolean authenticate(String userEmail, String userPassword, IPasswordEncryption encryption,IUserDAO userDAO, UserInfo userInfo) {
        userDAO.loadUserByEmail(userEmail,userInfo);
        return encryption.matches(userPassword,userInfo.getUserPassword());
    }
}
