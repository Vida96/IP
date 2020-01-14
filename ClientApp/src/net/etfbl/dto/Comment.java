package net.etfbl.dto;

public class Comment {

	private String text;
	private String image;
    private Integer postId;

    public Comment(String text, String image, Integer postId) {
		super();
		this.text = text;
		this.image = image;
		this.postId = postId;
	}
	
    public String getText() {
		return text;
	}
	
    public void setText(String text) {
		this.text = text;
	}
	
    public String getImage() {
		return image;
	}
	
    public void setImage(String image) {
		this.image = image;
	}
	
    public Integer getPostId() {
		return postId;
	}
	
    public void setPostId(Integer postId) {
		this.postId = postId;
	}

}
