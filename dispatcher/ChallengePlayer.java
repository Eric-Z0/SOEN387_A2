package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ChallengePlayerCommand;


public class ChallengePlayer extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ChallengePlayerCommand cmd = new ChallengePlayerCommand(myHelper);
		try {
			cmd.execute();
			myRequest.setAttribute("message", "The challenge has been successfully issued.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
