package com.hanyang.rest.kakao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanyang.rest.APIClientSupport;
import com.hanyang.rest.RestClient;
import com.hanyang.rest.kakao.domain.KakaoImageDocument;
import com.hanyang.rest.kakao.domain.KakaoVclipDocument;

public class KakaoAPIClient extends APIClientSupport {
	private static final Logger logger = LoggerFactory.getLogger(KakaoAPIClient.class);

	private String app_key = "ed7d83d2b7307d7452578cc6873083a4";

	public KakaoAPIClient(ObjectMapper objectmapper, RestClient restClient, String host) {
		super(objectmapper, restClient, host);
	}

	
	public KakaoAPIResult<KakaoImageDocument> getImageSearch(String search, int page) {
		/*
		 * query 검색을 원하는 질의어 O String sort 결과 문서 정렬 방식 X (accuracy) accuracy (정확도순) or
		 * recency (최신순) page 결과 페이지 번호 X(기본 1) 1-50 사이 Integer size 한 페이지에 보여질 문서의 개수
		 * X(기본 10) 1-50 사이 Integer
		 */
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/v2/search/image");
		uriBuilder.queryParam("query", search);
		uriBuilder.queryParam("page", String.valueOf(page));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + app_key);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		try {
			String uri = uriBuilder.build().toUriString();
			logger.debug("uri:{}", uri);
			ResponseEntity<KakaoAPIResult<KakaoImageDocument>> response = restClient.exchange(uri, HttpMethod.GET,
					httpEntity, new ParameterizedTypeReference<KakaoAPIResult<KakaoImageDocument>>() {
					});

			if (logger.isDebugEnabled()) {
				logger.debug("response:{}", response);
			}
			return response.getBody();
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public KakaoAPIResult<KakaoVclipDocument> getVclipSearch(String search, int page) {
		/*
		 * query 검색을 원하는 질의어 O String sort 결과 문서 정렬 방식 X (accuracy) accuracy (정확도순) or
		 * recency (최신순) page 결과 페이지 번호 X(기본 1) 1-50 사이 Integer size 한 페이지에 보여질 문서의 개수
		 * X(기본 10) 1-50 사이 Integer
		 */
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/v2/search/vclip");
		uriBuilder.queryParam("query", search);
		uriBuilder.queryParam("page", String.valueOf(page));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + app_key);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		try {
			String uri = uriBuilder.build().toUriString();
			logger.debug("uri:{}", uri);
			ResponseEntity<KakaoAPIResult<KakaoVclipDocument>> response = restClient.exchange(uri, HttpMethod.GET,
					httpEntity, new ParameterizedTypeReference<KakaoAPIResult<KakaoVclipDocument>>() {
					});

			if (logger.isDebugEnabled()) {
				logger.debug("response:{}", response);
			}
			return response.getBody();
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public KakaoAPIResult<KakaoVclipDocument> getCrawlerVclipSearch(String search, int page) {
		
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/v2/search/vclip");
		uriBuilder.queryParam("query", search);
		uriBuilder.queryParam("page", String.valueOf(page));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + app_key);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		try {
			String uri = uriBuilder.build().toUriString();
			logger.debug("uri:{}", uri);
			ResponseEntity<KakaoAPIResult<KakaoVclipDocument>> response = restClient.exchange(uri, HttpMethod.GET,
					httpEntity, new ParameterizedTypeReference<KakaoAPIResult<KakaoVclipDocument>>() {
					});

			if (logger.isDebugEnabled()) {
				logger.debug("response:{}", response);
			}
			return response.getBody();
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
