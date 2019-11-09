package com.hanyang.rest;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient extends RestTemplate{

	private Logger logger=LoggerFactory.getLogger(RestClient.class);


	public HttpEntity<?> getHttpEntityDefaultHeader(HttpEntity<?> requestEntity){
		if(requestEntity!=null){
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.putAll(requestEntity.getHeaders());

			return new HttpEntity<>(requestEntity.getBody(), headers);
		}

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return httpEntity;
	}
	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType,
			Object... uriVariables) throws RestClientException {
		requestEntity = getHttpEntityDefaultHeader(requestEntity);
		return super.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		requestEntity = getHttpEntityDefaultHeader(requestEntity);
		return super.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(URI url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType)
			throws RestClientException {
		requestEntity = getHttpEntityDefaultHeader(requestEntity);
		return super.exchange(url, method, requestEntity, responseType);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> responseType, Object... uriVariables)
			throws RestClientException {
		requestEntity = getHttpEntityDefaultHeader(requestEntity);
		return super.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		requestEntity = getHttpEntityDefaultHeader(requestEntity);
		return super.exchange(url, method, requestEntity, responseType, uriVariables);
	}


	public RestClient(Boolean enableSSL , ClientHttpRequestFactory requestFactory , ObjectMapper objectMapper){

		super(requestFactory);

		if(enableSSL){
			enableSSL();
		}

		boolean ischange = false;

		for(HttpMessageConverter<?> converter :  this.getMessageConverters()){
			if(converter instanceof MappingJackson2HttpMessageConverter){
				((MappingJackson2HttpMessageConverter)converter).setObjectMapper(objectMapper);
				ischange = true;
				break;
			}
		}

		if(!ischange){
			MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
			mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
			this.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		}
	}

	private void enableSSL() {

		if(logger.isInfoEnabled()){
			logger.info("trustAllCerts enable");
		}

		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, null);
			SSLContext.setDefault(sc);

			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

		} catch (Exception e) {
		}
	}
}
