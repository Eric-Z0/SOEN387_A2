package org.dom.model.deck.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckFinder {
	
	public static String SELECT_BY_ID_SQL = "SELECT deck.id, deck.version, deck.cardList FROM " + DeckTDG.TABLE + " AS deck " + "WHERE deck.id=?;";
	
	public static String SELECT_ALL_SQL = "SELECT deck.id FROM " + DeckTDG.TABLE + " AS deck;";
	
	public static ResultSet find(long id) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}

}
