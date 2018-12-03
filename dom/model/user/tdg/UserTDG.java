package org.dom.model.user.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	public final static String BASE_NAME = "Users";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE_NAME;
	
	public final static String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + " ("
			+ "id bigint, "
			+ "version int, "
			+ "name varchar(128), "
			+ "password varchar(128), "
			+ "decks varchar(128)"
			+ ");";

	public final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String GET_MAX_ID = "SELECT max(id) as id FROM " + TABLE + ";";
	
	public final static String DELETE_BYID_SQL = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public final static String INSERT_BYID_SQL = "INSERT INTO " + TABLE + " (id, version, name, password, decks) values(?,?,?,?,?);";
	
	public final static String UPDATE_BYID_SQL = "UPDATE " + TABLE + " SET version=version+1, name=?, password=?, decks=? WHERE id=? and version=?;";
	
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
	
	public static int insert(long id, long version, String name, String password, List<Long> decks) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, name);
		ps.setString(4, password);
		ps.setString(5, decks.toString());
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, String name, String password, List<Long> decks) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BYID_SQL);
		ps.setString(1, name);
		ps.setString(2, password);
		ps.setString(3, decks.toString());
		ps.setLong(4, id);
		ps.setLong(5, version);
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
