package org.dom.model.deck;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;

import org.dom.model.card.ICard;
import org.dom.model.deck.tdg.DeckTDG;


public class DeckFactory {
	
	public static Deck createNew(List<ICard> cards) throws SQLException, MapperException {
		Deck result = new Deck(DeckTDG.getMaxId(), 1, cards);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static Deck createClean(long id, long version, List<ICard> cards) throws SQLException {
		Deck result = new Deck(id, version, cards);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
