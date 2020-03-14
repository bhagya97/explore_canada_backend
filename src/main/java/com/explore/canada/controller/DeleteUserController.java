package com.explore.canada.controller;

import com.explore.canada.accesscontrol.IUserNotifications;
import com.explore.canada.beans.UserInfo;
import com.explore.canada.config.Configuration;
import com.explore.canada.dao.IUserDAO;
import com.explore.canada.dao.IUserLogin;
import com.explore.canada.security.IPasswordEncryption;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class DeleteUserController {
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    @GetMapping(value="/delete")
    @ResponseBody
    public UserInfo deleteUser(@RequestParam(name = USER_EMAIL) String userEmail)
    {
        boolean success = false;
        UserInfo userInfo = new UserInfo();
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        IPasswordEncryption passwordEncryption = Configuration.instance().getPasswordEncryption();
        IUserNotifications userNotifications = Configuration.instance().getUserNotifications();
        success = userDAO.deleteUser(userInfo);
        if(success){
            return userInfo;
        }
        return null;
    }
}
