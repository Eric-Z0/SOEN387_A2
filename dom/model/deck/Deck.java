package org.dom.model.deck;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

import org.dom.model.card.ICard;

public class Deck extends DomainObject<Long> implements IDeck {
	
	private List<ICard> cards;
	
	protected Deck(Long id, long version, List<ICard> cards) {
		super(id, version);
		this.cards = cards;	
	}
	
	@Override
	public List<ICard> getCards() {
		return this.cards;
	}
	
	@Override
	public void setCards(List<ICard> cards) {
		this.cards = cards;
	}

	public List<Long> getCardIdList(){
		List<Long> cardIdList = new ArrayList<Long>();
		for(ICard icard : cards) {
			cardIdList.add(icard.getId());
		}
		return cardIdList;
	}
}
