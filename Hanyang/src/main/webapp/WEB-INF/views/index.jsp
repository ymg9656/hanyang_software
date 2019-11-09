<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<body>

	<section class="content-header">
      <h1 style="line-height: 40px;">급상승 검색어  <small style="line-height: 20px;">검색 횟수가 급상승한 검색어의 구글,유튜브,네이버,다음 자세히 제공합니다.</small></h1>
    </section>
		<section class="section">
		    <div class="content">
		    	<div class="row">
		    	<div class="col-xs-12 col-md-3 col-lg-3"> 
		    	<div class="box box-none">
		            <div class="box-header with-border">
		              <h3 class="box-title"><img src="/static/img/naver_icon.jpg">네이버 </h3>
		              <a target="_blank" class="rank-link" href="https://datalab.naver.com/keyword/realtimeList.naver?where=main"><i class="fa fa-external-link-square"></i></a>
		              <div class="box-tools pull-right">
		                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
		                </button>
		              </div>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		              <ul class="nav nav-stacked rank-ul">
		                <c:forEach var="item" items="${naverRealTimeRankList }" varStatus="status">
				    	<li class="txt-overflow n-rank-li"><a href="/search/vclip?q=${item.html}"><b>${status.index+1 }.</b> ${item.html}</a></li>
				    	</c:forEach>
				    	<c:if test="${fn:length(naverRealTimeRankList)<=0 }">
				    	<li class="txt-overflow">데이터를 불러오지 못하였습니다.</li>
				    	</c:if>
		                <!-- /.item -->
		              </ul>
		            </div>
		            <!-- /.box-body -->
		          </div>
		    	</div>
		    	
		    	<div class="col-xs-12 col-md-3 col-lg-3">
		    	<div class="box box-none">
		            <div class="box-header with-border">
		              <h3 class="box-title"><img src="/static/img/daum_icon.png">다음</h3>
		             <a  target="_blank" class="rank-link" href="https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=실시간 이슈 검색어"><i class="fa fa-external-link-square"></i></a>
		              <div class="box-tools pull-right">
		                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
		                </button>
		              </div>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		              <ul class="nav nav-stacked rank-ul">
		                <c:forEach var="item" items="${daumRealTimeRankList }" varStatus="status">
				    		<li class="txt-overflow d-rank-li"><a href="/search/vclip?q=${item.html}"><b>${status.index+1 }.</b> ${item.html}</a></li>
				    	</c:forEach>
				    	<c:if test="${fn:length(daumRealTimeRankList)<=0 }">
				    	<li class="txt-overflow">데이터를 불러오지 못하였습니다.</li>
				    	</c:if>
		                <!-- /.item -->
		              </ul>
		            </div>
		            <!-- /.box-body -->
		          </div>
		    	</div>
		    	<div class="col-xs-12 col-md-3 col-lg-3">
		    	<div class="box box-none">
		            <div class="box-header with-border">
		              <h3 class="box-title"><img src="/static/img/google_icon.png"> Google</h3>
		              <a  target="_blank" class="rank-link" href="https://trends.google.co.kr/trends/trendingsearches/daily?geo=KR"><i class="fa fa-external-link-square"></i></a>
		              <div class="box-tools pull-right">
		                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
		                </button>
		              </div>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		              <ul class="nav nav-stacked rank-ul">
		                <c:forEach var="item" items="${googleRealTimeRankList }" varStatus="status">
				    		<li class="txt-overflow g-rank-li"><a href="/search/vclip?q=${item.html}"><b>${status.index+1 }.</b> ${item.html}</a></li>
				    	</c:forEach>
				    	<c:if test="${fn:length(googleRealTimeRankList)<=0 }">
				    	<li class="txt-overflow">데이터를 불러오지 못하였습니다.</li>
				    	</c:if>
		                <!-- /.item -->
		              </ul>
		            </div>
		            <!-- /.box-body -->
		          </div>
		    	</div>
		    	<div class="col-xs-12 col-md-3 col-lg-3">
		    	<div class="box box-none">
		            <div class="box-header with-border">
		              <h3 class="box-title"><img src="/static/img/youtube_icon.png"> YouTube</h3>
		              <a  target="_blank" class="rank-link" href="https://www.youtube.com/feed/trending" ><i class="fa fa-external-link-square"></i></a>
		              <div class="box-tools pull-right">
		                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
		                </button>
		              </div>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		              <ul class="nav nav-stacked rank-ul">
		                <c:forEach var="item" items="${youtubeRealTimeRankList }" varStatus="status">
				    		<li class="txt-overflow y-rank-li"><a href="/search/vclip?q=${item.html}"><b>${status.index+1 }.</b> ${item.html}</a></li>
				    	</c:forEach>
				    	<c:if test="${fn:length(youtubeRealTimeRankList)<=0 }">
				    	<li class="txt-overflow">데이터를 불러오지 못하였습니다.</li>
				    	</c:if>
		                <!-- /.item -->
		              </ul>
		            </div>
		            <!-- /.box-body -->
		          </div>
		          </div>
		    </div>
		    </div>
		</section>
	</main>
<script src="/static/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var n=0;
	var d=0;
	var g=0;
	var y=0;
	var rolling;
	clearInterval(rolling);
	function rollingFunction(){
		rolling=setInterval(function(){
			if((n+1)==$(".n-rank-li").length){
				$(".n-rank-li").show();
				n=0;
			}else{
				$(".n-rank-li").eq(n).hide("slow",function(){n++;});	
			}
			 
			if((d+1)==$(".d-rank-li").length){
				$(".d-rank-li").show();
				d=0;
			}else{
				$(".d-rank-li").eq(d).hide("slow",function(){d++;});	
			}
			 
			if((g+1)==$(".g-rank-li").length){
				$(".g-rank-li").show();
				g=0;
			}else{
				$(".g-rank-li").eq(g).hide("slow",function(){g++; });	
			}
			 
			if((y+1)==$(".y-rank-li").length){
				$(".y-rank-li").show();
				y=0;
			}else{
				$(".y-rank-li").eq(y).hide("slow",function(){y++;});	
			} 
		}, 2000);
	}
	$(window).resize( function() {
		if($(window).width()<760){
			rollingFunction();
		}else{
	 		clearInterval(rolling);
		}
	});
	$(window).trigger("resize");
});
</script>
</body>
</html>