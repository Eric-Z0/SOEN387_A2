package org.dom.model.deck;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import org.dom.model.card.ICard;
import org.dom.model.deck.mappers.DeckInputMapper;


public class DeckProxy extends DomainObjectProxy<Long, Deck> implements IDeck {
	
	public DeckProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Deck getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return DeckInputMapper.find(id);
		} catch (MapperException | SQLException e) {
			throw new DomainObjectCreationException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<ICard> getCards() {
		return getInnerObject().getCards();
	}
	
	@Override
	public void setCards(List<ICard> cards) {
		getInnerObject().setCards(cards);
	}

}
