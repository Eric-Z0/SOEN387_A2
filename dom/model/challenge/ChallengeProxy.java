package org.dom.model.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import org.dom.model.challenge.mappers.ChallengeInputMapper;


public class ChallengeProxy extends DomainObjectProxy<Long, Challenge> implements IChallenge {
	
	public ChallengeProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Challenge getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return ChallengeInputMapper.find(id);
		} catch (MapperException | SQLException e) {
			throw new DomainObjectCreationException(e.getMessage(), e);
		}
	}
	
	@Override
	public long getChallengerID() {
		return getInnerObject().getChallengerID();
	}
	
	@Override
	public void setChallengerID(long challengerID) {
		getInnerObject().setChallengerID(challengerID);
	}
	
	@Override
	public long getChallengeeID() {
		return getInnerObject().getChallengeeID();
	}
	
	@Override
	public void setChallengeeID(long challengerID) {
		getInnerObject().setChallengeeID(challengerID);
	}
	
	@Override
	public int getStatus() {
		return getInnerObject().getStatus();
	}
	
	@Override
	public void setStatus(int status) {
		getInnerObject().setStatus(status);
	}
	
	@Override
	public long getChallengerDeckID() {
		return getInnerObject().getChallengerDeckID();
	}

}
