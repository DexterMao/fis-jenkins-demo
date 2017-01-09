<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>咔咔硕</title>
<meta name="description" content="咔咔硕" />
<meta name="author" content="kakasure" />
<meta name="keyword" content="咔咔硕" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/static/page/css/bootstrap.min.css" rel="stylesheet">
<script src="/static/page/js/jquery-1.11.0.min.js"></script>
<script src="/static/page/js/bootstrap.min.js"></script>
<script src="/static/page/js/common.js"></script>
<script src="/static/js/kks.bqf.js"></script>
<script src="/static/js/plugins/laydate/laydate.js"></script>
<script src="/static/js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/static/js/laypage/laypage.js"></script>
<link href="/static/page/css/login.css" rel="stylesheet">
<!--[if lt IE 9]>
	<script src="/static/page/js/html5shiv.min.js"></script>
	<script src="/static/page/js/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="bg_login"></div>
	<div class="header_login"><a href="#" class="logo"><img src="/static/page/images/logo_login.png"></a><span>欢迎加入！</span></div>
	<div class="main_login">
		<p class="l_title">版权方注册</p>
		<div class="m_l_con">
			<div class="login_form">
				<div class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name" name="name" placeholder="2-16个字符，不能以数字开头" minlength="2" maxlength="16" aria-required="true">
							<!-- onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\@\.\_\-]/g,'')" -->
						</div>
						<div class="col-sm-4 error_con"><label class="control-label col-black2 pull-left p-r10"><span class="name_num col-black1">0</span>/16</label></div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password" placeholder="6-20个字符" aria-required="true">
						</div>
						<div class="col-sm-4 error_con"></div>
					</div>
					<div class="form-group">
						<label for="password1" class="col-sm-2 control-label">确认密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password1" name="password1" placeholder="请再次输入密码" aria-required="true">
						</div>
						<div class="col-sm-4 error_con"></div>
					</div>
					<div class="form-group">
						<label for="tel" class="col-xs-2 control-label">手机号：</label>
						<div class="col-xs-4">
							<input type="tel" class="form-control" id="tel" name="tel" placeholder="请输入手机号">
						</div>
						<div class="col-xs-2">
                        	<a href="javascript:;" class="btn btn-default btn-block" id="get_code">获取短信验证码</a>
                            <div style="position:absolute; width:215px; right:115px; top:0px; height:32px;z-index:3; background:#fff;overflow:hidden; display:none">
                            <img id="codeImg" src="/index/yzm" style="float:left; margin-right:15px;" onclick="changeCode(this);">
                            <input type="text" id="yzm" name="yzm" class="form-control pull-left" style="width:110px" placeholder="请输入答案"></div>
                        </div>
					</div>
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">短信验证码：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="code" name="code" placeholder="输入短信验证码" aria-required="true">
						</div>
						<div class="col-sm-4 error_con"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary font-s14" onclick="register();">注册</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">注册代表已阅读并同意<a href="http://www.kakasure.com/home/help/help3_5.html" class="col-blue" target="_Blank">《咔咔硕版权方入驻协议》</a></div>
					</div>
				</div>
			</div>
			<div class="login_right">
				<p>已有版权方账号？<br><a href="/" class="font-s14 col-blue">立即登录</a></p>
			</div>
		</div>
	</div>
<div class="login_footer">Copyright Reserved By kakasure.com @2013-2015 咔咔硕 版权所有<br>办公地址：浙江省杭州市西湖区文三路90号东部软件园科技大厦A406　邮编：310012　备案号：沪ICP备14025400号-1</div>
<script type="text/javascript" src="/static/js/register.js?v=2016111817"></script>
</body>
</html>