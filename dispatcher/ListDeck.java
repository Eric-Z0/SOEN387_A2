package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dom.command.ListDeckCommand;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

public class ListDeck extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		ListDeckCommand cmd = new ListDeckCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("decks", cmd.decks);
			forward("/WEB-INF/jsp/ListDecks.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
