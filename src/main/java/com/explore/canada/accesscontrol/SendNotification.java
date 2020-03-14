package com.explore.canada.accesscontrol;

//import org.apache.log4j.Logger;

import com.explore.canada.beans.UserInfo;

import java.io.IOException;

public class SendNotification implements IUserNotifications {

	//private Logger logger = Logger.getLogger(this.getClass());

	public void sendUserLoginCredentials(UserInfo user, String rawPassword) {
		try {
			AmazonSES.sendNotification(user.getUserEmail());
		}
		catch (IOException exception){
			exception.printStackTrace();
		}
	}

}
