package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

public class PostDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ACTIVE_UNEMERGENCY_POSTS = "SELECT * FROM post WHERE active=1 AND isEmergency=0";
	
	public static List<Post> getAllActivePosts(){
		List<Post> activePosts = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		User postCreator = null;
		List<String> images;
		Integer postId;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACTIVE_UNEMERGENCY_POSTS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer userId = rs.getInt("userId");
				postCreator = UserDAO.getById(userId);
				postId = rs.getInt("id");
				Post post = new Post(postId, rs.getString("text"), postCreator, rs.getTimestamp("creationTime"), rs.getString("location"), rs.getString(" video"));
				images  = ImagesDAO.getById(postId);
				post.setImages(images);
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