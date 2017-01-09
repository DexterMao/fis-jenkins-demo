<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<jsp:include page="./head.jsp"></jsp:include>
<link href="/static/page/css/login.css" rel="stylesheet">
</head>

<body>
	<div class="bg_login"></div>
	<div class="header_login">
		<a href="javascript:;" class="logo"><img
			src="/static/page/images/logo_login.png"></a><span>欢迎加入！</span>
	</div>
	<div class="main_login">
		<p class="l_title">修改密码</p>
		<div class="m_l_con">
			<div class="login_form">
				<div class="form-horizontal">
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">当前密码：</label>
						<div class="col-xs-6">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="">
						</div>
						<div class="col-xs-4 error_con"></div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">新密码：</label>
						<div class="col-xs-6">
							<input type="password" class="form-control" id="passwordNew"
								name="passwordNew" placeholder="6-20个字符">
						</div>
						<div class="col-xs-4 error_con"></div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">确认新密码：</label>
						<div class="col-xs-6">
							<input type="password" class="form-control" id="passwordNew2"
								name="passwordNew2" placeholder="请再次输入新密码">
						</div>
						<div class="col-xs-4 error_con"></div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-2 col-xs-10">
							<button type="button" class="btn btn-primary f-s14"
								onclick="changePassword();">确定</button>
						</div>
					</div>

				</div>
			</div>
			<!-- <div class="login_right">
				<p>
					<a href="#" class="f-s14 text-blue">忘记密码？</a>
				</p>
			</div> -->
		</div>
	</div>

	<div class="login_footer">
		Copyright Reserved By kakasure.com @2013-2015 咔咔硕 版权所有<br>办公地址：浙江省杭州市西湖区文三路90号东部软件园科技大厦A406
		邮编：310012 备案号：沪ICP备14025400号-1
	</div>
	<script type="text/javascript" src="/static/js/changePassword.js"></script>
</body>
</html>