package org.dom.model.deck;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;

import org.dom.model.card.ICard;

//ICard interface only provides methods to access card attributes
public interface IDeck extends IDomainObject<Long> {
	
	public List<ICard> getCards();
	
	public void setCards(List<ICard> cards);

}
