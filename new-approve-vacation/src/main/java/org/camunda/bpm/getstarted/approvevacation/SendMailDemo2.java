package org.camunda.bpm.getstarted.approvevacation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendMailDemo2 {

	private static String USER_NAME = "xxx";  // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "xxx"; // GMail password
	
	private static String RECIPIENT = "xxx";// recipient mail

public static void main(String[] args) {
	
	System.out.println("SendMailDemo2 START");
	
    String from = USER_NAME;
    String pass = PASSWORD;
    String[] to = { RECIPIENT }; // list of recipient email addresses
    String subject = "Bussines Proposition";
    String body = "Hi Nikola, I am Dr. Bakare Tunde, the cousin of Nigerian Astronaut, Air Force Major Abacha Tunde. "
    		+ "He was the first African in space when he made a secret flight to the Salyut 6 space station in 1979. "
    		+ "He was on a later Soviet spaceflight, Soyuz T-16Z to the secret Soviet military space station Salyut 8T in 1989. "
    		+ "He was stranded there in 1990 when the Soviet Union was dissolved. His other Soviet crew members returned to earth on the"
    		+ " Soyuz T-16Z, but his place was taken up by return cargo. There have been occasional Progrez supply flights "
    		+ "to keep him going since that time. He is in good humor, but wants to come home. !"
    		+ "In the 14-years since he has been on the station, he has accumulated flight pay and interest amounting to almost $ 15,000,000 American Dollars. This is held in a trust at the Lagos National Savings and Trust Association. "
    		+ "If we can obtain access to this money, we can place a down payment with the Russian Space Authorities for "
    		+ "a Soyuz return flight to bring him back to Earth. I am told this will cost $ 3,000,000 American Dollars. "
    		+ "In order to access the his trust fund we need your assistance.\r\n"
    		+ "\r\n"
    		+ "Consequently, my colleagues and I are willing to transfer the total amount to your account or subsequent disbursement, "
    		+ "since we as civil servants are prohibited by the Code of Conduct Bureau (Civil Service Laws) "
    		+ "from opening and/ or operating foreign accounts in our names.\r\n"
    		+ "\r\n"
    		+ "Needless to say, the trust reposed on you at this juncture is enormous. "
    		+ "In return, we have agreed to offer you 20 percent of the transferred sum, "
    		+ "while 10 percent shall be set aside for incidental expenses (internal and external) between the parties in the course of the transaction."
    		+ " You will be mandated to remit the balance 70 percent to other accounts in due course.";
    
    
    /*
     * 
     * Test get data from properties file
     * 
     */
    try (OutputStream output = new FileOutputStream("path/to/config.properties")) {

        Properties prop = new Properties();

        // set the properties value
        prop.setProperty("db.url", "localhost");
        prop.setProperty("db.user", "xxx");
        prop.setProperty("db.password", "password");

        // save properties to project root folder
        prop.store(output, null);

        System.out.println(prop);

    } catch (IOException io) {
        io.printStackTrace();
    }

    sendFromGMail(from, pass, to, subject, body);
}

public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	
	System.out.println("sendFromGMail method START");
    Properties props = System.getProperties();
    String host = "smtp.gmail.com";
    
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
	    	message.setFrom(new InternetAddress(from));
	    	InternetAddress[] toAddress = new InternetAddress[to.length];
	
	    	// To get the array of addresses
	    	for( int i = 0; i < to.length; i++ ) {
	    		toAddress[i] = new InternetAddress(to[i]);
	    	}
	
	    	for( int i = 0; i < toAddress.length; i++) {
	    		message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    	}
	
	    	message.setSubject(subject);
	    	message.setText(body);
	
	    	Transport transport = session.getTransport("smtp");
	
	    	transport.connect(host, from, pass);
	    	transport.sendMessage(message, message.getAllRecipients());
	    	transport.close();

    }
    catch (AddressException ae) {
    	ae.printStackTrace();
    }
    catch (MessagingException me) {
    	me.printStackTrace();
    }
    
    
	}
} 