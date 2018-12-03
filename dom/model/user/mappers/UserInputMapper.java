package org.dom.model.user.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;

import org.dom.model.user.IUser;
import org.dom.model.user.User;
import org.dom.model.user.UserFactory;
import org.dom.model.user.UserProxy;
import org.dom.model.user.tdg.UserFinder;

public class UserInputMapper {
	
	public static List<IUser> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "user.id");
	}
	
	public static List<IUser> buildCollection(ResultSet rs, String idString) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<IUser> l = new ArrayList<IUser>();
		
		while(rs.next()) {
			l.add(new UserProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<IUser> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = UserFinder.findAll();
		return buildCollection(rs);
	}
	
	public static User findByName(String name) throws SQLException, MapperException, DomainObjectCreationException {
		
		ResultSet rs = UserFinder.findByName(name);
		
		if(!rs.next()) throw new MapperException("The record for this User id doesn't exist");
		
		try {
			return IdentityMap.get(rs.getLong("user.id"), User.class);
		} catch (DomainObjectNotFoundException e) {
			
		} catch (ObjectRemovedException e) {
				
		}
		
		return getUser(rs);
	}
	
	public static User find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, User.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = UserFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this User id doesn't exist");
		
		return getUser(rs);
	}
	
	public static User getUser(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		long id = rs.getLong("user.id");
		User result = UserFactory.createClean(id, rs.getLong("user.version"), rs.getString("user.name"), rs.getString("user.password"), genCardList(rs.getString("user.decks")));

		return result;
	}
	
	public static User find(String username, String password) throws SQLException, MapperException {
		
		ResultSet rs = UserFinder.find(username, password);
		
		if(!rs.next()) throw new MapperException("User with that Username/Password doesn't exist!");
		
		try {
			return IdentityMap.get(rs.getLong("user.id"), User.class);
		} catch (DomainObjectNotFoundException e) {
		}
		
		return getUser(rs);	
	}
	
	public static List<Long> genCardList(String cardArrStr){
		
		List<Long> cardList = new ArrayList<Long>();
		
		if(cardArrStr.equals("[]")) {
			return cardList;
		} else {
			cardArrStr = cardArrStr.replaceAll("[\\[\\]]", "");
			String[] cardListStr = cardArrStr.split(",");
			
			for(String cardIDStr : cardListStr) {
				cardIDStr = cardIDStr.trim();
				long cardID = Long.parseLong(cardIDStr);
				cardList.add(cardID);
			}
			return cardList;
		}
	}
	
	
}