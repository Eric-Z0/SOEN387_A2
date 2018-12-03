package org.dom.command;

import org.dsrg.soenea.domain.MapperException;
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
import org.dom.model.user.User;
import org.dom.model.user.mappers.UserInputMapper;


public class ChallengePlayerCommand extends ValidatorCommand {

	@Source(sources={PermalinkSource.class})
	public long player;
	
	@Source
	public String deck;
	
	public ChallengePlayerCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				long challengerID = (long)helper.getSessionAttribute("userID");
				long challengeeID = player;
				
				// Check if two users exist
				User p1 = UserInputMapper.find(challengerID);
				User p2 = UserInputMapper.find(challengeeID);
				
				// Check if the challenger provides a deck
				long challengerDeckID = Long.parseLong(deck);
				
				try {
					Deck deckObj = DeckInputMapper.find(challengerDeckID);  
				} catch(MapperException e) {
					String msg = "You cannot issue a challenge with a deck that does not exist.";
					throw new CommandException(msg);
				}
				
				// Check if the challenger challenges himself/herself
				if(p1.getId() == p2.getId()) {
					String msg = "You cannot challenge yourself.";
					throw new CommandException(msg);
				} 
				
				// Check if the challenger has a valid deck
				if (p1.userHasDeck(challengerDeckID)){
					
					try {
						// Check if the challenger has already challenged the same challengee
						Challenge ch = ChallengeInputMapper.find(challengerID, challengeeID);
						if(ch.getStatus() == 0) {
							String msg = "You have already challenged that player.";
							throw new CommandException(msg);
						}
					} catch (MapperException e) {}
					
					ChallengeFactory.createNew(challengerID, challengeeID, 0, challengerDeckID);
					UoW.getCurrent().commit();
					
				} else {
					//System.out.println("challenger deck: " + p1.getDecks().toString());
					String msg = "You cannot issue a challenge with a deck that is not uploaded by you.";
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
