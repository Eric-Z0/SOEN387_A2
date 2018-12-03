package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dom.command.EndTurnCommand;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

public class EndTurn extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		EndTurnCommand cmd = new EndTurnCommand(myHelper);
		try {
			cmd.execute();
			myHelper.setRequestAttribute("message", "Player has ended the turn.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
