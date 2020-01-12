package net.etfbl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImagesDAO {

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM photo WHERE postId=?";
	
	public static List<String> getById(int postId) {
		List<String> photos = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {postId};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				String photoName = rs.getString("name");
				photos.add(photoName);
	  		}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		return photos;
	}
}
