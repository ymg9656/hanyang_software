<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<body class="home blog woocommerce dokan-theme-dokan">
<section class="section">
<c:choose>
<c:when test="${q == null or q == ''}">
	<div class="container not-found">
		<h2>찾으실 동영상을 검색해주세요.</h2>
	</div>
</c:when>
<c:when test="${fn:length(youtubeVclipList)<=0 
and fn:length(naverVclipList)<=0
and fn:length(kakaoVclipList)<=0
}">
	<div class="container">
		<h2>'<c:out value="${q}"/>'에 대한 검색결과가 없습니다.</h2>
	</div>
</c:when>
<c:otherwise>
    <div class="container">
     	<input type="hidden" id="q" value="${q }">
	    <div class="form-group category-board">
	    	<h4 id="horizontal"><img src="/static/img/youtube_icon.png"><span class="bd-content-title">YouTube</span> <a target="_blank" class="rank-link" href="https://www.youtube.com/results?search_query=<c:out value="${q}"/>"><i class="fa fa-external-link-square"></i></a></h4>
	    	<ul id="youtube-list" class="list-group list-group-horizontal scroll-x">
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
			</ul>
    	</div>	
    	
    	<div class="form-group category-board">
	    	<h4 id="horizontal"><img src="/static/img/naver_tv.png"><span class="bd-content-title">네이버 Tv</span> <a target="_blank" class="rank-link" href="https://tv.naver.com/search/clip?query=<c:out value="${q}"/>"><i class="fa fa-external-link-square"></i></a></h4>
	    	<ul id="naver-list" class="list-group list-group-horizontal scroll-x">
			   <input type="hidden" class="naverNextPage" value="${page }">
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
			</ul>
    	</div>	
    	
    	<div class="form-group category-board">
	    	<h4 id="horizontal"><img src="/static/img/kakao_tv.png"><span class="bd-content-title">카카오 Tv</span> <a target="_blank" class="rank-link" href="https://tv.kakao.com/search?q=<c:out value="${q}"/>"><i class="fa fa-external-link-square"></i></a></h4>
	    	<ul id="kakao-list" class="list-group list-group-horizontal scroll-x">
			  <input type="hidden" class="kakaoNextPage" value="${page }">
			  <c:forEach var="item" items="${kakaoVclipList}">
			  <li class="list-group-item">
			  <div class="card" >
				  <a href="https://tv.kakao.com${item.link}" target="_blank">
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
			</ul>
    	</div>
    </div>
</c:otherwise>
</c:choose>
</section>
<script src="/static/js/jquery-2.2.4.min.js"></script>
<script>

var youtube_is_running=false;
var naver_is_running=false;
var kakao_is_running=false;
$(document).ready(function(){  
	$("#youtube-list").scroll(function(){
		var scrollWidth=document.getElementById("youtube-list").scrollWidth;
		var scrollLeft=$("#youtube-list").scrollLeft()+$("#youtube-list").width();
		if(scrollWidth-scrollLeft<=100 && !youtube_is_running){
			youtube_is_running=true;
			getList("y",$(".youtubeNextPageToken").last().val(),$("#q").val(),$("#youtube-list"),youtube_is_running);   
	    } 
	}); 
	$("#naver-list").scroll(function(){
		var scrollWidth=document.getElementById("naver-list").scrollWidth;
		var scrollLeft=$("#naver-list").scrollLeft()+$("#naver-list").width();
		if(scrollWidth-scrollLeft<=100 && !naver_is_running){
			naver_is_running=true;
			getList("n",$(".naverNextPage").last().val(),$("#q").val(),$("#naver-list"),naver_is_running);
	    } 
	}); 
	$("#kakao-list").scroll(function(){
		var scrollWidth=document.getElementById("kakao-list").scrollWidth;
		var scrollLeft=$("#kakao-list").scrollLeft()+$("#kakao-list").width();
		if(scrollWidth-scrollLeft<=100 && !kakao_is_running){
			kakao_is_running=true;
			getList("n",$(".kakaoNextPage").last().val(),$("#q").val(),$("#kakao-list"),kakao_is_running);
	    } 
	}); 
}); 
function getList(pageType,page,q,el,is_running){
	 $.ajax({
	        type : 'GET',   
	        data : {"page" : page,"q":q},
	        url : '/search/'+pageType+'/contents/vclip',
	        success : function(r) { 
	        	if($.trim(r)){
	        		el.append(r)
	        		is_running=false;
	        	}
	       },error:function(e){ 
	           if(e.status!=200){
	               alert("데이터를 가져오는데 실패하였습니다.");
	           };
	       }
	    }); 
}
</script>
</body>
</html>