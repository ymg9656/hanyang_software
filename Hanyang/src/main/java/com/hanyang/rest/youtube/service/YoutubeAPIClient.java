package com.hanyang.rest.youtube.service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.hanyang.rest.youtube.domain.YoutubeVclip;
import com.hanyang.util.DateUtil;

public class YoutubeAPIClient {
	private static final Logger logger = LoggerFactory.getLogger(YoutubeAPIClient.class);

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/**
	 * Global instance of the max number of videos we want returned (50 = upper
	 * limit per page).
	 */
	private static final long NUMBER_OF_VIDEOS_RETURNED = 10;

	/** Global instance of Youtube object to make all API requests. */
	private YouTube youtube;
	private YouTube.Search.List youtubeSearch;
	private String app_key = "AIzaSyDthlYJHRxVlp0iTltsgcTOnnG6YAoRNNo";

	public YoutubeAPIClient() {
		/*
		 * The YouTube object is used to make all API requests. The last argument is
		 * required, but because we don't need anything initialized when the HttpRequest
		 * is initialized, we override the interface and provide a no-op function.
		 */
		youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
			}
		}).setApplicationName("My Project 19386").build();
		try {
			youtubeSearch = youtube.search().list("id,snippet");
			youtubeSearch.setKey(app_key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public YoutubeAPIResult<YoutubeVclip> getVclipSearch(String search, String nextPageToken) throws IOException {
		// Get query term from user.
		youtubeSearch.setQ(search);
		youtubeSearch.setType("video");
		if(!StringUtils.hasText(nextPageToken)) {
			return null;
		} 
		if(!"1".equals(nextPageToken)) {
			youtubeSearch.setPageToken(nextPageToken);	
		}
		
		/*
		 * This method reduces the info returned to only the fields we need and makes
		 * calls more efficient.
		 */
		youtubeSearch.setFields("nextPageToken,items(id/kind,id/videoId,snippet/title,snippet/thumbnails/medium/url)");
		
		youtubeSearch.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
		SearchListResponse searchResponse = youtubeSearch.execute();
		
		List<SearchResult> searchResultList = searchResponse.getItems();
		YoutubeAPIResult<YoutubeVclip> r = new YoutubeAPIResult<YoutubeVclip>();
		r.setNextPageToken(searchResponse.getNextPageToken());
		
		List<YoutubeVclip> list=new ArrayList<YoutubeVclip>();
		
		
		for(SearchResult searchResult: searchResultList) {
			YoutubeVclip y=new YoutubeVclip();
			searchResult.getId().getVideoId();
			SearchResultSnippet snippet=searchResult.getSnippet();
			String title=snippet.getTitle();
			Thumbnail thumbnailObj = snippet.getThumbnails().get("medium");
			String thumbnail = thumbnailObj.getUrl();
			String link=searchResult.getId().getVideoId();
			y.setLink(link);
			y.setTitle(title);
			y.setThumbnail(thumbnail);
			list.add(y);
		}
		List<String> idList=list.stream()
				.map(youtube ->youtube.getLink())
				.collect(Collectors.toList());

		List<Video> videos=getVideo(String.join(",", idList));
		String fromFmt="yyyy-MM-dd'T'HH:mm:ssZ";
		String toFmt="yyyy-MM-dd HH:mm:ss";
		for(Video video: videos) {
			YoutubeVclip v=list.stream()
			.filter(t -> t.getLink().equals(video.getId()))
			.findAny()
			.get();
			Date date=new Date(video.getSnippet().getPublishedAt().getValue());
			String dateStr="-";
			try {
				dateStr = DateUtil.formatChange(DateUtil.format(date, fromFmt), fromFmt, toFmt);
				dateStr = DateUtil.getFewDaysAgo(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			BigInteger viewCount=video.getStatistics().getViewCount().bigIntegerValue();//조회수
			String playTime=video.getContentDetails().getDuration().replace("PT", "").replace("M", ":").replace("S","");//재생시간
			
			//DateTime dateTime=new DateTime(playTime);
			v.setDate(dateStr);
			v.setPlayTime(playTime);
			v.setViewCount(viewCount);
		}
		r.setItems(list);
		return r;
	}

	private List<Video> getVideo(String ids) throws IOException {
		YouTube.Videos.List youtubeVideoDetail=youtube.videos().list(ids, "id, snippet, contentDetails, statistics");
		youtubeVideoDetail.setFields("items(id,snippet/publishedAt,contentDetails/duration,statistics/viewCount)");
		youtubeVideoDetail.setKey(app_key);
		VideoListResponse videoListResponse = youtubeVideoDetail.execute();
		List<Video> videos=videoListResponse.getItems();
		return videos;
	}
	
}
