package com.hanyang.crawler.naver.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hanyang.crawler.naver.domain.NaverVclip;
import com.hanyang.rest.naver.domain.NaverImageItem;

public class NaverResult{
	private Long count;
	private String code;
	private String codeMessage;
	private String status;
	private String statusMessage;
	
	private List<NaverVclip> items;
	
	public NaverResult()
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

	public List<NaverVclip> getItems() {
		return items;
	}

	public void setItems(List<NaverVclip> items) {
		this.items = items;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
