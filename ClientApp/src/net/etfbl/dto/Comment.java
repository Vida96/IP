package net.etfbl.dto;

public class Comment {

	private String text;
	private String image;
	private String time;
	private Integer postId;

    public Comment(String text, String image, Integer postId, String time) {
		super();
		this.text = text;
		this.image = image;
		this.postId = postId;
		this.setTime(time);
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
