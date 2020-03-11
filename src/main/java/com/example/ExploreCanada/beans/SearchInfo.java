package com.example.ExploreCanada.beans;

import java.util.List;

import com.example.ExploreCanada.dao.ISearchDAO;


public class SearchInfo {

    String searchID;
	String place;
    String location;
    String name;
    String category;
    String url;
    
	public SearchInfo() {
		super();
	}

	public SearchInfo(String searchID, String place, String location, String name, String category, String url) {
		super();
		this.searchID = searchID;
		this.place = place;
		this.location = location;
		this.name = name;
		this.category = category;
		this.url = url;
	}

	public String getSearchID() {
		return searchID;
	}

	public String getPlace() {
		return place;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getUrl() {
		return url;
	}
	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public List<SearchInfo> loadSearchByCategory(ISearchDAO userDb, String category, SearchInfo searchInfo)
    {
		List<SearchInfo> searchInfoList;
        try {
        		System.out.println(category);
        		System.out.println(searchInfo);
            searchInfoList = userDb.loadSearchByCategory(category);
            	System.out.println(searchInfo);
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        System.out.println(searchInfoList.size());
        return searchInfoList;
    }

    public List<SearchInfo> loadAllPlaces(ISearchDAO userDb)
    {
        List<SearchInfo> searchInfoList;
        try {
            searchInfoList = userDb.loadAllplaces();
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        return searchInfoList;
    }
	
}
