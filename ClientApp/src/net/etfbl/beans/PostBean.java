package net.etfbl.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import net.etfbl.dao.ConnectionPool;
import net.etfbl.dao.PostDAO;
import net.etfbl.dao.UserDAO;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

public class PostBean {

private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	public List<Post> getAllActiveUnemergencyPosts(){
		return PostDAO.getAllUnemergencyPosts();
	}
	
	public List<Post> getAllActiveEmergencyPosts(){
		return PostDAO.getAllEmergencyPosts();
	}
}
