package org.dom.model.card;

import org.dsrg.soenea.domain.interf.IDomainObject;

//ICard interface only provides methods to access card attributes
public interface ICard extends IDomainObject<Long> {
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract String getType();
	
	public abstract void setType(String type);
	
	public abstract String getBasicName();
	
	public abstract void setBasicName(String basicName);

}
