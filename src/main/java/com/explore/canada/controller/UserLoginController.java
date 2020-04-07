package com.explore.canada.controller;
import com.explore.canada.accesscontrol.IUserNotifications;
import com.explore.canada.dao.IUserLogin;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class UserLoginController {
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String SECRET = "secret";

    @PostMapping(value="/login")
    @ResponseBody
    public UserInfo userLogin(@RequestParam(name = USER_EMAIL) String userEmail,
                                     @RequestParam(name = USER_PASSWORD) String userPassword)
    {
        boolean success = false;
        UserInfo userInfo = new UserInfo();
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        IPasswordEncryption passwordEncryption = Configuration.instance().getPasswordEncryption();
        IUserNotifications userNotifications = Configuration.instance().getUserNotifications();
        IUserLogin userLogin = Configuration.instance().getUserLogin();
        success = userLogin.authenticate(userEmail,userPassword,passwordEncryption,userDAO, userInfo);
        if(success){
            String oneTimePassword = UUID.randomUUID().toString().substring(0,5);
            userLogin.setOneTimePassword(userEmail,oneTimePassword);
            userNotifications.sendOneTimePasswordNotification(userInfo,oneTimePassword);
            return userInfo;
        }
        return null;
    }

    @PostMapping(value="/validateotp")
    @ResponseBody
    public UserInfo validateOneTimePassword(@RequestParam(name = USER_EMAIL) String userEmail,
                                          @RequestParam(name = SECRET) String userSecret)
    {
        UserInfo userInfo = null;
        IUserDAO userDAO = null;
        IUserLogin userLogin = Configuration.instance().getUserLogin();
        if(userLogin.getOneTimePassword().equalsIgnoreCase(userSecret) &&
                userLogin.getUserEmail().equalsIgnoreCase(userEmail)){
            userInfo = new UserInfo();
            userDAO = Configuration.instance().getUserDAO();
            userDAO.loadUserByEmail(userEmail,userInfo);
        }
        return userInfo;
    }
}
