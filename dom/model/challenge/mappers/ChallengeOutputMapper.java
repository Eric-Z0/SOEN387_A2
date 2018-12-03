package org.dom.model.challenge.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import org.dom.model.challenge.Challenge;
import org.dom.model.challenge.tdg.ChallengeTDG;


public class ChallengeOutputMapper extends GenericOutputMapper<Long, Challenge>{
	
	public void delete(Challenge challenge) throws MapperException {
		
		try {
			int count = ChallengeTDG.delete(challenge.getId(), challenge.getVersion());
			
			if(count == 0) throw new LostUpdateException("ChallengeTDG: id " + challenge.getId() + " version " + challenge.getVersion());
			
			challenge.setVersion(challenge.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete Challenge " + challenge.getId(), e);
		}
		
	}
	
	public void insert(Challenge challenge) throws MapperException {
		
		try {
			ChallengeTDG.insert(challenge.getId(), challenge.getVersion(), challenge.getChallengerID(), challenge.getChallengeeID(), challenge.getStatus(), challenge.getChallengerDeckID());
		} catch (SQLException e) {
			throw new MapperException("Could not insert Challenge " + challenge.getId(), e);
		}
		
	}
	
	public void update(Challenge challenge) throws MapperException {
		
		try {
			int count = ChallengeTDG.update(challenge.getId(), challenge.getVersion(), challenge.getChallengerID(), challenge.getChallengeeID(), challenge.getStatus(), challenge.getChallengerDeckID());
			
			if(count == 0) throw new LostUpdateException("ChallengeTDG: id " + challenge.getId() + " version " + challenge.getVersion());
			
			challenge.setVersion(challenge.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update Challenge " + challenge.getId(), e);
		}
		
	}

}