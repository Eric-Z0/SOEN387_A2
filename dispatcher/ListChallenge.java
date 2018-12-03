package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ListChallengeCommand;


public class ListChallenge extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListChallengeCommand cmd = new ListChallengeCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("challenges", cmd.challenges);
			forward("/WEB-INF/jsp/ListChallenges.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
