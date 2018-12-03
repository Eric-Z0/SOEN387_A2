package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.ViewBoardCommand;


public class ViewBoard extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ViewBoardCommand cmd = new ViewBoardCommand(myHelper);
		try {
			cmd.execute();
			myRequest.getSession(true).setAttribute("game", cmd.gameObj);
			forward("/WEB-INF/jsp/ViewBoard.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
