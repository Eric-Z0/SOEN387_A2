package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ViewDiscardedPileCommand;


public class ViewDiscardedPile extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		ViewDiscardedPileCommand cmd = new ViewDiscardedPileCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("discardPile", cmd.discardPile);
			forward("/WEB-INF/jsp/ViewDiscardPile.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
