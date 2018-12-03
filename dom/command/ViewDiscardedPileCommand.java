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


public class ViewDiscardedPileCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source(sources={PermalinkSource.class})
	public long player;
	
	@SetInRequestAttribute
	public List<Long> discardPile;
	
	public ViewDiscardedPileCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				Game gameObj = GameInputMapper.find(game);
				long userID = (long)helper.getSessionAttribute("userID");
				
				// First check if the login user is in the game 
				if(userID == gameObj.getP1ID() || userID == gameObj.getP2ID()) {
					// Check which player's discard pile to be viewed
					if(player == gameObj.getP1ID()) {
						discardPile = gameObj.getP1DiscardPile();
					} else if(player == gameObj.getP2ID()) {
						discardPile = gameObj.getP2DiscardPile();
					} else {
						String msg = "The player of whom the discard pile to be viewed is not in that game.";
						throw new CommandException(msg);
					}
				} else {
					String msg = "Only player in the game can view discard pile.";
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
