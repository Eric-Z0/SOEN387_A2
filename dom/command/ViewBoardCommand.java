package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.game.Game;
import org.dom.model.game.mappers.GameInputMapper;


public class ViewBoardCommand extends ValidatorCommand {

	@Source(sources={PermalinkSource.class})
	public long game;
	
	@SetInRequestAttribute
	public Game gameObj;
	
	public ViewBoardCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				gameObj = GameInputMapper.find(game);
				
				long p1ID = gameObj.getP1ID();
				long p2ID = gameObj.getP2ID();
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(userID != p1ID && userID != p2ID) {
					String msg = "You cannot view a board that you are not included.";
					throw new CommandException(msg);
				}
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
