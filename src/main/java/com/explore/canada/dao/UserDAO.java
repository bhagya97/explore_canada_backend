package com.explore.canada.dao;

import com.explore.canada.database.CallStoredProcedure;
import com.explore.canada.database.ICallStoredProcedure;
import com.explore.canada.beans.UserInfo;
import com.explore.canada.beans.UserState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    @Override
    public boolean registerUser(UserInfo userInfo) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spRegisterUser(?, ?, ?, ?, ?, ?, ?, ?)");
            proc.setParameter(1, userInfo.getUserId());
            proc.setParameter(2, userInfo.getUserFirstName());
            proc.setParameter(3, userInfo.getUserLastName());
            proc.setParameter(4, userInfo.getUserEmail());
            proc.setParameter(5, userInfo.getUserPassword());
            proc.setParameter(6, userInfo.getUserDateOfBirth());
            proc.setParameter(7, UserState.ACTIVE.toString());
            proc.registerOutputParameterString(8);
            proc.execute();
        }
        catch (SQLException sqlException)
        {
            String errorMessage = String.format("Sql exception occurred: %s",sqlException.getMessage());
            //logger.error(errorMessage);
            return false;
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public void loadUserById(String userId, UserInfo userInfo) {
        ICallStoredProcedure proc = null;
        try
        {
            if(userInfo == null){
                userInfo = new UserInfo();
            }
            proc = new CallStoredProcedure("spLoadUserByID(?)");
            proc.setParameter(1, userId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    userInfo.setUserId(results.getString(1));
                    userInfo.setUserEmail(results.getString(2));
                    userInfo.setUserPassword(results.getString(3));
                    userInfo.setUserFirstName(results.getString(4));
                    userInfo.setUserLastName(results.getString(5));
                    userInfo.setUserDateOfBirth(results.getString(6));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //logger.error(e);
        }
        catch (Exception genericException){
            //logger.error(genericException);
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
    }

    @Override
    public void loadUserByEmail(String emailId, UserInfo userInfo) {
        ICallStoredProcedure proc = null;
        String userId = null;
        try
        {
            proc = new CallStoredProcedure("spLoadUserByEmailID(?)");
            proc.setParameter(1, emailId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    userId = results.getString(1);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //logger.error(e);
        }
        catch (Exception genericException){
            //logger.error(genericException);
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }

        if(userId != null){
            loadUserById(userId, userInfo);
        }
    }

    @Override
    public boolean updateUser(UserInfo userInfo) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spUpdateUserByEmailID(?, ?, ?, ?)");
            proc.setParameter(1, userInfo.getUserEmail());
            proc.setParameter(2, userInfo.getUserFirstName());
            proc.setParameter(3, userInfo.getUserLastName());
            proc.setParameter(4, userInfo.getUserDateOfBirth());
            proc.execute();
        }
        catch (SQLException sqlException)
        {
            String errorMessage = String.format("Sql exception occurred: %s",sqlException.getMessage());
            //logger.error(errorMessage);
            return false;
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean deleteUser(UserInfo userInfo) {
        ICallStoredProcedure proc = null;

        try
        {
            proc = new CallStoredProcedure("spDeleteUserByEmailID(?)");
            proc.setParameter(1, userInfo.getUserEmail());
            proc.execute();
        }
        catch (SQLException sqlException)
        {
            String errorMessage = String.format("Sql exception occurred: %s",sqlException.getMessage());
            //logger.error(errorMessage);
            return false;
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public List<UserInfo> loadAllUsers() {
        ICallStoredProcedure proc = null;
        UserInfo userInfo = null;
        List<UserInfo> userInfoList = new ArrayList<>();
        try
        {
            proc = new CallStoredProcedure("spLoadAllUsers()");
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    userInfo = new UserInfo();
                    userInfo.setUserId(results.getString(1));
                    userInfo.setUserEmail(results.getString(2));
                    userInfo.setUserPassword(results.getString(3));
                    userInfo.setUserFirstName(results.getString(4));
                    userInfo.setUserLastName(results.getString(5));
                    userInfo.setUserDateOfBirth(results.getString(6));
                    userInfoList.add(userInfo);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //logger.error(e);
        }
        catch (Exception genericException){
            //logger.error(genericException);
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return userInfoList;
    }
}
