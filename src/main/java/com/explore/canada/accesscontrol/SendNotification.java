package com.explore.canada.accesscontrol;

//import org.apache.log4j.Logger;
import com.explore.canada.beans.Mail;
import com.explore.canada.beans.TicketInfo;
import com.explore.canada.beans.UserInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendNotification implements IUserNotifications {

	private static final String EXPLORE_CANADA_LOGIN_URL = "http://explorecanadaclientdocker-env.eba-r54aukfg.us-west-2.elasticbeanstalk.com/login";
	private static final String FROM = "akki007.singh@email.com";
	private static final String SUBJECT = "Email from explore-canada";
	//private Logger logger = Logger.getLogger(this.getClass());
	private EmailSenderService emailService = new EmailSenderService();
	private Mail mail = new Mail();

	public void sendUserLoginCredentials(UserInfo user, String rawPassword) {
		String header =
				"<p>\n" +
				"You have been successfully registered at <b>Explore Canada</b> on\n" +
				"<span>" + new Date().toString() + "</span>\n" +
				"</p>\n";
		String body = "<p>Thank you for registering with us. Please use the link given below to login to your account.</p>" +
						 "<a href=" + EXPLORE_CANADA_LOGIN_URL + ">explore-canada.ca</a>";
		mail.setFrom(FROM);
		mail.setMailTo(user.getUserEmail());//replace with your desired email
		mail.setSubject(SUBJECT);

		Map<String, String> model = new HashMap<String, String>();
		model.put("name", user.getUserFirstName() + " " + user.getUserLastName());
		model.put("message",header + body);
		model.put("signature", "Explore-Canada Team");
		mail.setProps(model);
		emailService.sendEmail(mail);
	}

	public void sendOneTimePasswordNotification(UserInfo user, String secret) {
		String message = "Please refer your secret code given below, it will be valid for two minutes.\n" +
					 	 "<b>" + secret + "</b>";
		mail.setFrom(FROM);
		mail.setMailTo(user.getUserEmail());//replace with your desired email
		mail.setSubject(SUBJECT);

		Map<String, String> model = new HashMap<String, String>();
		model.put("name", user.getUserFirstName() + " " + user.getUserLastName());
		model.put("message",message);
		model.put("signature", "Explore-Canada Team");
		mail.setProps(model);
		emailService.sendEmail(mail);
	}

	public void sendTicketConfirmationNotification(UserInfo user, TicketInfo ticketInfo) {
		String message = "Please refer your booking details given below.</br>" +
				"<p>" +
				"Customer Name:  <b>" + user.getUserFirstName() + " " + user.getUserLastName() + "</b>" + "</b>" +
				"Company Name:  <b>" + ticketInfo.getCompany() + "</b>" + "</b>" +
				"Bus Id:  <b>" + ticketInfo.getBusId() + "</b>" + "</b>" +
				"Arrival date:  <b>" + ticketInfo.getArrivalDate() + "</b>" + "</b>" +
				"Arrival time:  <b>" + ticketInfo.getArrivalTime() + "</b>" + "</b>" +
				"Departure date:  <b>" + ticketInfo.getDepartureDate() + "</b>" + "</b>" +
				"Departure time:  <b>" + ticketInfo.getDepartureTime() + "</b>" + "</b>" +
				"Source:  <b>" + ticketInfo.getSource() + "</b>" + "</b>" +
				"Destination:  <b>" + ticketInfo.getDestination() + "</b>" + "</b>" +
				"Total fare:  <b>" + ticketInfo.getTotal() + "</b>" +
				"</p>";

		mail.setFrom(FROM);
		mail.setMailTo(user.getUserEmail());//replace with your desired email
		mail.setSubject(SUBJECT);

		Map<String, String> model = new HashMap<String, String>();
		model.put("name", user.getUserFirstName() + " " + user.getUserLastName());
		model.put("message",message);
		model.put("signature", "Explore-Canada Team");
		mail.setProps(model);
		emailService.sendEmail(mail);
	}

}
