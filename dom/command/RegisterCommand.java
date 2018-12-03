package org.dom.command;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;

import java.util.ArrayList;

import org.dom.model.user.IUser;
import org.dom.model.user.UserFactory;
import org.dom.model.user.mappers.UserInputMapper;


public class RegisterCommand extends ValidatorCommand {

	@Source
	public String user;
	
	@Source
	public String pass;
	
	@SetInRequestAttribute
	public IUser currentUser;
	
	public RegisterCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			
			if(user!=null && pass!= null) {
				if(user.contains("\"")) {
					user = user.replaceAll("\"", "");
				} else if(user.contains("\'")) {
					user = user.replaceAll("\'", "");
				}
				if(pass.contains("\"")) {
					pass = pass.replaceAll("\"", "");
				} else if(pass.contains("\'")) {
					pass = pass.replaceAll("\'", "");
				}

			} else {
				String message = "Please enter both username and password.";
				throw new CommandException(message);
			}
			
			// Check if the user has already registered
			try{
				currentUser = UserInputMapper.findByName(user);
				String message = "User already exists!";
				throw new CommandException(message);
			} catch (MapperException e) {
				
			}
			
			if(user.isEmpty() || pass.isEmpty()) {
				String message = "Username and password cannot be empty.";
				throw new CommandException(message);
			} else {
				currentUser = UserFactory.createNew(user, pass, new ArrayList<Long>());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}
