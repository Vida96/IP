package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostHasPostCategory {

	private static final String SQL_SELECT_POST_POST_CATEGORIES = "SELECT name FROM post_has_posttype inner join postType on post_has_posttype.postType_Id = postType.id WHERE post_Id=?";

	public static List<String> getById(int postId) {
		List<String> categories = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { postId };
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_POST_POST_CATEGORIES, false,
					values);
			rs = pstmt.executeQuery();
			categories = new ArrayList<>();

			while (rs.next()) {
				String categoryName = rs.getString("name");
				categories.add(categoryName);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return categories;
	}
}
