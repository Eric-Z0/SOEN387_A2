package org.dom.model.game;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.dom.model.game.tdg.GameTDG;


public class GameFactory {
	
	public static Game createNew(
			int turn, long CPID,
			long p1ID, long p1DID, String p1Status, List<Long> p1Hand, int p1Decksize, List<Long> p1DiscardPile, List<Long> p1Bench, int P1EPS,
			long p2ID, long p2DID, String p2Status, List<Long> p2Hand, int p2Decksize, List<Long> p2DiscardPile, List<Long> p2Bench, int P2EPS) throws SQLException, MapperException {
		
		Game result = new Game(GameTDG.getMaxId(), 1, turn, CPID,
				p1ID, p1DID, p1Status, p1Hand, p1Decksize, p1DiscardPile, p1Bench, P1EPS,
				p2ID, p2DID, p2Status, p2Hand, p2Decksize, p2DiscardPile, p2Bench, P2EPS);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static Game createClean(long id, long version, int turn, long CPID,
			long p1ID, long p1DID, String p1Status, List<Long> p1Hand, int p1Decksize, List<Long> p1DiscardPile, List<Long> p1Bench, int P1EPS,
			long p2ID, long p2DID, String p2Status, List<Long> p2Hand, int p2Decksize, List<Long> p2DiscardPile, List<Long> p2Bench, int P2EPS) throws SQLException {
		
		Game result = new Game(id, version, turn, CPID,
				p1ID, p1DID, p1Status, p1Hand, p1Decksize, p1DiscardPile, p1Bench, P1EPS,
				p2ID, p2DID, p2Status, p2Hand, p2Decksize, p2DiscardPile, p2Bench, P2EPS);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
