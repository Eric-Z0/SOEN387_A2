package org.dom.model.card.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import org.dom.model.card.Card;
import org.dom.model.card.tdg.CardTDG;


public class CardOutputMapper extends GenericOutputMapper<Long, Card>{
	
	public void delete(Card card) throws MapperException {
		
		try {
			int count = CardTDG.delete(card.getId(), card.getVersion());
			
			if(count == 0) throw new LostUpdateException("CardTDG: id " + card.getId() + " version " + card.getVersion());
			
			card.setVersion(card.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete Card " + card.getId(), e);
		}
		
	}
	
	public void insert(Card card) throws MapperException {
		
		try {
			CardTDG.insert(card.getId(), card.getVersion(), card.getType(), card.getName(), card.getBasicName());
		} catch (SQLException e) {
			throw new MapperException("Could not insert Card " + card.getId(), e);
		}
		
	}
	
	public void update(Card card) throws MapperException {
		
		try {
			int count = CardTDG.update(card.getId(), card.getVersion(), card.getType(), card.getName(), card.getBasicName());
			
			if(count == 0) throw new LostUpdateException("CardTDG: id " + card.getId() + " version " + card.getVersion());
			
			card.setVersion(card.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update Card " + card.getId(), e);
		}
		
	}
	

}