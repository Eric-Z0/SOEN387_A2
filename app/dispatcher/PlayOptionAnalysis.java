package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dom.command.PlayOptionAnalysisCommand;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.service.DispatcherFactory;
import org.dsrg.soenea.domain.command.CommandException;

public class PlayOptionAnalysis extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		
		PlayOptionAnalysisCommand cmd = new PlayOptionAnalysisCommand(myHelper);
		
		try {
			
			cmd.execute();
			
			Dispatcher dispatcher = DispatcherFactory.getInstance(cmd.dispatcherName);
			this.redirectToDispatcher(dispatcher);
			
		} catch (Exception e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}

	}

}
