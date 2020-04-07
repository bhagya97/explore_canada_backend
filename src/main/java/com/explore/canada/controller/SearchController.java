package com.explore.canada.controller;

import com.explore.canada.beans.SearchInfo;
import com.explore.canada.config.Configuration;
import com.explore.canada.dao.ISearchDAO;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE })
public class SearchController {

    @GetMapping(value="/locations/places")
    public List<SearchInfo> getPlacesByKeyword(Model model , @RequestParam("keyword") String keyword) throws UnsupportedEncodingException {
        keyword = URLDecoder.decode(keyword, "UTF-8");
        SearchInfo searchInfo = new SearchInfo();
        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
        List<SearchInfo> places = searchInfo.loadSearchByCategory(searchDAO, keyword, (SearchInfo) searchInfo);
        return places;
    }

    @GetMapping(value="/locations")
    public List<SearchInfo> getAllPlaces(Model model) {
        SearchInfo searchInfo = new SearchInfo();
        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
        List<SearchInfo> locations = searchInfo.loadAllPlaces(searchDAO);
        return locations;
    }
}
