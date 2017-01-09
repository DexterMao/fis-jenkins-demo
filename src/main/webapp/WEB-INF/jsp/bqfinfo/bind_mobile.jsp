<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body class="bg-white">
<input type="hidden" id="phone" name="phone" value="${phone }">
<div class="kks_modal">

	<div class="form-horizontal">
		<div id="step1">
			<div class="form-group m-0">
				<label for="" class="col-xs-2 control-label text-right">原手机号：</label>
				<div class="col-xs-3">
					<p class="form-control-static">${phone }</p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-xs-offset-2">
					<img id="codeImg1" src="" style="float:left; margin-right:15px;" onclick="changeCode(this);">
	                   <input type="text" id="yzm1" class="form-control pull-left" style="width:110px" placeholder="请输入答案" maxlength="4">
				</div>
				<div class="col-xs-3">
					<a href="javascript:getCode(1);" class="btn btn-default btn-block" id="get_code1">获取短信验证码</a>
				</div>
			</div>
			<div class="form-group">
				<label for="" class="col-xs-2 control-label text-right">验证码：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" id="hasCode"
						name="hasCode" placeholder="请输入手机收到的短信验证码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-7">
					<a href="javascript:hasCode();"
						class="btn btn-primary pull-left m-r20 font-s14" id="step_next">下一步</a>
					<a href="javascript:;"
						class="btn btn-default pull-left m-r20 font-s14"
						onclick="parent.layer.closeAll()">取消</a>
				</div>
			</div>
		</div>
		<div id="step2">
			<div class="form-group">
				<label for="" class="col-xs-2 control-label text-right">手机号：</label>
				<div class="col-xs-6">
					<input type="text" class="form-control" id="newPhone" name="newPhone" placeholder="输入要绑定的手机号">
				</div>
			</div>
			<div class="form-group">
					<div class="col-xs-6 col-xs-offset-2">
						<img id="codeImg2" src="" style="float:left; margin-right:15px;" onclick="changeCode(this);">
	                    <input type="text" id="yzm2" class="form-control pull-left" style="width:110px" placeholder="请输入答案" maxlength="4">
					</div>
					<div class="col-xs-3">
						<a href="javascript:getCode(2);" class="btn btn-default btn-block" id="get_code2">获取短信验证码</a>
					</div>
				</div>
			<div class="form-group">
				<label for="" class="col-xs-2 control-label text-right">验证码：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" id="Code" name="Code"
						placeholder="请输入手机收到的验证码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-7">
					<button type="button"
						class="btn btn-primary pull-left m-r20 font-s14"
						onclick="editPhone();">绑定</button>
					<a href="javascript:;"
						class="btn btn-default pull-left m-r20 font-s14"
						onclick="parent.layer.closeAll()">取消</a>
				</div>
			</div>
		</div>
	</div>

</div>
<div class="bg-gray l-h-20" style="padding:20px 20px 20px 50px;">
	<p>
		没收到短信验证码？<br>1.网络通讯异常可能会造成短信丢失，请重新获取或稍后再试。<br>2.请核实手机是否已欠费停机，或者屏蔽了系统短信。<br>3.特殊情况（如手机丢失）无法获取验证码，
		请致电咔咔硕：400-690-0571
	</p>
</div>

<script type="text/javascript"
	src="/static/js/bqfinfo/bind.mobile.js?v=2016111817"></script>
</body>
</html>