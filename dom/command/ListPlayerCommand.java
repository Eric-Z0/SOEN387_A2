package org.dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.user.IUser;
import org.dom.model.user.mappers.UserInputMapper;


public class ListPlayerCommand extends ValidatorCommand {
	
	@SetInRequestAttribute
	public List<IUser> players;
	
	public ListPlayerCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				players = UserInputMapper.findAll();
				
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
