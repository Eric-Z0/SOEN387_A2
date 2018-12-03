package org.dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.challenge.IChallenge;
import org.dom.model.challenge.mappers.ChallengeInputMapper;


public class ListChallengeCommand extends ValidatorCommand {

	@SetInRequestAttribute
	public List<IChallenge> challenges;
	
	public ListChallengeCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				challenges = ChallengeInputMapper.findAll();
			} else {
				String msg = "User is not logged in.";
				throw new CommandException(msg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}
