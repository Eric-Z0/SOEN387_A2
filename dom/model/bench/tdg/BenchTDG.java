package org.dom.model.bench.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class BenchTDG {
	
	public final static String BASE_NAME = "Benchs";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE_NAME;
	
	public final static String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + " ("
			+ "id bigint, "
			+ "version int, "
			+ "turn int, "
			+ "BPID bigint, "
			+ "S1PID bigint, "
			+ "Energy varchar(128)"
			+ ");";

	public final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String GET_MAX_ID = "SELECT max(id) as id FROM " + TABLE + ";";
	
	public final static String DELETE_BYID_SQL = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public final static String INSERT_BYID_SQL = "INSERT INTO " + TABLE + " (id, version, turn, BPID, S1PID, Energy) values(?,?,?,?,?,?);";
	
	public final static String UPDATE_BYID_SQL = "UPDATE " + TABLE + " SET version=version+1, turn=?, BPID=?, S1PID=?, Energy=? WHERE id=? and version=?;";
	
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
	
	public static int insert(long id, long version, int turn, long BPID, long S1PID, List<Long> Energy) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BYID_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setInt(3, turn);
		ps.setLong(4, BPID);
		ps.setLong(5, S1PID);
		ps.setString(6, Energy.toString());
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, int turn, long BPID, long S1PID, List<Long> Energy) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BYID_SQL);
		ps.setInt(1, turn);
		ps.setLong(2, BPID);
		ps.setLong(3, S1PID);
		ps.setString(4, Energy.toString());
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
