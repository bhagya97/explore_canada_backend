package com.example.ExploreCanada.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	@Value("${spring.datasource.url}")
	private String databaseUrl="jdbc:mysql://localhost:3306/explorecanada?zeroDateTimeBehavior=convertToNull&useSSL=false";

	@Value("${spring.datasource.username}")
	private String username="root";

	@Value("${spring.datasource.password}")
	private String password="root123";

	
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
