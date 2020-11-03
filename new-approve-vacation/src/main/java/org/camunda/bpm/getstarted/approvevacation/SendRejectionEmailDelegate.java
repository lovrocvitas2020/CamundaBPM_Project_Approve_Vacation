package org.camunda.bpm.getstarted.approvevacation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendRejectionEmailDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
	System.out.println("SendRejectionEmailDelegate execute START");
		
		System.out.println("SENDING REJECTION MAIL");
		
System.out.println("execution.getVariables().toString():"+execution.getVariables().toString());
		
		String email = null;
		String messageText = null;
		
	
		email = (String)execution.getVariableLocal("email");
		System.out.println("variable email: "+email);
		messageText = (String)execution.getVariableLocal("messageText");
		System.out.println("variable messageText: "+messageText);
		
		
		SendMailDemo2 sendmail = new SendMailDemo2();
		
		  String USER_NAME = "xxx";  // GMail user name (just the part before "@gmail.com")
		  String PASSWORD = "xxx"; // GMail password

		  String RECIPIENT = "xxx"; // recipient mail
		  
		    String from = USER_NAME;
		    String pass = PASSWORD;
		    String[] to = { RECIPIENT }; // list of recipient email addresses
		    String subject = "Rejection mail";
		    String body = messageText;
		
		System.out.println("sending rejection mail");
		
		sendmail.sendFromGMail(from, pass, to, subject, body);
	}
	
	

}
