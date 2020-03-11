package com.example.ExploreCanada.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExploreCanada.beans.SearchInfo;
import com.example.ExploreCanada.config.Configuration;
import com.example.ExploreCanada.dao.ISearchDAO;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE })
public class SearchController {

	    @GetMapping(value="/place/{category}")
	    @ResponseBody
	    public List<SearchInfo> getCategory(@PathVariable String category) {
	        SearchInfo searchInfo = new SearchInfo();
	        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
	        return searchInfo.loadSearchByCategory(searchDAO, category, (SearchInfo) searchInfo);
	      
	        
	    }

	    @GetMapping(value="/place")
	    @ResponseBody
	    public List<SearchInfo> getAllPlaces() {
	        SearchInfo searchInfo = new SearchInfo();
	        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
	        List<SearchInfo> places = new ArrayList<>();
	        return searchInfo.loadAllPlaces(searchDAO);
	    }
	}

