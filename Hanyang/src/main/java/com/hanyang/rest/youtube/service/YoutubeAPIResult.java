package com.hanyang.rest.youtube.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hanyang.rest.youtube.domain.YoutubeVclip;

public class YoutubeAPIResult<T>{
	private Long count;
	private String code;
	private String codeMessage;
	private String status;
	private String statusMessage;
	private String nextPageToken;
	private List<T> items;
	
	public YoutubeAPIResult()
	{
		code = "OK";
		codeMessage = "success";

		status = "200";
		statusMessage = "OK";
	}

	public Long getCount() {
		return count;
	}



	public void setCount(Long count) {
		this.count = count;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getCodeMessage() {
		return codeMessage;
	}



	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getStatusMessage() {
		return statusMessage;
	}



	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	
	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
