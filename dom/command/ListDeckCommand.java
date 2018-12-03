package org.dom.command;

import java.util.ArrayList;
import java.util.List;

import org.dom.model.deck.IDeck;
import org.dom.model.deck.mappers.DeckInputMapper;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;

public class ListDeckCommand extends ValidatorCommand {
	
	@SetInRequestAttribute
	public List<Long> decks;
	
	public ListDeckCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				List<IDeck> deckList = DeckInputMapper.findAll();
				decks = new ArrayList<Long>();
				for(IDeck ideck : deckList) {
					decks.add(ideck.getId());
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
