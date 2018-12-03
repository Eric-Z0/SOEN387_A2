package org.dom.model.bench.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class BenchFinder {
	
	public static String SELECT_BY_ID_SQL = "SELECT bench.id, bench.version, bench.turn, bench.BPID, bench.S1PID, bench.Energy FROM " + BenchTDG.TABLE + " AS bench " + "WHERE bench.id=?;";
	
	public static String SELECT_ALL_SQL = "SELECT bench.id FROM " + BenchTDG.TABLE + " AS bench;";
	
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
