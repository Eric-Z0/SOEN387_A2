package org.dom.model.challenge.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;


public class ChallengeFinder {
	
	public static String SELECT_BY_ID_SQL = "SELECT challenge.id, challenge.version, challenge.challengerID, challenge.challengeeID, challenge.status, challenge.challengerDeckID FROM " + ChallengeTDG.TABLE + " AS challenge " + "WHERE challenge.id=?;";
	
	public static String SELECT_ALL_SQL = "SELECT challenge.id FROM " + ChallengeTDG.TABLE + " AS challenge;";
	
	public static String SELECT_BY_TWO_IDS_SQL = "SELECT challenge.id, challenge.version, challenge.challengerID, challenge.challengeeID, challenge.status, challenge.challengerDeckID FROM " + ChallengeTDG.TABLE + " AS challenge WHERE challenge.challengerID=? AND challenge.challengeeID=?;";
	
	public static ResultSet find(long id) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet find(long challengerID, long challengeeID) throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_TWO_IDS_SQL);
		ps.setLong(1, challengerID);
		ps.setLong(2, challengeeID);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}

}
