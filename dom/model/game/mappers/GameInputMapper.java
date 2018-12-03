package org.dom.model.game.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom.model.game.Game;
import org.dom.model.game.GameFactory;
import org.dom.model.game.GameProxy;
import org.dom.model.game.IGame;
import org.dom.model.game.tdg.GameFinder;
import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;



public class GameInputMapper {
	
	public static List<IGame> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "game.id");
	}
	
	public static List<IGame> buildCollection(ResultSet rs, String idString)
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<IGame> l = new ArrayList<IGame>();
		
		while(rs.next()) {
			l.add(new GameProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<IGame> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = GameFinder.findAll();
		return buildCollection(rs);
	}
	
	public static Game find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, Game.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = GameFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this Play id doesn't exist");
		
		return getGame(rs);
	}
	
	public static Game getGame(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		Game result = GameFactory.createClean(
				rs.getLong("id"),
				rs.getLong("version"),
				rs.getInt("turn"),
				rs.getLong("CPlayerID"),
				rs.getLong("P1ID"),
				rs.getLong("P1DeckID"),
				rs.getString("P1Status"),
				genCardList(rs.getString("P1Hand")),
				rs.getInt("P1Decksize"),
				genCardList(rs.getString("P1Discard")),
				genCardList(rs.getString("P1Bench")),
				rs.getInt("P1EPS"),
				rs.getLong("P2ID"),
				rs.getLong("P2DeckID"),
				rs.getString("P2Status"),
				genCardList(rs.getString("P2Hand")),
				rs.getInt("P2Decksize"),
				genCardList(rs.getString("P2Discard")),
				genCardList(rs.getString("P2Bench")),
				rs.getInt("P2EPS"));
		
		return result;
	}
	
	public static List<Long> genCardList(String cardArrStr){
		
		List<Long> cardList = new ArrayList<Long>();
		
		if(cardArrStr.equals("[]")) {
			return cardList;
		} else {
			cardArrStr = cardArrStr.replaceAll("[\\[\\]]", "");
			String[] cardListStr = cardArrStr.split(",");
			
			for(String cardIDStr : cardListStr) {
				cardIDStr = cardIDStr.trim();
				long cardID = Long.parseLong(cardIDStr);
				cardList.add(cardID);
			}
			return cardList;
		}
	}
	

}