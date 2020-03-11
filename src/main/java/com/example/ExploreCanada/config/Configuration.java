package com.example.ExploreCanada.config;

import com.example.ExploreCanada.dao.ISearchDAO;
import com.example.ExploreCanada.dao.SearchDAO;
import com.example.ExploreCanada.database.DefaultDatabaseConfiguration;
import com.example.ExploreCanada.database.IDatabaseConfiguration;

public class Configuration {

	    static Configuration configuration;
	    IDatabaseConfiguration databaseConfiguration;
	    ISearchDAO searchDAO;

	    private Configuration(){

	        databaseConfiguration = new DefaultDatabaseConfiguration();
	        searchDAO = new SearchDAO();
	    }

	    public static Configuration instance(){
	        if(configuration == null){
	            configuration = new Configuration();
	        }

	        return configuration;
	    }


	    public IDatabaseConfiguration getDatabaseConfiguration() {
	        return databaseConfiguration;
	    }

	    public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
	        this.databaseConfiguration = databaseConfiguration;
	    }


	    public ISearchDAO getSearchDAO() {
	        return searchDAO;
	    }

	    public void setSearchDAO(ISearchDAO searchDAO) {
	        this.searchDAO = searchDAO;
	    }

}
