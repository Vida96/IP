package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.etfbl.dto.PostCategory;
import net.etfbl.dto.User; 

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD_AND_ACTIVE = "SELECT * FROM user WHERE username=? AND password=? AND active=?";
	
	private static final String SQL_SELECT_BY_USERNAME_AND_ACTIVE = "SELECT * FROM user WHERE username=? AND active=?";
	
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id=?";
	
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username = ? AND active = 1";

	private static final String SQL_IS_USERNAME_ON_HOLD = "SELECT * FROM user WHERE username = ? AND active = 0 AND loginTime IS NULL";
	
	private static final String SQL_IS_MAIL_USED = "SELECT * FROM user WHERE mail = ? AND active = 1";

	private static final String SQL_IS_MAIL_ON_HOLD = "SELECT * FROM user WHERE mail = ? AND active = 0 AND loginTime IS NULL";
	
	private static final String SQL_INSERT = "INSERT INTO user (username, password, firstname, lastname, mail) VALUES (?,?,?,?,?)";
	
	private static final String SQL_UPDATE = "UPDATE user set firstname=?, lastname=?, username=?, password=?, photo=?, country=?, region=?, city=?, numberoflogins=?, notificationOnMail=?, notificationInApp=? WHERE id=?";
	
	private static final String SQL_SELECT_USERS_FOR_EMERGENCY_MAIL = "SELECT mail FROM user WHERE active=1 AND notificationOnMail=1 AND mail <>?";
	
	public static User getUserByUsernameAndPasswordAndActive(String username, String password, Integer active){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password, active};
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD_AND_ACTIVE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"), rs.getString("password"),rs.getString("mail"), rs.getString("photo"), rs.getString("country"), rs.getString("region"), rs.getString("city"), rs.getInt("notificationOnMail") , rs.getInt("notificationInApp"),rs.getInt("numberoflogins"));
			}
																														
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		return user;
	}
	
	public static User getUserByUsernameAndActive(String username, Integer active){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, active};
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_ACTIVE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"), rs.getString("password"),rs.getString("mail"), rs.getString("photo"), rs.getString("country"), rs.getString("region"), rs.getString("city"), rs.getInt("notificationOnMail") , rs.getInt("notificationInApp"),rs.getInt("numberoflogins"));
			}
																														
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
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
	
	public static boolean isUsernameOnHold(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_USERNAME_ON_HOLD, false, values);
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

	public static boolean isMailOnHold(String mail) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {mail};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_MAIL_ON_HOLD, false, values);
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
	
	public static boolean update(User user) {
		boolean result = false;
		Connection connection = null;
	
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPhoto());
			pstmt.setString(6, user.getCountry());
			pstmt.setString(7, user.getRegion());
			pstmt.setString(8, user.getCity());
			pstmt.setInt(9, user.getNumberOfLogins());
			pstmt.setInt(10, user.getNotificationOnMail());
			pstmt.setInt(11, user.getNotificationInApp());
			pstmt.setInt(12, user.getId());
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static User getById(int postId) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {postId};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setphoto(rs.getString("photo"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
	  		}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		return user;
	}

	public static List<String> getUsersMailsForEmergencyMail(String senderMail) {
		List<String> usersMails = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		Object values[] = {senderMail};
	
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SQL_SELECT_USERS_FOR_EMERGENCY_MAIL, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String mail = resultSet.getString(1);
				usersMails.add(mail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return usersMails;
	}
}
