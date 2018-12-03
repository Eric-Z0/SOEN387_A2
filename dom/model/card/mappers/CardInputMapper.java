package org.dom.model.card.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;

import org.dom.model.card.Card;
import org.dom.model.card.CardFactory;
import org.dom.model.card.CardProxy;
import org.dom.model.card.ICard;
import org.dom.model.card.tdg.CardFinder;

public class CardInputMapper {
	
	public static List<ICard> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "card.id");
	}
	
	public static List<ICard> buildCollection(ResultSet rs, String idString)
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<ICard> l = new ArrayList<ICard>();
		
		while(rs.next()) {
			l.add(new CardProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<ICard> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = CardFinder.findAll();
		return buildCollection(rs);
	}
	
	public static Card find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, Card.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = CardFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this Card id doesn't exist");
		
		return getCard(rs);
	}
	
	public static Card getCard(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		long id = rs.getLong("card.id");
		Card result = CardFactory.createClean(
				id,
				rs.getLong("card.version"),
				rs.getString("card.type"),
				rs.getString("card.name"),
				rs.getString("card.basicName"));
		
		return result;
	}
	
	public static Card find(String type, String name, String basicName) throws SQLException, MapperException {
		
		ResultSet rs = CardFinder.find(type, name, basicName);
		
		if(!rs.next()) throw new MapperException("The record for this Card id doesn't exist");
		
		return getCard(rs);
	}
}