package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;

import org.dom.model.deck.Deck;
import org.dom.model.deck.mappers.DeckInputMapper;
import org.dom.model.game.Game;
import org.dom.model.game.mappers.GameInputMapper;


public class DrawCardCommand extends ValidatorCommand {

	@Source
	public String game;
	
	public DrawCardCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				long gameID = Long.parseLong(game);
				Game gameObj = GameInputMapper.find(gameID);
				
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(userID == gameObj.getP1ID()) {
					
					long deckID = gameObj.getDeckIDs()[0];
					Deck deck = DeckInputMapper.find(deckID);
					int deckIndex = gameObj.getP1DeckIndex();
					long cardID = deck.getCardIdList().get(deckIndex);
					gameObj.addCardToP1Hand(cardID);
					
				} else if(userID == gameObj.getP2ID()) {
					
					long deckID = gameObj.getDeckIDs()[1];
					Deck deck = DeckInputMapper.find(deckID);
					int deckIndex = gameObj.getP2DeckIndex();
					long cardID = deck.getCardIdList().get(deckIndex);
					gameObj.addCardToP2Hand(cardID);
					
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
