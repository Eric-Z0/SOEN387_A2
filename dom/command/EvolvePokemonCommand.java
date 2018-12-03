package org.dom.command;

import org.dom.model.card.Card;
import org.dom.model.card.mappers.CardInputMapper;
import org.dom.model.game.Game;
import org.dom.model.game.GameFactory;
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

public class EvolvePokemonCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source(sources={PermalinkSource.class})
	public long card;
	
	@Source
	public String basic;
	
	@Source
	public String version;
	
	@SetInRequestAttribute
	public String playerName;
	
	public EvolvePokemonCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				Game gameObj = GameInputMapper.find(game);
				
				long userID = (long)helper.getSessionAttribute("userID");
				long stageOnePokemonID = card;
				long basePokemoneID = Long.parseLong(basic);
				
				if(gameObj.isPlayerInGame(userID)) {
					
					if(userID == gameObj.getCurrentPlayerID()) {
						
						if(gameObj.isCardInPlayerHand(userID, stageOnePokemonID)) {
							
							if(gameObj.isPokemonInBench(userID, basePokemoneID)) {
								
								Card SIPCard = CardInputMapper.find(stageOnePokemonID);
								String BPName = SIPCard.getBasicName();
								Card BPCard = CardInputMapper.find(basePokemoneID);
								
								if(BPCard.getName().equals(BPName)) {
									
									gameObj.evolvePokemon(userID, stageOnePokemonID, basePokemoneID);
									
									long newVersion = Long.parseLong(version);
									gameObj.setVersion(newVersion);
									UoW.getCurrent().registerDirty(gameObj);
									UoW.getCurrent().commit();
									
									User user = UserInputMapper.find(userID);
									playerName = user.getName();
									
								} else {
									String msg = "Evolve the wrong pokemon";
									throw new CommandException(msg);
								}
								
							} else {
								String msg = "Base pokemon does not exist on the bench";
								throw new CommandException(msg);
							}
							
						} else {
							String msg = "Player does not have that stage one pokemon card at his/her hand";
							throw new CommandException(msg);
						}
					} else {
						String msg = "Only player in his/her turn can evolve pokemon.";
						throw new CommandException(msg);
					}
					
				} else {
					String msg = "Only player in the game can evolve pokemon.";
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
