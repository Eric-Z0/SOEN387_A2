package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.AcceptChallengeCommand;


public class AcceptChallenge extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		AcceptChallengeCommand cmd = new AcceptChallengeCommand(myHelper);
		try {
			cmd.execute();;
			myRequest.setAttribute("message", "That challenge has been successfully accepted.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
