package org.dom.model.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;

//IChallenge interface only provides methods to access challenge attributes
public interface IChallenge extends IDomainObject<Long> {
	
	public abstract long getChallengerID();
	public abstract void setChallengerID(long challengerID);
	
	public abstract long getChallengeeID();
	public abstract void setChallengeeID(long challengeeID);
	
	public abstract int getStatus();
	public abstract void setStatus(int status);
	
	public abstract long getChallengerDeckID();
	
}
