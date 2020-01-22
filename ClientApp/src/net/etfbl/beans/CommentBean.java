package net.etfbl.beans;

import java.util.List;

import net.etfbl.dao.CommentDAO;
import net.etfbl.dao.PostDAO;
import net.etfbl.dto.Comment;
import net.etfbl.dto.Post;

public class CommentBean {
	
	public void insertComment(String description, String image, int postId){
		 CommentDAO.insert(new Comment(description, image, postId));
	}


}
