package com.hanyang.rest.naver.domain;

import com.hanyang.crawler.ws.Image;

public class NaverImageItem extends Image{
	/*title	string	검색 결과 이미지의 제목을 나타낸다.
	link	string	검색 결과 이미지의 하이퍼텍스트 link를 나타낸다.
	thumbnail	string	검색 결과 이미지의 썸네일 link를 나타낸다.
	sizeheight	string	검색 결과 이미지의 썸네일 높이를 나타낸다.
	sizewidth	string	검색 결과 이미지의 너비를 나타낸다. 단위는 pixel이다.
	*/
	private String title;
	private String link;
	private String thumbnail;
	private String sizeheight;
	private String sizewidth;
	public NaverImageItem() {
		super.setType("naver");
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
		super.setLink(link);
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		super.setThumbnail(thumbnail);
	}
	public String getSizeheight() {
		return sizeheight;
	}
	public void setSizeheight(String sizeheight) {
		this.sizeheight = sizeheight;
	}
	public String getSizewidth() {
		return sizewidth;
	}
	public void setSizewidth(String sizewidth) {
		this.sizewidth = sizewidth;
	}
}
