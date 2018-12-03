package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ListPlayerCommand;


public class ListPlayer extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListPlayerCommand cmd = new ListPlayerCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("players", cmd.players);
			forward("/WEB-INF/jsp/ListPlayers.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
