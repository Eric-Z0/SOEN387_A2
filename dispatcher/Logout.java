package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.LogoutCommand;

public class Logout extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		LogoutCommand cmd = new LogoutCommand(myHelper);
		try {
			cmd.execute();
			myHelper.setRequestAttribute("message", cmd.userName + " has logged out");
			myRequest.getSession(true).invalidate();
			forward("/WEB-INF/jsp/success.jsp");	
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
