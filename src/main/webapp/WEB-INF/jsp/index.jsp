<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="kks_main">
	<jsp:include page="left.jsp"></jsp:include>
	<div class="m_right">
		<div class="index1">
			<div class="col-xs-3 text-center"><a href="#"><p>已上架商品</p><h4>500</h4></a></div>
			<div class="col-xs-3 text-center"><a href="#"><p>未上架商品</p><h4>0</h4></a></div>
			<div class="col-xs-3 text-center"><a href="#"><p>待发货订单</p><h4>15</h4></a></div>
			<div class="col-xs-3 text-center"><a href="#"><p>待付款订单</p><h4>8</h4></a></div>
		</div>

		<div class="index2">
			<div class="col-xs-4 text-center"><a href="#"><i class="iconfont text-blue">&#xe601;</i><p>总订单数</p><h4>800</h4></a></div>
			<div class="col-xs-4 text-center"><a href="#"><i class="iconfont text-green">&#xe600;</i><p>上周成交金额</p><h4><small>￥</small>2,000.00</h4></a></div>
			<div class="col-xs-4 text-center"><a href="#"><i class="iconfont f-s60 text-yel">&#xe602;</i><p>总成交金额</p><h4><small>￥</small>14,500.00</h4></a></div>
		</div>

		<div class="index3">
			<div class="col-xs-6">
				<div class="ec" id="ec1"></div>
			</div>
			<div class="col-xs-6">
				<div class="ec" id="ec2"></div>
			</div>
		</div>

	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="/static/page/js/index_echarts.js"></script>
</body>
</html>
