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


		<h3 class="m_r_title">节目管理</h3>

		<ul class="nav nav-tabs m_r_tab">
			<li class="active"><a href="javascript:void(0);">节目列表</a></li>
			<a href="/liveBroadcast/goAddLive" class="pull-right btn btn-primary"><i class="iconfont">&#xe608;</i> 新增节目</a>
			<div class="kks_search pull-right m-r20">
				<input type="text" class="kks_s_i" placeholder="输入节目名搜索" id="name"
					onkeypress="if(event.keyCode==13) {loadData(1);return false;}">
				<button type="button" onclick="loadData();" class="kks_s_b">
					<i class="iconfont">&#xe604;</i>
				</button>
			</div>
		</ul>
		<div class="clearfix"></div>

		<div class="kks_sorts">
			<ul>
				<li><span>费用：</span> <select class="form-control" id="payType"
					onchange="loadData();">
						<option value="">全部</option>
						<option value="0">免费</option>
						<option value="1">付费</option>
				</select></li>
				<li><span>状态：</span> <select class="form-control" id="status"
					onchange="loadData();">
						<option value="">全部</option>
						<option value="11">未开始</option>
						<option value="22" >进行中</option>
						<option value="33">已结束</option>
				</select></li>
			</ul>
			<p class="num">
				共计：<span id="totalRecord">0</span>个节目
			</p>
		</div>

		<div class="kks_content">

			<div class="table-responsive">
				<table class="table table-bordered kks_table h80">
					<thead>
						<tr>
							<th>节目名称</th>
							<th width="100">节目日期</th>
                            <th width="100">节目时段</th>
                            <th width="80">费用</th>
                            <th width="80">商品佣金</th>
                            <th width="70">来源</th>
                            <th width="70">状态</th>
                            <th width="70">节目入口</th>
                            <th width="70">聊天记录</th>
                            <th width="100">操作</th>
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
<script src="/static/js/jquery.qrcode.min.js"></script>
<script type="text/javascript"
	src="/static/js/live/livebroadcast.list.js?v=1.0"></script>
</html>