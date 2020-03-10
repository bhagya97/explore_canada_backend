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
    ServiceResponse response;
    int responseCode;
    List<String> responseMessage;
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    @GetMapping(value="/login")
    @ResponseBody
    public ServiceResponse userLogin(@RequestParam(name = USER_EMAIL) String userEmail,
                                     @RequestParam(name = USER_PASSWORD) String userPassword)
    {
        System.out.println("In user login");
        boolean success = false;
        IUserDAO userDAO = Configuration.instance().getUserDAO();
        IPasswordEncryption passwordEncryption = Configuration.instance().getPasswordEncryption();
        IUserNotifications userNotifications = Configuration.instance().getUserNotifications();
        IUserLogin userLogin = Configuration.instance().getUserLogin();
        success = userLogin.authenticate(userEmail,userPassword,passwordEncryption,userDAO);
        //We are setting the below value just to reply a message back to the caller
        response = new ServiceResponse();
        responseMessage = new ArrayList<>();
        responseCode = success ? ErrorMessage.SUCCESS_CODE : ErrorMessage.ERROR_CODE;
        responseMessage.add(success ? ErrorMessage.SUCCESS_MESSAGE : ErrorMessage.ERROR_MESSAGE);
        response.setStatus(responseCode);
        response.setMessage(responseMessage.toArray(new String[0]));
        return response;
    }
}
