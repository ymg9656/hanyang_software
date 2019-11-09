package com.hanyang.rest.kakao.domain;

import java.math.BigInteger;

public class KakaoMeta {
	private BigInteger totalCount;
	private Integer pageableCount;
	private boolean isEnd;
	public BigInteger getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(BigInteger totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageableCount() {
		return pageableCount;
	}
	public void setPageableCount(Integer pageableCount) {
		this.pageableCount = pageableCount;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}
