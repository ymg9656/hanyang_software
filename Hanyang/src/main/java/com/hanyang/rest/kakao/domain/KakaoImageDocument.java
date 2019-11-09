package com.hanyang.rest.kakao.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanyang.crawler.ws.Image;

public class KakaoImageDocument extends Image{
	public String collection;
    public String thumbnailUrl;
    public String imageUrl;
    public int width;
    public int height;
    public String displaySitename;
    public String docUrl;
    public Date datetime;
    public KakaoImageDocument() {
    	super.setType("kakao");
    }
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	} 
	@JsonProperty(value="thumbnail_url")
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
		super.setThumbnail(thumbnailUrl);
	}
	public String getImageUrl() {
		return imageUrl;
	}
	@JsonProperty(value="image_url")
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDisplaySitename() {
		return displaySitename;
	}
	@JsonProperty(value="display_sitename")
	public void setDisplaySitename(String displaySitename) {
		this.displaySitename = displaySitename;
	}
	public String getDocUrl() {
		return docUrl;
	}
	@JsonProperty(value="doc_url")
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
		super.setLink(docUrl);
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
