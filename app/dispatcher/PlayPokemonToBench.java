package org.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;

import org.dom.command.PlayPokemonToBenchCommand;


public class PlayPokemonToBench extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		PlayPokemonToBenchCommand cmd = new PlayPokemonToBenchCommand(myHelper);
		try {
			cmd.execute();
			myHelper.setRequestAttribute("message", cmd.playerName + " played pokemon to bench");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			myHelper.setRequestAttribute("notifications", cmd.getNotifications());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
