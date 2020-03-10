package com.explore.canada.dao;

import com.explore.canada.beans.UserInfo;

import java.util.List;

public interface IUserDAO {

    public boolean registerUser(UserInfo userInfo);
    public UserInfo loadUserById(String userId);
    public UserInfo loadUserByEmail(String emailId, UserInfo userInfo);
    public boolean updateUser(UserInfo userInfo);
    public boolean deleteUser(UserInfo userInfo);
    public List<UserInfo> loadAllUsers();
}
