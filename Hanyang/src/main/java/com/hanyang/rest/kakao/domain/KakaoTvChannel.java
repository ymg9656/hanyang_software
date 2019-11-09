package com.hanyang.rest.kakao.domain;

import java.math.BigInteger;

public class KakaoTvChannel {
	 private BigInteger id;
	 private BigInteger userId;
	 private Integer categoryId;
	 private String name;
	 private String description;
	 private Integer headPlaylistId;
	 private Integer subscriberCount;
	 private Integer visitCount;
	 private boolean isOpen;
	 private boolean shouldFeedSearcher;
	 private boolean isLegacy;
	 private String createTime;
	 private boolean canAutoUploadLiveClip;
	 private boolean canLive;
	 private boolean canUploadClip;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getHeadPlaylistId() {
		return headPlaylistId;
	}
	public void setHeadPlaylistId(Integer headPlaylistId) {
		this.headPlaylistId = headPlaylistId;
	}
	public Integer getSubscriberCount() {
		return subscriberCount;
	}
	public void setSubscriberCount(Integer subscriberCount) {
		this.subscriberCount = subscriberCount;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean isShouldFeedSearcher() {
		return shouldFeedSearcher;
	}
	public void setShouldFeedSearcher(boolean shouldFeedSearcher) {
		this.shouldFeedSearcher = shouldFeedSearcher;
	}
	public boolean isLegacy() {
		return isLegacy;
	}
	public void setLegacy(boolean isLegacy) {
		this.isLegacy = isLegacy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public boolean isCanAutoUploadLiveClip() {
		return canAutoUploadLiveClip;
	}
	public void setCanAutoUploadLiveClip(boolean canAutoUploadLiveClip) {
		this.canAutoUploadLiveClip = canAutoUploadLiveClip;
	}
	public boolean isCanLive() {
		return canLive;
	}
	public void setCanLive(boolean canLive) {
		this.canLive = canLive;
	}
	public boolean isCanUploadClip() {
		return canUploadClip;
	}
	public void setCanUploadClip(boolean canUploadClip) {
		this.canUploadClip = canUploadClip;
	}
}
