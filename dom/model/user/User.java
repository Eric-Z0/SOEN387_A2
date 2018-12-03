package org.dom.model.user;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

public class User extends DomainObject<Long> implements IUser{
	
	private String name;
	private String password;
	private List<Long> decks;
	
	protected User(Long id, long version, String name, String password, List<Long> decks) {
		super(id, version);
		this.name = name;
		this.password = password;
		this.decks = decks;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public List<Long> getDecks() {
		List<Long> temp = new ArrayList<Long>();
		for(long deckID : this.decks) {
			temp.add(deckID);
		}
		return temp;
	}
	
	@Override
	public void setDecks(List<Long> decks) {
		this.decks = decks;
	}
	
	@Override
	public boolean userHasDeck(long deckID) {
		return this.decks.contains(deckID);
	}
	
	@Override
	public void addDeckToUserDeckList(long deckID) {
		this.decks.add(deckID);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
