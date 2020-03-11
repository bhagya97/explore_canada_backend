package com.example.ExploreCanada.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ExploreCanada.beans.SearchInfo;
import com.example.ExploreCanada.database.CallStoredProcedure;
import com.example.ExploreCanada.database.ICallStoredProcedure;

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
	public List<SearchInfo> loadSearchByCategory(String category) {
		System.out.println("inside class");
        ICallStoredProcedure proc = null;
        SearchInfo searchInfo = null;
        List<SearchInfo> newsearch = new ArrayList<>();
        try {
            proc = new CallStoredProcedure("spLoadByCatergory(?)");
            proc.setParameter(1, category);
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
