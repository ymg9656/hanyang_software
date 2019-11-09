package com.hanyang.rest.domain;

import java.util.Date;

public class RestVclip {
	private String title;
	private Integer playTime;
	private String thumbnail;
	private String link;
	private Date date;
	private String type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
