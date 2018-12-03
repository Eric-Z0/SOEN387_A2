package org.dom.model.user;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import org.dom.model.user.mappers.UserInputMapper;

public class UserProxy extends DomainObjectProxy<Long, User> implements IUser {
	
	public UserProxy(Long id) {
		super(id);
	}
	
	@Override
	protected User getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return UserInputMapper.find(id);
		} catch (MapperException | SQLException e) {
			throw new DomainObjectCreationException(e.getMessage(), e);
		}
	}
	
	@Override
	public String getName() {
		return getInnerObject().getName();
	}
	
	@Override
	public void setName(String name) {
		getInnerObject().setName(name);
	}
	
	@Override
	public List<Long> getDecks() {
		return getInnerObject().getDecks();
	}
	
	@Override
	public void setDecks(List<Long> decks) {
		getInnerObject().setDecks(decks);
	}
	
	@Override
	public boolean userHasDeck(long deckID) {
		return getInnerObject().userHasDeck(deckID);
	}
	
	@Override
	public void addDeckToUserDeckList(long deckID) {
		getInnerObject().addDeckToUserDeckList(deckID);
	}

}
