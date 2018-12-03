package org.dom.command;

import org.dom.model.deck.Deck;
import org.dom.model.deck.mappers.DeckInputMapper;
import org.dom.model.game.Game;
import org.dom.model.game.mappers.GameInputMapper;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;

public class EndTurnCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source
	public String version;
	
	public EndTurnCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				Game gameObj = GameInputMapper.find(game);
				
				long userID = (long)helper.getSessionAttribute("userID");
				
				// Check if the user is in the game
				if(userID == gameObj.getP1ID() || userID == gameObj.getP2ID()) {
					
					// Check if the player is in his/her turn
					if(userID == gameObj.getCurrentPlayerID()) {
						
						// Check the size of player's hand
						gameObj.checkPlayerHandSizeLimit(userID);
						
						//System.out.println("ET[0]: User " + userID + " played energy?: " + gameObj.checkPlayerPlayedEnergy(userID));
						
						// Clean energy card playing record
						gameObj.cleanEnergyPlayRecord();
						
						//System.out.println("ET[1]: User " + userID + " played energy?: " + gameObj.checkPlayerPlayedEnergy(userID));
						
						// Update the current player id
						gameObj.endPlayerTurn();
						
						// Draw one card and add it to the current player's hand
						long deckID = gameObj.getPlayerDeckID(gameObj.getCurrentPlayerID());
						int deckIndex = gameObj.getPlayerCurrentDeckIndex();
						Deck deck = DeckInputMapper.find(deckID);
						long newCardID = deck.getCardIdList().get(deckIndex);
						gameObj.addCardToPlayerHand(newCardID);
						
						long newVersion = Long.parseLong(version);
						gameObj.setVersion(newVersion);
						// Update the turn
						gameObj.setTurn(gameObj.getTurn() + 1);
						
						UoW.getCurrent().registerDirty(gameObj);
						UoW.getCurrent().commit();
						
					} else {
						String msg = "Only player who is in his/her turn can end his/her turn";
						throw new CommandException(msg);
					}
					
				} else {
					String msg = "Only player in the game can end turn";
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
