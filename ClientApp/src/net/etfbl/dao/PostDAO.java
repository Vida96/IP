package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.etfbl.dto.Comment;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

public class PostDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	  
	private static final String SQL_INSERT = "INSERT INTO post (text, user_id, time, location, video, link, isEmergency) VALUES (?,?, ?,?,?,?,?)";
	
	private static final String SQL_SELECT_ACTIVE_UNEMERGENCY_POSTS = "SELECT * FROM post WHERE active=1 AND isEmergency=0";
	
	public static boolean insert(Post post) {
		boolean result = false;
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		ResultSet generatedKeys = null;
		Integer postId;
		Object values[] = { post.getText(), post.getUserId(), post.getCreationTime(), post.getLocation(), post.getVideo(), post.getLink(), post.getIsEmergency()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next())
				postId = generatedKeys.getInt(1);

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
	
	public static List<Post> getAllActivePosts(){
		List<Post> activePosts = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		User postCreator = null;
		List<String> images;
		List<Comment> comments;
		Integer postId;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACTIVE_UNEMERGENCY_POSTS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer userId = rs.getInt("user_Id");
				postCreator = UserDAO.getById(userId);
				postId = rs.getInt("id");
				Post post = new Post(postId, rs.getString("text"), postCreator, rs.getString("time"), rs.getString("location"), rs.getString("video"), rs.getString("link"));
				images  = ImageDAO.getById(postId);
				post.setImages(images);
				comments = CommentDAO.getAllpostComments(postId);
				post.setCommments(comments);
				activePosts.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return activePosts;
	}
}