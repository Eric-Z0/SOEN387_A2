package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.UploadDeckCommand;


public class UploadDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		UploadDeckCommand cmd = new UploadDeckCommand(myHelper);
		try {
			cmd.execute();
			//myRequest.getSession(true).setAttribute("deckID", cmd.deckID);
			myHelper.setRequestAttribute("message", "The deck has been successfully uploaded.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
