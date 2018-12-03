package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.LoginCommand;


public class Login extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		
		LoginCommand cmd = new LoginCommand(myHelper);
		
		try {
			
			myRequest.getSession(true).invalidate();
			cmd.execute();
			myRequest.getSession(true).setAttribute("userID", cmd.currentUser.getId());
			myHelper.setRequestAttribute("message", "User '" + cmd.currentUser.getName() + "' has successfully logged in.");
			forward("/WEB-INF/jsp/success.jsp");
			
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
