package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.etfbl.dto.Comment;
import net.etfbl.dto.Comment;
import net.etfbl.dto.User;

public class CommentDAO {

	private static final String SQL_INSERT = "INSERT INTO comment (text, image, postId) VALUES (?,?,?)";
	
	private static final String SQL_SELECT_POST_COMMENTS = "SELECT * FROM comment WHERE postid=?";
	
	public static boolean insert(Comment postComment) {
		boolean result = false;
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		ResultSet generatedKeys = null;
		Object values[] = { postComment.getText(), postComment.getText(), postComment.getPostId()};
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
	
	public static List<Comment> getAllpostComments(Integer postId){
		List<Comment> postComments = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {postId};
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_POST_COMMENTS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment(rs.getString(1), rs.getString(2), rs.getInt(3));
				postComments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return postComments;
	}
	
}
