package org.dom.model.card;

import org.dsrg.soenea.domain.DomainObject;

public class Card extends DomainObject<Long> implements ICard {
	
	private String name;
	private String type;
	private String basicName;
	
	protected Card(Long id, long version, String type, String name, String basicName) {
		super(id, version);
		this.type = type;
		this.name = name;
		this.basicName = basicName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getBasicName() {
		return this.basicName;
	}
	
	@Override
	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}
}
