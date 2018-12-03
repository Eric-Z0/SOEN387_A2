package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom.model.deck.Deck;
import org.dom.model.deck.mappers.DeckInputMapper;


public class ViewDeckCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long deck;
	
	@SetInRequestAttribute
	public Deck deckObj;
	
	@SetInRequestAttribute
	public Iterator<Integer> cardIDItr;
	
	public ViewDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				deckObj = DeckInputMapper.find(deck);
				
				/*
				List<Integer> cardIDArray = new ArrayList<Integer>();
				for(int index = 1; index <= 40; index++) {
					cardIDArray.add(index);
				}
				cardIDItr = cardIDArray.iterator();
				*/
				
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
