package org.dom.command;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.card.Card;
import org.dom.model.card.CardFactory;
import org.dom.model.card.ICard;
import org.dom.model.card.mappers.CardInputMapper;
import org.dom.model.deck.Deck;
import org.dom.model.deck.DeckFactory;
import org.dom.model.user.User;
import org.dom.model.user.mappers.UserInputMapper;


public class UploadDeckCommand extends ValidatorCommand {

	@Source
	public String deck;
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				final int CARD_NUM_REQ = 40;
				final String CARD_PATTERN = "(.) \"([^\"]*)\"(?: \"([^\"]*)\")?";
				Pattern pattern = Pattern.compile(CARD_PATTERN);
				
				// Test the number of cards in deck meets the requirement or not
				List<ICard> cards = new ArrayList<ICard>();
				String[] cardListStr = deck.split("\n");
				
				for(String cardStr : cardListStr) {
					Matcher m = pattern.matcher(cardStr);
					m.find();
					String type = m.group(1);
					String name = m.group(2);
					String basicName = m.group(3);
					
					if(basicName == null) {
						basicName = "";
					}
					
					/*
					// check if the card exists or not
					Card card = null;
					try {
						card = CardInputMapper.find(type, name, basicName);
					} catch (MapperException e) {
						card = CardFactory.createNew(type, name, basicName);
						UoW.getCurrent().commit();
					}
					*/
					
					// quick solution but not that good
					Card card = CardFactory.createNew(type, name, basicName);
					UoW.getCurrent().commit();
					
					cards.add(card);
				}
				
				if(cards.size() > CARD_NUM_REQ) {
					String msg = "The size of deck is greater than 40.";
					throw new CommandException(msg);
				} else if(cards.size() < CARD_NUM_REQ) {
					String msg = "The size of deck is less than 40.";
					throw new CommandException(msg);
				} else {
					Deck deckObj = DeckFactory.createNew(cards);
					long deckID = deckObj.getId();
					
					long userID = (long)helper.getSessionAttribute("userID");
					User user = UserInputMapper.find(userID);
					user.addDeckToUserDeckList(deckID);
					
					UoW.getCurrent().registerDirty(user);	// mark user as dirty to let UoW update it
					UoW.getCurrent().commit();
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
