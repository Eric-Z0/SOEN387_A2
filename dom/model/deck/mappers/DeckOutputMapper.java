package org.dom.model.deck.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import org.dom.model.deck.Deck;
import org.dom.model.deck.tdg.DeckTDG;


public class DeckOutputMapper extends GenericOutputMapper<Long, Deck>{
	
	public void delete(Deck deck) throws MapperException {
		
		try {
			int count = DeckTDG.delete(deck.getId(), deck.getVersion());
			
			if(count == 0) throw new LostUpdateException("DeckTDG: id " + deck.getId() + " version " + deck.getVersion());
			
			deck.setVersion(deck.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete Deck " + deck.getId(), e);
		}
		
	}
	
	public void insert(Deck deck) throws MapperException {
		
		try {
			DeckTDG.insert(deck.getId(), deck.getVersion(), deck.getCardIdList());
		} catch (SQLException e) {
			throw new MapperException("Could not insert Deck " + deck.getId(), e);
		}
		
	}
	
	public void update(Deck deck) throws MapperException {
		
		try {
			int count = DeckTDG.update(deck.getId(), deck.getVersion(), deck.getCardIdList());
			
			if(count == 0) throw new LostUpdateException("DeckTDG: id " + deck.getId() + " version " + deck.getVersion());
			
			deck.setVersion(deck.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update Deck " + deck.getId(), e);
		}
		
	}
	

}