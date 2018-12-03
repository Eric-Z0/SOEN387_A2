package org.dom.command;

import org.dom.model.card.Card;
import org.dom.model.card.mappers.CardInputMapper;
import org.dom.model.game.Game;
import org.dom.model.game.mappers.GameInputMapper;
import org.dom.model.user.User;
import org.dom.model.user.mappers.UserInputMapper;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;

public class PlayTrainerCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source(sources={PermalinkSource.class})
	public long card;
	
	@Source
	public String version;
	
	@SetInRequestAttribute
	public String playerName;
	
	public PlayTrainerCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				Game gameObj = GameInputMapper.find(game);
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(gameObj.isPlayerInGame(userID)) {
					
					if(userID == gameObj.getCurrentPlayerID()) {
						
						Card cardObj = CardInputMapper.find(card);
						String cardType = cardObj.getType();
						
						if(cardType.equals("t")) {
							
							if(userID == gameObj.getP1ID()) {
								gameObj.playTrainerP1(card);
							} else {
								gameObj.playTrainerP2(card);
							}
							
							User user = UserInputMapper.find(userID);
							playerName = user.getName();
							
							// Update Game data
							long newVersion = Long.parseLong(version);
							gameObj.setVersion(newVersion);
							UoW.getCurrent().registerDirty(gameObj);
							UoW.getCurrent().commit();
							
						} else {
							String msg = "The card type needs to be trainer.";
							throw new CommandException(msg);
						}
						
					} else {
						String msg = "Only player in his/her turn can play trainer.";
						throw new CommandException(msg);
					}
					
				} else {
					String msg = "Only player in the game can play trainer.";
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
