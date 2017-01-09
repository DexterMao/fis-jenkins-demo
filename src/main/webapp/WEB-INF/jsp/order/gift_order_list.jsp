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
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="kks_main">
		<jsp:include page="../left.jsp"></jsp:include>
		<div class="m_right">
			<h3 class="m_r_title">交易中心</h3>
<input type="hidden" id="canPay" value="${balance.canPay }">
			<div class="withdraw_money">
				<ul class="pull-left">
					<li><p>总收入</p>
						<p>
							<span class="f-s20">${balance.balance }</span> 元
						</p></li>
					<li><p>可转收入</p>
						<p>
							<span class="f-s20" id="canPay_span">${balance.canPay }</span> 元
						</p></li>
				</ul>
				<a href="javascript:intoBalance();" class="pull-right btn btn-primary">转入账户</a>
				<p class="pull-right m-r20">收入可转入版权方账户</p>
			</div>

			<ul class="nav nav-tabs m_r_tab">
				<li class="active"><a href="javascript:;">虚拟礼物收入</a></li>
				<div class="kks_search pull-right">
					<input type="text" class="kks_s_i" placeholder="输入节目名/礼物名搜索"
						id="name"
						onkeypress="if(event.keyCode==13) {loadData(1);return false;}">
					<button type="button" class="kks_s_b" onclick="loadData();">
						<i class="iconfont">&#xe604;</i>
					</button>
				</div>
			</ul>
			<div class="clearfix"></div>

			<div class="kks_sorts">
				<ul>
					<li><span>时间：</span><input type="text"
						class="form-control laydate-icon-default" placeholder="开始时间"
						id="startTime"><span class="p-lr10">至</span><input
						type="text" class="form-control laydate-icon-default"
						placeholder="结束时间" id="endTime"></li>
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
								<th>节目名称</th>
								<th>礼物名称</th>
								<th>数量</th>
								<th>积分</th>
								<th width="100">金额（元）</th>
								<th width="150">赠送时间</th>
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
<script type="text/javascript" src="/static/js/order/gift.order.list.js"></script>
</html>