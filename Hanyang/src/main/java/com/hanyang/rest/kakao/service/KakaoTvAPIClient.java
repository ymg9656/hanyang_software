package com.hanyang.rest.kakao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanyang.rest.APIClientSupport;
import com.hanyang.rest.RestClient;
import com.hanyang.rest.kakao.domain.KakaoTv;
import com.hanyang.rest.kakao.domain.KakaoVclipDocument;

public class KakaoTvAPIClient extends APIClientSupport {
	private static final Logger logger = LoggerFactory.getLogger(KakaoTvAPIClient.class);


	public KakaoTvAPIClient(ObjectMapper objectmapper, RestClient restClient, String host) {
		super(objectmapper, restClient, host);
	}

	
	public KakaoTvAPIResult<KakaoTv> getCrawlerVclipSearch(String search, int page) {
		
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/api/v1/ft/search/cliplinks");
		uriBuilder.queryParam("sort", "Score");
		uriBuilder.queryParam("q", search);
		uriBuilder.queryParam("fulllevels", "list");
		uriBuilder.queryParam("fields", "-user,-clipChapterThumbnailList,-tagList");
		uriBuilder.queryParam("size", 20);
		uriBuilder.queryParam("page", page);
		uriBuilder.queryParam("_", System.currentTimeMillis());
		try {
			String uri = uriBuilder.build().toUriString();
			logger.debug("uri:{}", uri);
			ResponseEntity<KakaoTvAPIResult<KakaoTv>> response = restClient.exchange(uri, HttpMethod.GET,
					null, new ParameterizedTypeReference<KakaoTvAPIResult<KakaoTv>>() {
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
