package net.etfbl.dto;

public class Comment {

	private String text;
	private String image;

	public Comment(String text, String image) {
		super();
		this.text = text;
		this.image = image;
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
}
