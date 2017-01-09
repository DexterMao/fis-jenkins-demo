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
<input type="hidden" id="alifee" value="">
<input type="hidden" id="canpay" value="${balance.canPay }">
<input type="hidden" id="forCompany" value="${userplus.forcompny }">
<input type="hidden" id="banklocation" value="${userplus.bankLocation }">
<input type="hidden" id="banktype" value="${userplus.bankType }">
<div class="kks_modal m_b10">
		
	<div  class="form-horizontal" >
		
		<div class="form-group"><span class="font-s14 p-l40">申请提现</span><span class="col-black2 p-l10">(可提现金额：${balance.canPay }元)</span></div>
		<div class="form-group">
			<label for="a_w_money" class="col-xs-3 control-label text-right">提现金额：</label>
			<div class="col-xs-9">
				<input type="number" class="form-control"  min="1" id="amount" onkeyup="alifee();" name="a_w_money" placeholder="输入提现金额" aria-required="true">
			</div>
			<div class="col-xs-offset-3 col-xs-9 error_con"></div>
		</div>
		<div class="form-group">
			<label for="a_w_type" class="col-xs-3 control-label text-right">提现方式：</label>
			<div class="col-xs-9">
				<label class="radio-inline"><input type="radio" name="one" id="ali" onclick="drawType('1');" checked value="00"> 支付宝</label>
				<label class="radio-inline"><input type="radio" name="one" id="can" onclick="drawType('2');" value="01"> 银联</label>
			</div>
		</div>
		 <div id="canpay1">
		<div class="form-group">
			<label for="a_w_zfb" class="col-xs-3 control-label text-right">银行卡号：</label>
			<div class="col-xs-9">
				 <p class="form-control-static">${userplus.bankAccount }</p>
			</div>
		</div>
		<div class="form-group">
			<label for="a_w_zfb" class="col-xs-3 control-label text-right">开户银行：</label>
			<div class="col-xs-9">
				 <p class="form-control-static">${userplus.bankLocation == "y" ?"交通银行":"其他银行" }</p>
			</div>
		</div>
		<div class="form-group">
			<label for="a_w_zfb" class="col-xs-3 control-label text-right">开户人：</label>
			<div class="col-xs-9">
				 <p class="form-control-static">${userplus.bankUserName }</p>
			</div>
		</div>
		</div>
		<div id="alipay">
		<div class="form-group">
			<label for="a_w_zfb" class="col-xs-3 control-label text-right">支付宝账号：</label>
			<div class="col-xs-9">
				 <p class="form-control-static">${userplus.alipayAccount }</p>
			</div>
		</div>
		<div class="form-group">
			<label for="a_w_user" class="col-xs-3 control-label text-right">收款人：</label>
			<div class="col-xs-9">
				 <p class="form-control-static">${userplus.alipayname }</p>
			</div>
		</div>
		</div>
		<div class="form-group">
			<label for="a_w_money1" class="col-xs-3 control-label text-right">手续费：</label>
			<div class="col-xs-9">
				 <p class="form-control-static"><span class="col-yel"><b class="font-s14" id="alifee2">0</b>元</span></p>
			</div>
		</div>
		<div class="form-group">
			<label for="a_w_text" class="col-xs-3 control-label text-right">备注：</label>
			<div class="col-xs-9">
				<textarea name="a_w_text" class="form-control" rows="4" id="description"></textarea>
			</div>
		</div>
		<p class="clearfix h10"></p>
		<div class="form-group">
			<div class="col-xs-offset-3 col-xs-9">
				<button type="button" class="btn btn-primary pull-left m-r20 f-s14" onclick="parentIframe();">提交</button>
				<button type="button" class="btn btn-default pull-left m-r20 f-s14" onclick="closeIframe();">取消</button>
			</div>
		</div>
	</div>
		
</div>
</body>
<script type="text/javascript" src="/static/js/draw/draw.js"></script>
</html>