package com.explore.canada.controller;

import com.explore.canada.accesscontrol.IUserNotifications;
import com.explore.canada.exception.ErrorMessage;
import com.explore.canada.beans.ServiceResponse;
import com.explore.canada.beans.UserInfo;
import com.explore.canada.config.Configuration;
import com.explore.canada.dao.IUserDAO;
import com.explore.canada.security.IPasswordEncryption;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class UserRegistrationController {

    @PostMapping(value="/register")
    @ResponseBody
    public UserInfo registerUser(@RequestBody UserInfo userInfo) {
        System.out.println("In UserRegistrationResponse");
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        IPasswordEncryption passwordEncryption = Configuration.instance().getPasswordEncryption();
        IUserNotifications userNotifications = Configuration.instance().getUserNotifications();
        boolean success = userInfo.registerUser(userDAO,passwordEncryption,userNotifications);
        if(success){
            return userInfo;
        }
        return null;
    }

    @GetMapping(value="/users/{userId}")
    @ResponseBody
    public UserInfo getUserById(@PathVariable String userId) {
        UserInfo userInfo = new UserInfo();
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        userInfo.loadUserById(userDAO,userId,userInfo);
        return userInfo;
    }

    @GetMapping(value="/users/user/{emailId}")
    @ResponseBody
    public UserInfo getUserByEmailId(@PathVariable String emailId) {
        UserInfo userInfo = new UserInfo();
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        userInfo.loadUserByEmailId(userDAO,emailId,userInfo);
        return userInfo;
    }

    @GetMapping(value="/users")
    @ResponseBody
    public List<UserInfo> getAllUsers() {
        System.out.println("In getAllUsers controller");
        UserInfo userInfo = new UserInfo();
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        return userInfo.loadAllUsers(userDAO);
    }
}
