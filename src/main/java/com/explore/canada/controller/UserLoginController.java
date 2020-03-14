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

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class UserLoginController {
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

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
            return userInfo;
        }
        return null;
    }
}
