package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.user.IUser;
import org.dom.model.user.mappers.UserInputMapper;

public class LogoutCommand extends ValidatorCommand {
	
	@SetInRequestAttribute
	public String userName;
	
	public LogoutCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				long userID = (long)helper.getSessionAttribute("userID");
				IUser user = UserInputMapper.find(userID);
				userName = user.getName();
			} else {
				String msg = "No user logged in.";
				throw new CommandException(msg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}
}
