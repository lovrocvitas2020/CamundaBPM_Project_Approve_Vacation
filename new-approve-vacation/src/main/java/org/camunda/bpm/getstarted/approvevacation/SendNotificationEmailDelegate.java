package org.camunda.bpm.getstarted.approvevacation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendNotificationEmailDelegate implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("SendNotificationEmailDelegate execute START");		
		System.out.println("execution.getVariables().toString():"+execution.getVariables().toString());
		
		String email = null;
		String messageText = null;
		String[] to = null;
	
		email = (String)execution.getVariableLocal("email");
		System.out.println("variable email: "+email);
		messageText = (String)execution.getVariableLocal("messageText");
		System.out.println("variable messageText: "+messageText);
		
		System.out.println("SENDING MAIL");
		
		SendMailDemo2 sendmail = new SendMailDemo2();
		
		String USER_NAME = "xxx";  // GMail user name (just the part before "@gmail.com")
		String PASSWORD = "xxx"; // GMail password
		String RECIPIENT = "xxx"; // recipient mail		   
		String from = USER_NAME;
	    String pass = PASSWORD;
		    
		    if(email != null) {
		    	String[] to1= { email }; // list of recipient email addresses
		    	to=to1;
		    } else {
		    	String[] to2 = { RECIPIENT }; 
		    	to=to2;
		    }
		   
		    String subject = "Notification mail";
		    String body = messageText;
		
		System.out.println("sending notification mail");		
		sendmail.sendFromGMail(from, pass, to, subject, body);		

	}

}
