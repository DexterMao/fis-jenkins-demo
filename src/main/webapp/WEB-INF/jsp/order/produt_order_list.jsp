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

		<ul class="nav nav-tabs m_r_tab">
			<li class="active"><a href="javascript:;">交易中心</a></li>
			<div class="kks_search pull-right">
				<input type="text" class="kks_s_i" placeholder="输入订单号/商品名搜索" id="name" onkeypress="if(event.keyCode==13) {loadData(1);return false;}">
				<button type="button" class="kks_s_b"><i class="iconfont" onclick="loadData();">&#xe604;</i></button>
			</div>
		</ul>
		<div class="clearfix"></div>
		
		<div class="kks_sorts">
			<ul>
				<li><span>状态：</span>
					<select class="form-control" id="type" onchange="loadData();">
						<option value="">全部</option>
						<option value="1">多媒体</option>
						<option value="2">节目</option>
					</select>
				</li>
				<li><span>类型：</span>
					<select class="form-control" id="status" onchange="loadData();">
						<option value="">全部</option>
						<option value="0">待处理</option>
						<option value="4">交易失败</option>
						<option value="100">交易成功</option>
					</select>
				</li>
			</ul>
            <p class="num">共计：<span id="totalRecord">0</span>条记录</p>
		</div>
		
        <div class="kks_content">
			
			<div class="table-responsive">
				<table class="table table-bordered kks_table">
					<thead>
						<tr>
							<th>订单号</th>
                            <th width="170">商品</th>
							<th>多媒体/节目名称</th>
                            <th width="100">类型</th>
							<th width="110">商品佣金（元）</th>
                            <th width="150">成交时间</th>
							<th>订单状态</th>
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
<script type="text/javascript"
	src="/static/js/order/product.order.list.js"></script>
</html>