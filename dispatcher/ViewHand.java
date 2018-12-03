package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ViewHandCommand;

public class ViewHand extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ViewHandCommand cmd = new ViewHandCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("hand", cmd.hand);
			forward("/WEB-INF/jsp/ViewHand.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
