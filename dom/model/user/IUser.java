package org.dom.model.user;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;

// IUser interface only provides methods to access user attributes
public interface IUser extends IDomainObject<Long> {
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract List<Long> getDecks();
	
	public abstract void setDecks(List<Long> decks);

	public abstract boolean userHasDeck(long deckID);
	
	public abstract void addDeckToUserDeckList(long deckID);
}
