package com.explore.canada.dao;

import com.explore.canada.beans.SearchInfo;

import java.util.List;

public interface ISearchDAO {

    public List<SearchInfo> loadAllplaces();
    public List<SearchInfo> loadSearchByKeyword(String keyword);
}
