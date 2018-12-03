package org.dom.model.card.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardTDG {
	
	public final static String BASE_NAME = "Cards";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE_NAME;
	
	public final static String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + " ("
			+ "id bigint, "
			+ "version int, "
			+ "type varchar(128), "
			+ "name varchar(128), "
			+ "basicName varchar(128)"
			+ ");";

	public final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String GET_MAX_ID = "SELECT max(id) as id FROM " + TABLE + ";";
	
	public final static String DELETE_BYID_SQL = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public final static String INSERT_BYID_SQL = "INSERT INTO " + TABLE + " (id, version, type, name, basicName) values(?,?,?,?,?);";
	
	public final static String UPDATE_BYID_SQL = "UPDATE " + TABLE + " SET version=version+1, type=?, name=?, basicName=? WHERE id=? and version=?;";
	
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
	
	public static int insert(long id, long version, String type, String name, String basicName) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, type);
		ps.setString(4, name);
		ps.setString(5, basicName);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, String type, String name, String basicName) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, type);
		ps.setString(4, name);
		ps.setString(5, basicName);
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
