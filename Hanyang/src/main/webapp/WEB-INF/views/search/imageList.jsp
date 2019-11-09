<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<body class="home blog woocommerce dokan-theme-dokan">
<section class="section">
<c:choose>
<c:when test="${q == null or q == ''}">
	<div class="container not-found">
		<h2>찾으실 이미지를 검색해주세요.</h2>
	</div>
</c:when>
<c:when test="${fn:length(imageList)<=0}">
	<div class="container not-found"> 
		<h2>'<c:out value="${q}"/>'에 대한 검색결과가 없습니다.</h2>
	</div>
</c:when>
<c:otherwise>
    <div id="image_wrapper"> 
       <c:forEach var="item" items="${imageList}" varStatus="index">
	   <a href="${item.link}" target="_blank"><img style="padding: 2px;" alt="${item.type}" src="${item.thumbnail}"></a>
	   </c:forEach>
    </div>  
</c:otherwise>
</c:choose>
</section>  
<script src="/static/js/jquery-2.2.4.js"></script>
<script>
var page=1; 
var is_running=false;
function initJustified(){
	$("#image_wrapper").justifiedGallery();	
}
$(document).ready(function(){  
	$(window).scroll(function(){
		var height=$(document).height() - $(window).height() - 300;
		var scrollTop=$(window).scrollTop();
		if($(window).scrollTop() >= $(document).height() - $(window).height()){
	       if(!is_running){
	    	   page++; 
		       getList(page);   
	       }
			
	    } 
	}); 
}); 

function getList(page){
    $.ajax({
        type : 'GET',   
        dataType : 'json', 
        data : {"page" : page,"q":"${q}"},
        url : '/rest/search/image',
        success : function(imageList) { 
        	for(var i = 0; i < imageList.length; i++){
        		var imageJson=imageList[i];
        		$("#image_wrapper").append('<a href="'+imageJson.link+'" target="_blank"><img alt="'+imageJson.type+'" src="'+imageJson.thumbnail+'"></a>');
        	}
        	is_running=false;
       },error:function(e){ 
           if(e.status!=200){
               alert("데이터를 가져오는데 실패하였습니다.");
           };
           is_running=false;
       }
    }); 
}
</script>
<script src="/static/justified/js/jquery.justifiedGallery.min.js" 
onload = "initJustified()"></script>
</body>
</html>