package org.dom.model.card;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;

import org.dom.model.card.mappers.CardInputMapper;



public class CardProxy extends DomainObjectProxy<Long, Card> implements ICard {
	
	public CardProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Card getFromMapper(Long id) throws DomainObjectCreationException {
		try {
			return CardInputMapper.find(id);
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
	public String getType() {
		return getInnerObject().getType();
	}
	
	@Override
	public void setType(String type) {
		getInnerObject().setType(type);
	}
	
	@Override
	public String getBasicName() {
		return getInnerObject().getBasicName();
	}
	
	@Override
	public void setBasicName(String basicName) {
		getInnerObject().setBasicName(basicName);
	}

}
