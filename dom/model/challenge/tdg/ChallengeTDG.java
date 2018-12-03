package org.dom.model.challenge.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeTDG {
	
	public final static String BASE_NAME = "Challenges";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE_NAME;
	
	public final static String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + " ("
			+ "id bigint, "
			+ "version int, "
			+ "challengerID bigint, "
			+ "challengeeID bigint, "
			+ "status int, "
			+ "challengerDeckID bigint"
			+ ");";

	public final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String GET_MAX_ID = "SELECT max(id) as id FROM " + TABLE + ";";
	
	public final static String DELETE_BYID_SQL = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public final static String INSERT_BYID_SQL = "INSERT INTO " + TABLE + " (id, version, challengerID, challengeeID, status, challengerDeckID) values(?,?,?,?,?,?);";
	
	public final static String UPDATE_BYID_SQL = "UPDATE " + TABLE + " SET version=version+1, challengerID=?, challengeeID=?, status=?, challengerDeckID=? WHERE id=? and version=?;";
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE_TABLE);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP_TABLE);
	}
	
	public static long getMaxId() throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(GET_MAX_ID);
		ResultSet rs = SQLLogger.processQuery(ps);
		long maxId = -1L;		
		if(rs.next()) { maxId = rs.getLong("id"); }
		return ++maxId;
	}
	
	public static int insert(long id, long version, long challengerID, long challengeeID, int status, long challengerDeckID) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, challengerID);
		ps.setLong(4, challengeeID);
		ps.setInt(5, status);
		ps.setLong(6, challengerDeckID);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, long challengerID, long challengeeID, int status, long challengerDeckID) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BYID_SQL);
		ps.setLong(1, challengerID);
		ps.setLong(2, challengeeID);
		ps.setInt(3, status);
		ps.setLong(4, challengerDeckID);
		ps.setLong(5, id);
		ps.setLong(6, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int delete(long id, long version) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

}
