package org.dom.model.card.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardFinder {
	
	public static String SELECT_BY_ID_SQL = "SELECT card.id, card.version, card.type, card.name, card.basicName FROM " + CardTDG.TABLE + " AS card " + "WHERE card.id=?;";
	
	public static String SELECT_BY_NAME_TYPE_SQL = "SELECT card.id, card.version, card.type, card.name, card.basicName FROM " + CardTDG.TABLE + " AS card " + "WHERE card.type=? AND card.name=? AND card.basicName=?;";
	
	public static String SELECT_ALL_SQL = "SELECT card.id FROM " + CardTDG.TABLE + " AS card;";
	
	public static ResultSet find(long id) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet find(String type, String name, String basicName) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME_TYPE_SQL);
		ps.setString(1, type);
		ps.setString(2, name);
		ps.setString(3, basicName);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}

}
