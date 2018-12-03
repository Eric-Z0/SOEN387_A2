package org.dom.model.game.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.dom.model.game.Game;
import org.dom.model.game.tdg.GameTDG;


public class GameOutputMapper extends GenericOutputMapper<Long, Game>{
	
	public void delete(Game game) throws MapperException {
		
		try {
			int count = GameTDG.delete(game.getId(), game.getVersion());
			
			if(count == 0) throw new LostUpdateException("GameTDG: id " + game.getId() + " version " + game.getVersion());
			
			game.setVersion(game.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete Game " + game.getId(), e);
		}
		
	}
	
	public void insert(Game game) throws MapperException {
		
		try {
			GameTDG.insert(game.getId(), game.getVersion(), game.getTurn(), game.getCurrentPlayerID(),
					game.getP1ID(), game.getP1DeckID(), game.getP1Status(), game.getP1Hand(), game.getP1Decksize(), game.getP1DiscardPile(), game.getP1BenchIDList(), game.getP1EPS(),
					game.getP2ID(), game.getP2DeckID(), game.getP2Status(), game.getP2Hand(), game.getP2Decksize(), game.getP2DiscardPile(), game.getP2BenchIDList(), game.getP2EPS());
		} catch (SQLException e) {
			throw new MapperException("Could not insert Game " + game.getId(), e);
		}
		
	}
	
	public void update(Game game) throws MapperException {
		
		try {
			int count = GameTDG.update(game.getId(), game.getVersion(), game.getTurn(), game.getCurrentPlayerID(), 
					game.getP1ID(), game.getP1DeckID(), game.getP1Status(), game.getP1Hand(), game.getP1Decksize(), game.getP1DiscardPile(), game.getP1BenchIDList(), game.getP1EPS(),
					game.getP2ID(), game.getP2DeckID(), game.getP2Status(), game.getP2Hand(), game.getP2Decksize(), game.getP2DiscardPile(), game.getP2BenchIDList(), game.getP2EPS());
			
			if(count == 0) throw new LostUpdateException("GameTDG: id " + game.getId() + " version " + game.getVersion());
			
			game.setVersion(game.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update Game " + game.getId(), e);
		}
		
	}
	

}