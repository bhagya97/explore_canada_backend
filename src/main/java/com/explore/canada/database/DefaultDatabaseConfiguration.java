package com.explore.canada.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	@Value("${spring.datasource.url}")
	private String databaseUrl="jdbc:mysql://localhost:3306/explore_canada?zeroDateTimeBehavior=convertToNull&useSSL=false";

	@Value("${spring.datasource.username}")
	private String username="root";

	@Value("${spring.datasource.password}")
	private String password="33021923";

	
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
