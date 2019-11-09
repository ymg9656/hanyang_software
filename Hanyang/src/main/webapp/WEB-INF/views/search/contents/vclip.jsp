<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<c:choose>
<c:when test="${pageType eq 'y' and youtubeVclipList != null and fn:length(youtubeVclipList)>0}">
  <input type="hidden" class="youtubeNextPageToken" value="${youtubeNextPageToken }">
  <c:forEach var="item" items="${youtubeVclipList}">
  <li class="list-group-item"> 
  <div class="card" >
	<a href="https://www.youtube.com/watch?v=${item.link}" target="_blank">
	  <img src="${item.thumbnail }" class="card-img-top" alt="...">
	  <span class="tm_b">${item.playTime}</span>
	</a> 
	  <div class="card-body">
	    <span class="card-title">${item.title}</span>
	     <p class="card-text"><i class="fa fa-play"></i> <fmt:formatNumber value="${item.viewCount}" pattern="#,###"/> | ${item.date}</p>
	  </div>
  </div>
  </li>
  </c:forEach>
</c:when>
<c:when test="${pageType eq 'n' }">
<input type="hidden" class="naverNextPageToken" value="${page }">
  <c:forEach var="item" items="${naverVclipList}">
  <li class="list-group-item"> 
  <div class="card" >
			<a href="https://tv.naver.com${item.link}" target="_blank">
	  <img src="${item.thumbnail }" class="card-img-top" alt="...">
	  <span class="tm_b">${item.playTime}</span>
	</a>
	  <div class="card-body">
	    <span class="card-title">${item.title}</span>
    	<p class="card-text"><i class="fa fa-play"></i> ${item.viewCount} | ${item.date}</p>
	  </div>
  </div>
  </li>
 	</c:forEach>
</c:when>
<c:when test="${pageType eq 'k' }">
 <input type="hidden" id="kakaoNextPage" value="${page }">
  <c:forEach var="item" items="${kakaoVclipList}">
  <li class="list-group-item">
  <div class="card" >
	  <a href="https://tv.kakao.com/channel/${item.channelId}/cliplink/${item.id}" target="_blank">
	  <img src="${item.clip.thumbnailUrl }" class="card-img-top" alt="...">
	  <span class="tm_b">${item.clip.durationStr}</span>
	  </a>
	  <div class="card-body">
	    <span class="card-title">${item.displayTitle}</span>
	    <p class="card-text"><i class="fa fa-play"></i> ${item.clip.playCount} | ${item.createTime}</p>
	  </div>
  </div>
  </li>
  </c:forEach>
</c:when>
</c:choose>
  
