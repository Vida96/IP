package net.etfbl.dto;

public class Post {
	private Integer id;
	private String text;
	private User creator;

	public Post(Integer id, String text, User creator) {
		super();
		this.id = id;
		this.text = text;
		this.creator = creator;
	}

	public Post() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}

}
