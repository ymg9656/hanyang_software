package com.hanyang.crawler;

import java.io.IOException;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanyang.json.JacksonObjectMapper;
import com.hanyang.rest.APIClientSupport;
import com.hanyang.rest.RestClient;
import com.hanyang.rest.naver.service.NaverAPIClient;
import com.hanyang.rest.naver.service.NaverAPIResult;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InstagramServiceTest {

	@Autowired
	private JacksonObjectMapper objectMapper;
	
	@Autowired
	private RestClient restClient;
	
	
	
	class InstagramAPIClient extends APIClientSupport{

		public InstagramAPIClient(ObjectMapper objectmapper, RestClient restClient, String host) {
			super(objectmapper, restClient, host);
		}
		public void getImageSearch(String search, int page) {
			UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/explore/tags/맛집/?__a=1");
			uriBuilder.queryParam("__a", 1);
			HttpHeaders headers = new HttpHeaders();
			headers.set("referer", "https://www.instagram.com/explore/tags/맛집/?hl=ko");
			headers.set("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
			/*:authority: www.instagram.com
			:method: GET
			:path: /explore/tags/%EB%A7%9B%EC%A7%91/?__a=1
			:scheme: https
			accept: *
			accept-encoding: gzip, deflate, br
			accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
			cache-control: no-cache
			cookie: mid=W8Lz9gAEAAHOzUtKGBn9JehHtisA; mcd=3; csrftoken=dtrusI9jL0DVWOhOb74zC1DC6RakBMii; rur=PRN; urlgen="{\"112.222.251.244\": 3786}:1iJoFp:CaLR398vIQrxWJteXVk4dbbm9_w"
			pragma: no-cache
			referer: https://www.instagram.com/explore/tags/%EB%A7%9B%EC%A7%91/?hl=ko
			sec-fetch-mode: cors
			sec-fetch-site: same-origin
			user-agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36
			x-ig-app-id: 936619743392459
			x-ig-www-claim: 0
			x-requested-with: XMLHttpRequest
			__a: 1
			*/
			
			
			HttpEntity<?> httpEntity = new HttpEntity<>(headers);
			try {
				String uri = uriBuilder.build().toUriString();
				ResponseEntity response = restClient.exchange(uri, HttpMethod.GET,
						httpEntity, new ParameterizedTypeReference<Object>() {
						});
				System.out.println(response.getBody());
			} catch (RestClientException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void imageSearchTest() throws IOException, JSONException {
		String host="https://www.instagram.com";
		InstagramAPIClient instagram = new InstagramAPIClient(objectMapper,restClient,host);
		instagram.getImageSearch("맛집", 1);
		
		//ToDo
		//String url="https://www.instagram.com/explore/tags/맛집/?__a=1";
		//Doing 
		String userAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36";
		//Document document = Jsoup.connect(url).userAgent(userAgent).get();
		//System.out.println(document.html());
		//System.out.println(document.html());
		//Done
	}
}
