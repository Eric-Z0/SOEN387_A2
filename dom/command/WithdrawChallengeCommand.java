package org.dom.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.challenge.Challenge;
import org.dom.model.challenge.ChallengeFactory;
import org.dom.model.challenge.mappers.ChallengeInputMapper;

public class WithdrawChallengeCommand extends ValidatorCommand {
	
	@Source(sources={PermalinkSource.class})
	public long challenge;
	
	@Source
	public String version;
	
	public WithdrawChallengeCommand(Helper helper) {
		super(helper);
	}
	
	@Override
	public void process() throws CommandException {
		
		try {
			
			if(helper.getSessionAttribute("userID") != null) {
				
				long challengeID = challenge;
				Challenge ch = ChallengeInputMapper.find(challengeID);
				
				if(ch.getStatus() != 0) {
					String msg = "You cannot withdraw a challenge the status of which is not open.";
					throw new CommandException(msg);	
				}
				
				long userID = (long)helper.getSessionAttribute("userID");
				
				if(userID == ch.getChallengerID()) {
					int newVersion = Integer.parseInt(version);
					ch.setStatus(2);
					ch.setVersion(newVersion);
					UoW.getCurrent().registerDirty(ch);
					UoW.getCurrent().commit();
					//ChallengeFactory.createClean(ch.getId(), ch.getVersion(), ch.getChallengerID(), ch.getChallengeeID(), ch.getStatus(), ch.getChallengerDeckID());
				} else if(userID == ch.getChallengeeID()) {
					String msg = "Only Challenger can withdraw a challenge.";
					throw new CommandException(msg);
				} else {
					String msg = "You cannot withdraw a challenge that you are not included.";
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
