package com.explore.canada.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Controller
@PropertySource("classpath:application.properties")
public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	@Value("${spring.datasource.url}")
	private String databaseUrl = "jdbc:mysql://explore-canada.csogsl7wmasf.us-west-2.rds.amazonaws.com:3306/explore_canada?zeroDateTimeBehavior=convertToNull&useSSL=false";

	@Value("${spring.datasource.username}")
	private String username = "admin";

	@Value("${spring.datasource.password}")
	private String password = "password";

	
	public String getDatabaseUserName()
	{
		return username;
	}

	public String getDatabasePassword()
	{
		return password;
	}

	public String getDatabaseUrl()
	{
		return databaseUrl;
	}
}
