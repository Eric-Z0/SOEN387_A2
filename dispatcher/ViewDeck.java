package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ViewDeckCommand;

public class ViewDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		
		ViewDeckCommand cmd = new ViewDeckCommand(myHelper);
		
		try {
			cmd.execute();
			myHelper.setRequestAttribute("deckObj", cmd.deckObj);
			//myHelper.setRequestAttribute("cardIDItr", cmd.cardIDItr);
			forward("/WEB-INF/jsp/ViewDeck.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}
}
