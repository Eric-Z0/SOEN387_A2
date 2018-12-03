package org.dom.model.user.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserFinder {
	
	public static String SELECT_BY_ID_SQL = "SELECT user.id, user.version, user.name, user.password, user.decks FROM " + UserTDG.TABLE + " AS user " + "WHERE user.id=?;";
	
	public static String SELECT_ALL_SQL = "SELECT user.id FROM " + UserTDG.TABLE + " AS user;";
	
	public static String SELECT_BY_NAME_SQL = "SELECT user.id, user.version, user.name, user.password, user.decks FROM " + UserTDG.TABLE + " AS user " + "WHERE user.name=?;";
	
	public static String SELECT_BY_NAME_PASSWORD_SQL = "SELECT user.id, user.version, user.name, user.password, user.decks FROM " + UserTDG.TABLE + " AS user " + "WHERE user.name=? AND user.password=?;";
	
	public static ResultSet find(long id) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet find(String name, String password) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME_PASSWORD_SQL);
		ps.setString(1, name);
		ps.setString(2, password);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByName(String name) throws SQLException 
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME_SQL);
		ps.setString(1, name);
		return SQLLogger.processQuery(ps);
	}
	
}
