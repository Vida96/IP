package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

public class PostDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ACTIVE_POSTS = "SELECT * FROM post WHERE active=1";
	
	public static List<Post> getAllActivePosts(){
		List<Post> activePosts = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		User creator = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACTIVE_POSTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Post post = new Post();
				int postId = resultSet.getInt(1);
				post.setId(postId);
				post.setText(resultSet.getString(2));
				creator = UserDAO.getById(postId);
				post.setCreator(creator);
				activePosts.add(post);
			/*	event.setCreationDate(resultSet.getTimestamp(3));
				event.setIdEvent(resultSet.getInt(4));
				event.setName(resultSet.getString(5));
				event.setTimeOfEvent(resultSet.getTimestamp(6));
				event.setDescription(resultSet.getString(7));
				event.setPictureUrl(resultSet.getString(8));
				event.setCategory(resultSet.getString(9));
				event.setId(resultSet.getInt(10));
				event.setLikes(LikeDAO.getAllByPostId(postId));
				event.setDislikes(DislikeDAO.getAllByPostId(postId));*/
//				result.add(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return activePosts;
	}
}
