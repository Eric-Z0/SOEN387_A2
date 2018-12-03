package org.dom.model.user.mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

import org.dom.model.user.User;
import org.dom.model.user.tdg.UserTDG;

public class UserOutputMapper extends GenericOutputMapper<Long, User>{
	
	public void delete(User user) throws MapperException {
		
		try {
			int count = UserTDG.delete(user.getId(), user.getVersion());
			
			if(count == 0) throw new LostUpdateException("UserTDG: id " + user.getId() + " version " + user.getVersion());
			
			user.setVersion(user.getVersion() + 1);
				
		} catch (SQLException | LostUpdateException e) {
			throw new MapperException("Could not delete User " + user.getId(), e);
		}
		
	}
	
	public void insert(User user) throws MapperException {
		
		try {
			UserTDG.insert(user.getId(), user.getVersion(), user.getName(), user.getPassword(), user.getDecks());
		} catch (SQLException e) {
			throw new MapperException("Could not insert User " + user.getId(), e);
		}
		
	}
	
	public void update(User user) throws MapperException {
		
		try {
			int count = UserTDG.update(user.getId(), user.getVersion(), user.getName(), user.getPassword(), user.getDecks());
			
			if(count == 0) throw new LostUpdateException("UserTDG: id " + user.getId() + " version " + user.getVersion());
			
			user.setVersion(user.getVersion() + 1);
			
		} catch (SQLException e) {
			throw new MapperException("Could not update User " + user.getId(), e);
		}
		
	}
	
}