package com.example.ExploreCanada.dao;

import java.util.List;

import com.example.ExploreCanada.beans.SearchInfo;



public interface ISearchDAO {

	public List<SearchInfo> loadAllplaces();
	public List<SearchInfo> loadSearchByCategory(String category);
}
