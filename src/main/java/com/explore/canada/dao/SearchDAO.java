package com.explore.canada.dao;

import com.explore.canada.beans.SearchInfo;
import com.explore.canada.database.CallStoredProcedure;
import com.explore.canada.database.ICallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO  implements ISearchDAO{

    @Override
    public List<SearchInfo> loadAllplaces() {
        ICallStoredProcedure proc = null;
        SearchInfo searchInfo = null;
        List<SearchInfo> searchInfoList = new ArrayList<>();
        try
        {
            proc = new CallStoredProcedure("spLoadAllPlaces()");
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    searchInfo = new SearchInfo();
                    searchInfo.setSearchID(results.getString(1));
                    searchInfo.setPlace(results.getString(2));
                    searchInfo.setLocation(results.getString(3));
                    searchInfo.setName(results.getString(4));
                    searchInfo.setCategory(results.getString(5));
                    searchInfo.setUrl(results.getString(6));
                    searchInfoList.add(searchInfo);
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
        return searchInfoList;
    }

    @Override
    public List<SearchInfo> loadSearchByKeyword(String keyword) {
        System.out.println("inside class");
        ICallStoredProcedure proc = null;
        SearchInfo searchInfo = null;
        List<SearchInfo> newsearch = new ArrayList<>();
        try {
            proc = new CallStoredProcedure("spLoadPlacesByKeyword(?)");
            proc.setParameter(1, keyword);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    searchInfo = new SearchInfo();
                    searchInfo.setSearchID(results.getString(1));
                    searchInfo.setPlace(results.getString(2));
                    searchInfo.setLocation(results.getString(3));
                    searchInfo.setName(results.getString(4));
                    searchInfo.setCategory(results.getString(5));
                    searchInfo.setUrl(results.getString(6));
                    newsearch.add(searchInfo);
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
        System.out.println(newsearch.size());
        return newsearch;
    }
}
