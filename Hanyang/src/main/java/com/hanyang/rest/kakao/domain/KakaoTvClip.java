package com.hanyang.rest.kakao.domain;

import java.math.BigInteger;

import com.hanyang.util.DateUtil;

public class KakaoTvClip {
	  
	private BigInteger id;
	private String vid;
	private String title;
	private String description;
	
	private Integer userId;
	private Integer channelId;
	private Integer categoryId;

	private String status;
	private Integer duration;
	private String durationStr;
	private boolean isVertical;
	private String thumbnailUrl;
	private String sourceUrl;
	private boolean isOpen;
	private boolean canComment;
	private boolean canScrap;
	private boolean canLink;
	private boolean shouldFeedSearcher;
	private boolean shouldGeoBlock;
	private String ageLimit;
	private Integer playCount;
	private Integer likeCount;
	private Integer commentCount;
	private String createTime;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDuration() {
		return duration;
	}
	public String getDurationStr() {
		if(duration!=null) {
			Integer duration=206;
			String h=duration/3600 == 0 ? null : ""+(duration/3600);
			String m=(duration % 3600 / 60) == 0 ? "00" : ""+(duration % 3600 / 60);
			m=m.length() == 1 ? "0"+m : m;
			String s=(duration % 3600 % 60) == 0 ? "00" : ""+(duration % 3600 % 60);
			s=s.length() == 1 ? "0"+s : s;
			String r= h == null ? m+":"+s : h+":"+m+":"+s; 
			return r;
		}
		return null;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public boolean isVertical() {
		return isVertical;
	}
	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean isCanComment() {
		return canComment;
	}
	public void setCanComment(boolean canComment) {
		this.canComment = canComment;
	}
	public boolean isCanScrap() {
		return canScrap;
	}
	public void setCanScrap(boolean canScrap) {
		this.canScrap = canScrap;
	}
	public boolean isCanLink() {
		return canLink;
	}
	public void setCanLink(boolean canLink) {
		this.canLink = canLink;
	}
	public boolean isShouldFeedSearcher() {
		return shouldFeedSearcher;
	}
	public void setShouldFeedSearcher(boolean shouldFeedSearcher) {
		this.shouldFeedSearcher = shouldFeedSearcher;
	}
	public boolean isShouldGeoBlock() {
		return shouldGeoBlock;
	}
	public void setShouldGeoBlock(boolean shouldGeoBlock) {
		this.shouldGeoBlock = shouldGeoBlock;
	}
	public String getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}
	public Integer getPlayCount() {
		return playCount;
	}
	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
