package com.hanyang.rest;

import java.util.List;

public class APIResult<T> {
	private String status;
	private String statusMessage;
	private String code;
	private String codeMessage;
	private T entity;
	private List<T> entitys;
	
	public APIResult() {
		this.status="200";
		this.statusMessage="success";
		this.code="1";
		this.codeMessage="ok";
	}
	
	public String getStatus() {
		return status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public String getCode() {
		return code;
	}
	public String getCodeMessage() {
		return codeMessage;
	}
	public T getEntity() {
		return entity;
	}
	public List<T> getEntitys() {
		return entitys;
	}
	
	
	
}
