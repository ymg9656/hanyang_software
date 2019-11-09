package com.hanyang.rest.kakao.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanyang.rest.domain.RestVclip;

public class KakaoVclipDocument extends RestVclip{
	private String title;
    private Integer playTime;
    private String thumbnail;
    private String url;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date datetime;
    private String author;
    public KakaoVclipDocument() {
    	super.setType("kakao");
    }
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		super.setTitle(title);
	}
	public Integer getPlayTime() {
		return playTime;
	}
	@JsonProperty(value="play_time")
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
		super.setPlayTime(playTime);
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		super.setThumbnail(thumbnail);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
		super.setLink(url);
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
		super.setDate(datetime);
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
