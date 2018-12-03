package org.dom.model.card;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;

import org.dom.model.card.tdg.CardTDG;


public class CardFactory {
	
	public static Card createNew(String type, String name, String basicName) throws SQLException, MapperException {
		Card result = new Card(CardTDG.getMaxId(), 1, type, name, basicName);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static Card createClean(long id, long version, String type, String name, String basicName) throws SQLException {
		Card result = new Card(id, version, type, name, basicName);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
