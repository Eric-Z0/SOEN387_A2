package org.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;

import org.dom.command.RegisterCommand;


public class Register extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		RegisterCommand cmd = new RegisterCommand(myHelper);
		try {
			myRequest.getSession(true).invalidate();
			cmd.execute();
			myRequest.getSession(true).setAttribute("userID", cmd.currentUser.getId());
			
			try {
				UoW.getCurrent().commit();
				myHelper.setRequestAttribute("message", "User '" + cmd.currentUser.getName() + "' has been registered.");
				forward("/WEB-INF/jsp/success.jsp");
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
			
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
