<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
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

		<h3 class="m_r_title">多媒体管理</h3>

		<ul class="nav nav-tabs m_r_tab">
			<li class="active"><a href="javascript:;">多媒体列表</a></li>
			<div class="kks_search pull-right">
				<input type="text" class="kks_s_i" placeholder="输入多媒体名称/关键字搜索" id="mediaName" onkeypress="if(event.keyCode==13) {loadData(1);return false;}">
				<button type="button" class="kks_s_b" onclick="loadData(1);"><i class="iconfont">&#xe604;</i></button>
			</div>
		</ul>
		<div class="clearfix"></div>
		
		<div class="kks_sorts">
			<ul>
				<li><span>类型：</span>
					<select class="form-control" id="type"  onchange="loadData();">
						<option value="">全部</option>
						<option value="01">视频</option>
						<option value="02">音频</option>
					</select>
				</li>
				<li><span>费用：</span>
					<select class="form-control" id="payType"  onchange="loadData();">
						<option value="">全部</option>
						<option value="0">免费</option>
						<option value="1">付费</option>
					</select>
				</li>
				<li><span>状态：</span>
					<select class="form-control" id="auditStatus"  onchange="loadData();">
						<option value="">全部</option>
						<option value="99">待审核</option>
						<option value="00">审核通过</option>
						<option value="01">审核不通过</option>
					</select>
				</li>
			</ul>
            <p class="num">共计：<span id="totalRecord">0</span>个多媒体</p>
		</div>
		
        <div class="kks_content">
			
			<div class="table-responsive">
				<table class="table table-bordered kks_table h80">
					<thead>
						<tr>
							<th>多媒体名称</th>
                            <th width="80">类型</th>
                            <th width="80">费用（元）</th>
                            <th width="80">商品佣金</th>
                            <th width="80">推广次数</th>
                            <th width="80">扫码次数</th>
                            <th width="80">播放次数</th>
                            <th width="100">状态</th>
                            <th width="120">操作</th>
						</tr>
					</thead>
					<tbody id="tbody_content">
					</tbody>
				</table>
			</div>
			<div class="clearfix"></div>
			
			<div class="kks_page" id="div_page" >
			</div>
		</div>

	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="/static/js/copyrightmulti/copyrightmulti.list.js"></script>
</html>