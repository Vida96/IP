package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.dto.User;
 
public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username = ?";
	private static final String SQL_IS_MAIL_USED = "SELECT * FROM user WHERE mail = ?";
	private static final String SQL_INSERT = "INSERT INTO user (username, password, firstname, lastname, mail) VALUES (?,?,?,?,?)";
	
	public static User selectByUsernameAndPassword(String username, String password){
		User user = null;
	/*	Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
	*/
				/* TO DO */
			//	user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("last_name"), rs.getString("first_name"));
		user = new User(username, password);
	/*		}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}*/
		return user;
	}
	
	public static boolean isUsernameAllowed(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_USERNAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean isMailAllowed(String mail) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {mail};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_MAIL_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean insert(User user) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getMail()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				user.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
