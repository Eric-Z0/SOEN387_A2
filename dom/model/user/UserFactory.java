package org.dom.model.user;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;

import org.dom.model.user.tdg.UserTDG;

public class UserFactory {
	
	public static User createNew(String name, String password, List<Long> decks) throws SQLException, MapperException {
		User result = new User(UserTDG.getMaxId(), 1, name, password, decks);
		UoW.getCurrent().registerNew(result);
	
		return result;
	}
	
	public static User createClean(long id, long version, String name, String password, List<Long> decks) throws SQLException {
		User result = new User(id, version, name, password, decks);
		UoW.getCurrent().registerClean(result);
		return result;
	}

}
