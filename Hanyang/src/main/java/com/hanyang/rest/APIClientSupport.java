package com.hanyang.rest;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIClientSupport {
	private ObjectMapper objectmapper;
	protected RestClient restClient;
	private String host;

	public APIClientSupport(ObjectMapper objectmapper, RestClient restClient, String host) {
		this.restClient = restClient;
		this.host = host;
		this.objectmapper = objectmapper;
	}
	
	protected ObjectMapper getObjectMapper(){
		return this.objectmapper;
	}

	protected UriComponentsBuilder getUriComponentsBuilder() {
		return UriComponentsBuilder.fromUriString(host);
	}

	protected UriComponentsBuilder getUriComponentsBuilder(String path) {
		return getUriComponentsBuilder().path(path);
	}

	protected Map<String, String> toMap(Object entity) {
		return objectmapper.convertValue(entity, new TypeReference<Map<String, String>>() {});
	}

	protected MultiValueMap<String, String> toMultiValueMap(Object entity) {

		Map<String, String> map = toMap(entity);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String> entry : map.entrySet()) {
			params.add(entry.getKey(), entry.getValue());
		}

		return params;
	}
	
	protected HttpHeaders createHeaderWithFormUrlEncoded(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		return headers;
	}
}
