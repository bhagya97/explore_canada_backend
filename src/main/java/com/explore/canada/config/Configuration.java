package com.explore.canada.config;

import com.explore.canada.accesscontrol.IUserNotifications;
import com.explore.canada.accesscontrol.SendNotification;
import com.explore.canada.dao.IUserDAO;
import com.explore.canada.dao.IUserLogin;
import com.explore.canada.dao.UserDAO;
import com.explore.canada.dao.UserLogin;
import com.explore.canada.database.DefaultDatabaseConfiguration;
import com.explore.canada.database.IDatabaseConfiguration;
import com.explore.canada.security.BCryptPasswordEncryption;
import com.explore.canada.security.IPasswordEncryption;

public class Configuration {

    static Configuration configuration;
    IDatabaseConfiguration databaseConfiguration;
    IUserDAO userDAO;
    IPasswordEncryption passwordEncryption;
    IUserNotifications userNotifications;
    IUserLogin userLogin;

    private Configuration(){
        setAWSCredentials();
        databaseConfiguration = new DefaultDatabaseConfiguration();
        userDAO = new UserDAO();
        passwordEncryption = new BCryptPasswordEncryption();
        userNotifications = new SendNotification();
        userLogin = new UserLogin();
    }

    public static Configuration instance(){
        if(configuration == null){
            configuration = new Configuration();
        }

        return configuration;
    }


    private void setAWSCredentials(){
        System.setProperty("aws.accessKeyId", "AKIAIWGVZIJ52CRYX57Q");
        System.setProperty("aws.secretKey","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");
    }

    public IDatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }


    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public IPasswordEncryption getPasswordEncryption() {
        return passwordEncryption;
    }

    public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    public IUserNotifications getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(IUserNotifications userNotifications) {
        this.userNotifications = userNotifications;
    }

    public IUserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(IUserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
