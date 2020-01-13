package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.dto.Comment;
import net.etfbl.dto.User;

public class CommentDAO {

	private static final String SQL_INSERT = "INSERT INTO user (username, password, firstname, lastname, mail) VALUES (?,?,?,?,?)";
	
	public static boolean insert(Comment postComment) {
		boolean result = false;
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		ResultSet generatedKeys = null;
		Object values[] = { postComment.getText(), postComment.getText(), 1}; //dodati ID objave
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
		 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
}
