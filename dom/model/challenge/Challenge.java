package org.dom.model.challenge;

import org.dsrg.soenea.domain.DomainObject;

public class Challenge extends DomainObject<Long> implements IChallenge {
	
	private long challengerID;
	private long challengeeID;
	private int status;
	private long challengerDeckID;

	protected Challenge(Long id, long version, long challengerID, long challengeeID, int status, long challengerDeckID) {
		super(id, version);
		this.challengerID = challengerID;
		this.challengeeID = challengeeID;
		this.status = status;
		this.challengerDeckID = challengerDeckID;
	}
	
	@Override
	public long getChallengerID() {
		return this.challengerID;
	}
	
	@Override
	public void setChallengerID(long challengerID) {
		this.challengerID = challengerID;
	}
	
	@Override
	public long getChallengeeID() {
		return this.challengeeID;
	}
	
	@Override
	public void setChallengeeID(long challengeeID) {
		this.challengeeID = challengeeID;
	}
	
	@Override
	public int getStatus() {
		return this.status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public long getChallengerDeckID() {
		return this.challengerDeckID;
	}
	
}
