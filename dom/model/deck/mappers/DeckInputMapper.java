package org.dom.model.deck.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;

import org.dom.model.card.CardProxy;
import org.dom.model.card.ICard;
import org.dom.model.deck.Deck;
import org.dom.model.deck.DeckFactory;
import org.dom.model.deck.DeckProxy;
import org.dom.model.deck.IDeck;
import org.dom.model.deck.tdg.DeckFinder;


public class DeckInputMapper {
	
	public static List<IDeck> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "deck.id");
	}
	
	public static List<IDeck> buildCollection(ResultSet rs, String idString)
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<IDeck> l = new ArrayList<IDeck>();
		
		while(rs.next()) {
			l.add(new DeckProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<IDeck> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = DeckFinder.findAll();
		return buildCollection(rs);
	}
	
	public static Deck find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, Deck.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = DeckFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this Deck id doesn't exist");
		
		return getDeck(rs);
	}
	
	public static Deck getDeck(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		long id = rs.getLong("deck.id");
		List<Long> cardIdList = genCardList(rs.getString("deck.cardList"));
		
		List<ICard> cards = new ArrayList<ICard>();
		for(long cardID : cardIdList) {
			cards.add(new CardProxy(cardID));
		}
		
		Deck result = DeckFactory.createClean(id, rs.getLong("deck.version"), cards);
		
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