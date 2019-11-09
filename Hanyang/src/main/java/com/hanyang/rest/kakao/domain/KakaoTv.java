package com.hanyang.rest.kakao.domain;

import java.math.BigInteger;
import java.text.ParseException;

import com.hanyang.util.DateUtil;

public class KakaoTv {
	private BigInteger id;
	private BigInteger channelId;
	private BigInteger clipId;
	private String displayTitle;
	private String createTime;
	private KakaoTvChannel channel;
	private KakaoTvClip clip;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getChannelId() {
		return channelId;
	}
	public void setChannelId(BigInteger channelId) {
		this.channelId = channelId;
	}
	public BigInteger getClipId() {
		return clipId;
	}
	public void setClipId(BigInteger clipId) {
		this.clipId = clipId;
	}
	public String getDisplayTitle() {
		return displayTitle;
	}
	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}
	public String getCreateTime() {
		if(createTime==null) {
			return null;
		}
		try {
			String dt=DateUtil.getFewDaysAgo(createTime);
			return dt;
		} catch (ParseException e) {
			return "-";
		}
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public KakaoTvChannel getChannel() {
		return channel;
	}
	public void setChannel(KakaoTvChannel channel) {
		this.channel = channel;
	}
	public KakaoTvClip getClip() {
		return clip;
	}
	public void setClip(KakaoTvClip clip) {
		this.clip = clip;
	}
}
