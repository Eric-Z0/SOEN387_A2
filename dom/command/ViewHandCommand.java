package org.dom.command;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.game.Game;
import org.dom.model.game.mappers.GameInputMapper;


public class ViewHandCommand extends ValidatorCommand {

	@Source(sources={PermalinkSource.class})
	public long game;
	
	@SetInRequestAttribute
	public List<Long> hand;
	
	public ViewHandCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				Game gameObj = GameInputMapper.find(game);
				
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(userID == gameObj.getP1ID()) {
					hand = gameObj.getP1Hand();
				} else if(userID == gameObj.getP2ID()) {
					hand = gameObj.getP2Hand();
				} else {
					String msg = "Only player in the game can view hand.";
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
