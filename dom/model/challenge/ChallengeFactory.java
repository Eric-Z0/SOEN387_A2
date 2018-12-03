package org.dom.model.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;

import org.dom.model.challenge.tdg.ChallengeTDG;


public class ChallengeFactory {
	
	public static Challenge createNew(long challengerID, long challengeeID, int status, long challengerDeckID) throws SQLException, MapperException {
		Challenge result = new Challenge(ChallengeTDG.getMaxId(), 1, challengerID, challengeeID, status, challengerDeckID);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static Challenge createClean(long id, long version, long challengerID, long challengeeID, int status, long challengerDeckID) throws SQLException {
		Challenge result = new Challenge(id, version, challengerID, challengeeID, status, challengerDeckID);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
