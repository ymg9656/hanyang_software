package com.hanyang.rest.naver.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hanyang.rest.naver.domain.NaverImageItem;

public class NaverAPIResult{
	private Long count;
	private String code;
	private String codeMessage;
	private String status;
	private String statusMessage;
	
	/*lastBuildDate	datetime	검색 결과를 생성한 시간이다.
	total	integer	검색 결과 문서의 총 개수를 의미한다.
	start	integer	검색 결과 문서 중, 문서의 시작점을 의미한다.
	display	integer	검색된 검색 결과의 개수이다.*/
	private Date lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<NaverImageItem> items;
	
	public NaverAPIResult()
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

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public List<NaverImageItem> getItems() {
		return items;
	}

	public void setItems(List<NaverImageItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
