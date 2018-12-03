package org.dom.command;

import org.dom.model.card.Card;
import org.dom.model.card.mappers.CardInputMapper;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;

public class PlayOptionAnalysisCommand extends ValidatorCommand {

	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source(sources={PermalinkSource.class})
	public long card;
	
	@Source
	public String version;
	
	@SetInRequestAttribute
	public String dispatcherName;
	
	public PlayOptionAnalysisCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			Card cardObj = null;
			
			try {
				cardObj = CardInputMapper.find(card);
			} catch(MapperException e) {
				String msg = "Invalid Card.";
				throw new CommandException(msg);
			}
			
			String cardType = cardObj.getType();
			
			if(cardType.equals("p")) {
				
				if(cardObj.getBasicName().isEmpty()) {
					dispatcherName = "org.app.dispatcher.PlayPokemonToBench";
				} else {
					dispatcherName = "org.app.dispatcher.EvolvePokemon";
				}
				
			} else if(cardType.equals("e")) {
				
				dispatcherName = "org.app.dispatcher.AttachEnergy";
				
			} else if(cardType.equals("t")) {
				
				dispatcherName = "org.app.dispatcher.PlayTrainer";
				
			} else {
				String msg = "Un defined card type.";
				throw new CommandException(msg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}
}
