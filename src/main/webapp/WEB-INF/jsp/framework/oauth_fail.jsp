<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/static/v4/css/404.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<title>咔咔硕</title>
</head>
<body>
	<div class="error">
	    <div class="errorerror"></div>
	    <div class="error_con">
	      	<p class="error_text1">很&nbsp;抱&nbsp;歉</p>
	      	<p class="error_text2">${message}</p>
	      	<p class="error_btn"><a href="/" class="btn1">回到首页</a><a href="javascript:history.go(-1);" class="btn1">上一页</a></p>
	    </div>
  	</div>
  	<iframe src="${sso_cas_host_logout }" style="display: none;"/>
</body>
</html>