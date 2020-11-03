package org.camunda.bpm.getstarted.approvevacation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ApproveVacationDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		boolean test = false;
		
		String approve = (String)execution.getVariable("approve");
		String email = (String)execution.getVariable("email");
		String messageText = (String)execution.getVariable("messageText");
		
		System.out.println("ApproveVacationDelegate execute START test 3");
		
		System.out.println("form input approve: "+approve);
		System.out.println("form input email: "+email);
		System.out.println("form input messageText: "+messageText);
		
		if(approve != null && approve.equals("true")) {
			 test = true;
		}

		
		System.out.println("boolean test set to: "+test);
		
		//execution.setVariable("name", "Lovro");
		execution.setVariable("vacationOk", test);
		execution.setVariable("approve", approve);
		execution.setVariable("email", email);
		execution.setVariable("messageText", messageText);
		
	}

}
