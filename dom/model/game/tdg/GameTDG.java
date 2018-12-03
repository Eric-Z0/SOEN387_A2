package org.dom.model.game.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameTDG {
	
	public final static String BASE_NAME = "Games";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE_NAME;
	
	public final static String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + " ("
			+ "id bigint, "
			+ "version int, "
			+ "turn int, "
			+ "CPlayerID bigint, "
			+ "P1ID bigint, P1Status varchar(128), P1Hand varchar(128), P1Decksize int, P1Discard varchar(128), P1DeckID bigint, P1Bench varchar(128), P1EPS int, "
			+ "P2ID bigint, P2Status varchar(128), P2Hand varchar(128), P2Decksize int, P2Discard varchar(128), P2DeckID bigint, P2Bench varchar(128), P2EPS int"
			+ ");";

	public final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String GET_MAX_ID = "SELECT max(id) as id FROM " + TABLE + ";";
	
	public final static String DELETE_BYID_SQL = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public final static String INSERT_BYID_SQL = "INSERT INTO " + TABLE + " (id, version, turn, CPlayerID,"
			+ " P1ID, P1Status, P1Hand, P1Decksize, P1Discard, P1DeckID, P1Bench, P1EPS,"
			+ " P2ID, P2Status, P2Hand, P2Decksize, P2Discard, P2DeckID, P2Bench, P2EPS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
	public final static String UPDATE_BYID_SQL = "UPDATE " + TABLE + " SET version = version + 1, turn = ?, CPlayerID = ?, P1ID = ? , P1Status = ? , P1Hand = ? , P1Decksize = ? , P1Discard = ? , P1DeckID = ? , P1Bench = ? , P1EPS = ? , P2ID = ? , P2Status = ? , P2Hand = ? , P2Decksize = ? , P2Discard = ? , P2DeckID = ? , P2Bench = ? , P2EPS = ? WHERE id = ? and version = ?;";
	
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
	
	public static int insert(long id, long version, int turn, long CPID,
			long P1ID, long P1DID, String P1Status, List<Long> P1Hand, int P1Decksize, List<Long> P1Discard, List<Long> P1Bench, int P1EPS,
			long P2ID, long P2DID, String P2Status, List<Long> P2Hand, int P2Decksize, List<Long> P2Discard, List<Long> P2Bench, int P2EPS) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BYID_SQL);
		
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setInt(3, turn);
		ps.setLong(4, CPID);
		
		ps.setLong(5, P1ID);
		ps.setString(6, P1Status);
		ps.setString(7, P1Hand.toString());
		ps.setInt(8, P1Decksize);
		ps.setString(9, P1Discard.toString());
		ps.setLong(10, P1DID);
		ps.setString(11, P1Bench.toString());
		ps.setInt(12, P1EPS);
		
		ps.setLong(13, P2ID);
		ps.setString(14, P2Status);
		ps.setString(15, P2Hand.toString());
		ps.setInt(16, P2Decksize);
		ps.setString(17, P2Discard.toString());
		ps.setLong(18, P2DID);
		ps.setString(19, P2Bench.toString());
		ps.setInt(20, P2EPS);
		
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, int turn, long CPID,
			long P1ID, long P1DID, String P1Status, List<Long> P1Hand, int P1Decksize, List<Long> P1Discard, List<Long> P1Bench, int P1EPS,
			long P2ID, long P2DID, String P2Status, List<Long> P2Hand, int P2Decksize, List<Long> P2Discard, List<Long> P2Bench, int P2EPS) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BYID_SQL);
		
		ps.setInt(1, turn);
		ps.setLong(2, CPID);
		
		ps.setLong(3, P1ID);
		ps.setString(4, P1Status);
		ps.setString(5, P1Hand.toString());
		ps.setInt(6, P1Decksize);
		ps.setString(7, P1Discard.toString());
		ps.setLong(8, P1DID);
		ps.setString(9, P1Bench.toString());
		ps.setInt(10, P1EPS);
		
		ps.setLong(11, P2ID);
		ps.setString(12, P2Status);
		ps.setString(13, P2Hand.toString());
		ps.setInt(14, P2Decksize);
		ps.setString(15, P2Discard.toString());
		ps.setLong(16, P2DID);
		ps.setString(17, P2Bench.toString());
		ps.setInt(18, P2EPS);
		
		ps.setLong(19, id);
		ps.setLong(20, version);
		
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
