package org.dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.game.IGame;
import org.dom.model.game.mappers.GameInputMapper;


public class ListGameCommand extends ValidatorCommand {
	
	@SetInRequestAttribute
	public List<IGame> games;
	
	public ListGameCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			games = GameInputMapper.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}
