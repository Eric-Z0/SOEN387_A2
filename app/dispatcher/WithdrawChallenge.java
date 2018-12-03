package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.WithdrawChallengeCommand;


public class WithdrawChallenge extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		WithdrawChallengeCommand cmd = new WithdrawChallengeCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("message", "The challenge has been withdrawed.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
