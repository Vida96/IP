package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.etfbl.dto.PostCategory;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

public class PostCategoryDAO {

	private static final String SQL_SELECT_ACTIVE_CATEGORIES = "SELECT * FROM postType WHERE active=1";
	
	private static final String SQL_INSERT = "INSERT INTO post_has_postType (post_id, postType_id) VALUES (?,?)";
	
	public static List<PostCategory> getAllActiveCategories(){
		List<PostCategory> activeCategories = new java.util.ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACTIVE_CATEGORIES);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PostCategory category = new PostCategory();
				category.setId(resultSet.getInt(1));
				category.setName(resultSet.getString(2));
				activeCategories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return activeCategories;
	}
	
	public static Boolean insert(List<Integer> categoriesId, Integer postId) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
	    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		
		for(Integer categoryId : categoriesId) {
		Object values[] = { categoryId, postId}; 
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
		}
		return result;		
	}
}
