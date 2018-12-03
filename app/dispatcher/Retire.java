package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.RetireCommand;

public class Retire extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		RetireCommand cmd = new RetireCommand(myHelper);
		try {
			cmd.execute();
			myHelper.setRequestAttribute("message", cmd.playerName + " retired from game");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}
	

}
