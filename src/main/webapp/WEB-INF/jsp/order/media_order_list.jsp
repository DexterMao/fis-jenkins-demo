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
			<li class="active"><a href="javascript:;">多媒体/节目收入</a></li>
			<div class="kks_search pull-right">
				<input type="text" class="kks_s_i" placeholder="输入节目名/多媒体名称搜索" id="name" onkeypress="if(event.keyCode==13) {loadData(1);return false;}">
				<button type="button" class="kks_s_b"><i class="iconfont" onclick="loadData();">&#xe604;</i></button>
			</div>
		</ul>
		<div class="clearfix"></div>
		
		<div class="kks_sorts">
            <p class="num">共计：<span id="totalRecord">0</span>条记录</p>
		</div>
		
        <div class="kks_content">
			
			<div class="table-responsive">
				<table class="table table-bordered kks_table">
					<thead>
						<tr>
							<th>多媒体/节目名称</th>
                            <th width="80">类型</th>
							<th>买家账号</th>
                            <th width="80">价格（元）</th>
							<th width="110">码商收入（元）</th>
                            <th width="120">平台服务费（元）</th>
							<th width="120">版权方收入（元）</th>
							<th width="90">成交时间</th>
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
	src="/static/js/order/media.order.list.js"></script>
</html>