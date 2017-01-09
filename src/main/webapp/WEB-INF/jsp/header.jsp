<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header">
	<div class="header1">
		<div class="container">
			<div class="navbar-header">
				<a class="logo" href="#"><img src="/static/page/images/logo.png"></a>
			</div>
			<ul class="nav navbar-nav h1_nav">
				<li class="dropdown open1"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=request.getAttribute("session_username") %><i class="iconfont">&#xe603;</i></a>
					<ul class="dropdown-menu">
						<li><a target="_blank" href="/changePassword">修改密码</a></li>
						<li><a href="/oauth/logout">退出</a></li>
					</ul>
				</li>
				<li><a href="//www.kakasure.com/help/help3_5.html" target="_blank">帮助中心</a></li>
			</ul>
		</div>
	</div>
</header>
