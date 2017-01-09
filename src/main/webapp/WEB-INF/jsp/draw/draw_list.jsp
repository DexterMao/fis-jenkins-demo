<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
	<input type="hidden" id="alipayaccount" value="${userplus.alipayAccount }">
	<input type="hidden" id="alipayname" value="${userplus.alipayname }">
	<input type="hidden" id="bankaccount" value="${userplus.bankAccount }">
	<input type="hidden" id="bankname" value="${userplus.bankName }">
	<input type="hidden" id="bankusername" value="${userplus.bankUserName }">
	<input type="hidden" id="phone" value="${userplus.phone }">
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="kks_main">
		<jsp:include page="../left.jsp"></jsp:include>
		<div class="m_right">

			<h3 class="m_r_title">个人中心</h3>

			<ul class="nav nav-tabs m_r_tab">
				<li class="active"><a href="javascript:;">提现管理</a></li>
			</ul>
			<div class="clearfix"></div>

			<div class="withdraw_money">
				<ul class="pull-left">
					<li><p>我的收益</p>
						<p>
							<span class="f-s20">${balance.balance }</span> 元
						</p></li>
					<li><p>历史提现金额</p>
						<p>
							<span class="f-s20">${balance.paied }</span> 元
						</p></li>
					<li><p>可提现金额</p>
						<p>
							<span class="f-s20">${balance.canPay }</span> 元
						</p></li>
					<li><p>提现中金额</p>
						<p>
							<span class="f-s20">${balance.paying }</span> 元
						</p></li>
				</ul>
				<a onclick="drawshenhe();" class="pull-right btn btn-primary" id="sqtx">申请提现</a>
				<a href="javascript:;" class="pull-right m-r20" id="txsm">提现说明</a>
			</div>

			<div class="kks_sorts">
				<ul>
					<li><span>时间：</span><input type="text"
						class="form-control laydate-icon-default" placeholder="开始时间"
						id="startTime"><span class="p-lr10">至</span><input
						type="text" class="form-control laydate-icon-default"
						placeholder="结束时间" id="endTime"></li>
					<li><span>状态：</span> <select class="form-control" id="status"
						onchange="loadData();">
							<option value="">全部</option>
							<option value="0">审核中</option>
							<option value="1">提现成功</option>
							<option value="2">提现失败</option>
							<option value="3">银行直连处理</option>
					</select></li>
				</ul>
				<p class="num">
					共计：<span id="totalRecord">0</span>条记录
				</p>
			</div>

			<div class="kks_content">

				<div class="table-responsive">
					<table class="table table-bordered kks_table">
						<thead>
							<tr>
								<th>账户</th>
								<th>账户名称</th>
								<th>提现金额（元）</th>
								<th>手续费（元）</th>
								<th>备注</th>
								<th width="150">申请提交时间</th>
								<th>提现状态</th>
							</tr>
						</thead>
						<tbody id="tbody_content">
						</tbody>
					</table>
				</div>
				<div class="clearfix"></div>
				<div class="kks_page" id="div_page"></div>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function() {
		laydate({
			elem : '#startTime',
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			festival : true, //显示节日
			choose : function(datas) { //选择日期完毕的回调
				loadData();
			}
		});
		laydate({
			elem : "#endTime",
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			festival : true, //显示节日
			choose : function(datas) { //选择日期完毕的回调
				loadData();
			}
		});

	});
</script>
<script type="text/javascript" src="/static/js/draw/draw.list.js"></script>
</html>