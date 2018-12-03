package org.dom.model.bench.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom.model.bench.Bench;
import org.dom.model.bench.BenchFactory;
import org.dom.model.bench.BenchProxy;
import org.dom.model.bench.IBench;
import org.dom.model.bench.tdg.BenchFinder;
import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;


public class BenchInputMapper {
	
	public static List<IBench> buildCollection(ResultSet rs) 
			throws SQLException, MapperException, DomainObjectCreationException {
		
		return buildCollection(rs, "bench.id");
	}
	
	public static List<IBench> buildCollection(ResultSet rs, String idString)
			throws SQLException, MapperException, DomainObjectCreationException {
		
		ArrayList<IBench> l = new ArrayList<IBench>();
		
		while(rs.next()) {
			l.add(new BenchProxy(rs.getLong(idString)));
		}
		
		return l;
	}
	
	public static List<IBench> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = BenchFinder.findAll();
		return buildCollection(rs);
	}
	
	public static Bench find(long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		try {
			return IdentityMap.get(id, Bench.class);
			
		} catch (DomainObjectNotFoundException e) {	
		} catch (ObjectRemovedException e) {
			
		}
		
		ResultSet rs = BenchFinder.find(id);
		
		if(!rs.next()) throw new MapperException("The record for this Bench id doesn't exist");
		
		return getBench(rs);
	}
	
	public static Bench getBench(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		
		Bench result = BenchFactory.createClean(
				rs.getLong("bench.id"),
				rs.getLong("bench.version"),
				rs.getInt("bench.turn"),
				rs.getLong("bench.BPID"),
				rs.getLong("bench.S1PID"),
				genCardList(rs.getString("bench.Energy")));
		
		return result;
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