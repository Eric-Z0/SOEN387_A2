package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.game.Game;
import org.dom.model.game.GameFactory;
import org.dom.model.game.mappers.GameInputMapper;
import org.dom.model.user.User;
import org.dom.model.user.mappers.UserInputMapper;


public class RetireCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long game;
	
	@Source
	public String version;
	
	@SetInRequestAttribute
	public String playerName;
	
	public RetireCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {

				Game gameObj = GameInputMapper.find(game);
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(gameObj.isPlayerInGame(userID)) {
					
					if(userID == gameObj.getP1ID()) {
						gameObj.setP1Status("retired");	
					} else {
						gameObj.setP2Status("retired");
					}
					
					User user = UserInputMapper.find(userID);
					playerName = user.getName();
					
					// update game object
					long newVersion = Long.parseLong(version);
					gameObj.setVersion(newVersion);
					
					UoW.getCurrent().registerDirty(gameObj);
					UoW.getCurrent().commit();
					
				} else {
					String msg = "Only player in the game can retire.";
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
