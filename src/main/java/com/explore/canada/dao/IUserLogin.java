package com.explore.canada.dao;

import com.explore.canada.beans.UserInfo;
import com.explore.canada.security.IPasswordEncryption;

public interface IUserLogin {
    public boolean authenticate(String userEmail, String userPassword, IPasswordEncryption encryption, IUserDAO userDAO, UserInfo userInfo);
    public void setOneTimePassword(String userEmail, String oneTimePassword);
    public String getOneTimePassword();
    public String getUserEmail();
}
