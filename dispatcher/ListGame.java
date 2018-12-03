package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ListGameCommand;


public class ListGame extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListGameCommand cmd = new ListGameCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("games", cmd.games);
			forward("/WEB-INF/jsp/ListGames.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
