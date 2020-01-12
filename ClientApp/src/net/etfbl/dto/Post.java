package net.etfbl.dto;

import java.util.Date;
import java.util.List;

public class Post {
	private Integer id;
	private String text;
	private User creator;
	private Date creationTime; 
	private String location;
	private String video;
    private List images;
	
    public Post(Integer id, String text, User creator, Date creationTime, String location, String video) {
		super();
		this.id = id;
		this.text = text;
		this.creator = creator;
		this.creationTime = creationTime;
		this.location = location;
		this.video = video;
	}

    public Post(String text, User creator, Date creationTime, String location, String video) {
		super();
		this.text = text;
		this.creator = creator;
		this.creationTime = creationTime;
		this.location = location;
		this.video = video;
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

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List getImages() {
		return images;
	}

	public void setImages(List images) {
		this.images = images;
	}
}

