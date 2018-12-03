package org.dom.model.challenge.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;

import org.dom.model.challenge.Challenge;
import org.dom.model.challenge.ChallengeFactory;
import org.dom.model.challenge.ChallengeProxy;
import org.dom.model.challenge.IChallenge;
import org.dom.model.challenge.tdg.ChallengeFinder;



public class ChallengeInputMapper {
	
	public static List<IChallenge> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "challenge.id");
	}
	
	public static List<IChallenge> buildCollection(ResultSet rs, String idString)
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<IChallenge> l = new ArrayList<IChallenge>();
		
		while(rs.next()) {
			l.add(new ChallengeProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<IChallenge> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = ChallengeFinder.findAll();
		return buildCollection(rs);
	}
	
	public static Challenge find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, Challenge.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = ChallengeFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this Challenge id doesn't exist");
		
		return getChallenge(rs);
	}
	
	public static Challenge find(long challengerID, long challengeeID) throws SQLException, MapperException, DomainObjectCreationException {
		
		ResultSet rs = ChallengeFinder.find(challengerID, challengeeID);
		
		if(!rs.next()) throw new MapperException("Challenge with that challenger and challengee doesn't exist");
		
		try {
			return IdentityMap.get(rs.getLong("challenge.id"), Challenge.class);
		} catch (DomainObjectNotFoundException e) {
			
		}
		
		return getChallenge(rs);
	}
	
	public static Challenge getChallenge(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		long id = rs.getLong("challenge.id");
		Challenge result = ChallengeFactory.createClean(
				id,
				rs.getLong("challenge.version"),
				rs.getLong("challenge.challengerID"),
				rs.getLong("challenge.challengeeID"),
				rs.getInt("challenge.status"),
				rs.getLong("challenge.challengerDeckID"));
		
		return result;
	}

}