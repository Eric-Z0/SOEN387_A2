package org.dom.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.challenge.Challenge;
import org.dom.model.challenge.ChallengeFactory;
import org.dom.model.challenge.mappers.ChallengeInputMapper;
import org.dom.model.deck.Deck;
import org.dom.model.deck.mappers.DeckInputMapper;
import org.dom.model.game.Game;
import org.dom.model.game.GameFactory;


public class AcceptChallengeCommand extends ValidatorCommand {

	@Source(sources={PermalinkSource.class})
	public long challenge;
	
	@Source
	public String deck;
	
	@Source
	public String version;
	
	public AcceptChallengeCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				long userID = (long)helper.getSessionAttribute("userID");
				Challenge ch = ChallengeInputMapper.find(challenge);
				
				// If the challenge has already been accepted
				if(ch.getStatus() != 0) {
					String msg = "You cannot accept a challenge the status of which is not open.";
					throw new CommandException(msg);
				} else {
								
					long challengerID = ch.getChallengerID();
					long challengeeID = ch.getChallengeeID();
					
					if(challengerID == userID) {
						String msg = "You cannot accept a challenge that is issued by yourself.";
						throw new CommandException(msg);
					} else if(challengeeID != userID) {
						String msg = "You cannot accept a challenge that you are not included.";
						throw new CommandException(msg);
					} else {
									
						// If everything is good then create a new game and update the challenge
						long p1DeckID = ch.getChallengerDeckID();
						long p2DeckID = Long.parseLong(deck);
						
						// Check validity of player2 / challengee deck 
						Deck p2Deck = DeckInputMapper.find(p2DeckID);
						
						// Create a new game
						Game gameObj = GameFactory.createNew(1, challengerID, challengerID, p1DeckID, "playing", new ArrayList<Long>(), 40, new ArrayList<Long>(), new ArrayList<Long>(), 0, 
								challengeeID, p2DeckID, "playing", new ArrayList<Long>(), 40, new ArrayList<Long>(), new ArrayList<Long>(), 0);
						
						// Start game: draw one card and add it to current player's hand
						long deckID = gameObj.getPlayerDeckID(gameObj.getCurrentPlayerID());
						int deckIndex = gameObj.getPlayerCurrentDeckIndex();
						Deck deck = DeckInputMapper.find(deckID);
						long newCardID = deck.getCardIdList().get(deckIndex);
						gameObj.addCardToPlayerHand(newCardID);
						
						long newVersion = Long.parseLong(version);
						
						// Update the challenge status
						ch.setStatus(3);
						ch.setVersion(newVersion);
						UoW.getCurrent().registerDirty(ch);
						UoW.getCurrent().registerDirty(gameObj);
						UoW.getCurrent().commit();
					}
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
