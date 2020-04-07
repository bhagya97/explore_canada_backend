package com.explore.canada.accesscontrol;

import com.explore.canada.beans.TicketInfo;
import com.explore.canada.beans.UserInfo;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IUserNotifications
{
	public void sendUserLoginCredentials(UserInfo user, String rawPassword);
	public void sendOneTimePasswordNotification(UserInfo user, String secret);
	public void sendTicketConfirmationNotification(UserInfo user, TicketInfo ticketInfo);
}
