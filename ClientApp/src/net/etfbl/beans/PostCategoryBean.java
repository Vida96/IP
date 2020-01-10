package net.etfbl.beans;

import java.util.List;

import net.etfbl.dao.ConnectionPool;
import net.etfbl.dao.PostCategoryDAO;
import net.etfbl.dao.PostDAO;
import net.etfbl.dto.Post;
import net.etfbl.dto.PostCategory;

public class PostCategoryBean {


private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	public List<PostCategory> getAllActivePostCategories(){
		return PostCategoryDAO.getAllActiveCategories();
	}

}
