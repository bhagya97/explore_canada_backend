package com.explore.canada.accesscontrol;

import com.explore.canada.beans.UserInfo;

public interface IUserNotifications
{
	public void sendUserLoginCredentials(UserInfo user, String rawPassword);
}
