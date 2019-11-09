<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name='viewport'
	content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>
<title>소프트웨어공학</title>
<link rel="stylesheet"
	href="/static/adminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/static/adminLTE/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/static/adminLTE/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="/static/adminLTE/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="/static/adminLTE/dist/css/skins/_all-skins.min.css">
 <link rel="stylesheet" href="/static/css/style.css">
 

<link rel="stylesheet"
	href="/static/justified/css/justifiedGallery.min.css" />
	
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header"> <!-- Logo --> <a
			href="/index" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><img src="/static/img/mini_logo.png"></span>
			<!-- logo for regular state and mobile devices --> <span
			class="logo-lg"><img src="/static/img/logo.gif"></span>

 
		</a> <!-- Header Navbar: style can be found in header.less --> <nav
			class="navbar navbar-static-top"> <!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- search form -->
				<form action="#" method="get" id="search-form" class="sidebar-form">
					<div class="input-group"> 
					<input type="hidden" name="page" value="${page}" id="page">
						<input type="text" name="q" value="${q}" id="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="button" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
			</ul>
		</div>
		</nav> </header>
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- sideba r menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" data-widget="tree">
			<li><a href="/index"><i class="fa fa-home"></i>
					<span>Home</span></a></li>
			<li><a href="/search/vclip"><i class="fa fa-youtube-play"></i>
					<span>동영상</span></a></li>
			<li><a href="/search/image"><i class="fa fa-image"></i>
					<span>이미지</span></a></li>
		</ul>
		</section> <!-- /.sidebar --> </aside>
		<div class="content-wrapper" style="padding-top: 15px;">
			<sitemesh:write property='body' />
		</div>
	</div>

	<script src="/static/js/jquery-2.2.4.min.js"></script>
	<script type="text/javascript"
		src="/static/adminLTE/dist/js/adminlte.min.js"></script>
<script>
$(document).ready(function(){
	$("#search-form").on("submit",function(){
		if(location.pathname=="/index"){
			$("#search-form").attr("action","/search/vclip");
		}else{
			$("#search-form").attr("action",location.pathname);
		}
	});
});
function active_menu(target_menu) {
    if (target_menu.length > 0 && !target_menu.hasClass("active")) {
        target_menu.addClass("active")
        return active_menu(target_menu.parents("li"));
    }
}
active_menu($(".sidebar-menu li a[href='" + location.pathname + "']"));
</script>
</body>
</html>