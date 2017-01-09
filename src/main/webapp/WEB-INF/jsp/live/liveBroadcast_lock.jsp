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
				<li class="active"><a href="javascript:;">节目详情</a></li>
			</ul>
			<div class="clearfix"></div>

			<div class="kks_content">

				<div class="form-horizontal m_b0">
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目名称：</label>
						<div class="col-xs-8">
							<p class="form-control-static">${lb.name }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目日期：</label>
						<div class="col-xs-8">
							<p class="form-control-static"><fmt:formatDate pattern="yyyy.MM.dd" value="${lb.dateStartTime }"/> ~<fmt:formatDate pattern="yyyy.MM.dd" value="${lb.dateEndTime }"/></p>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目时段：</label>
						<div class="col-xs-8">
							<p class="form-control-static">${lb.dateStartIntervalStr }~${lb.dateEndIntervalStr }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">商品佣金：</label>
						<div class="col-xs-8">
							<p class="form-control-static">${lb.productConcession } %</p>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">收费类型：</label>
						<div class="col-xs-8">
							<c:if test="${lb.payType == '0'}">
								<p class="form-control-static">免费</p>
							</c:if>
							<c:if test="${lb.payType == '1'}">
								<p class="form-control-static">${lb.price }元</p>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">图片：</label>
						<div class="col-xs-8">
							<p class="form-control-static">
								<img src="${lb.picturePath }" width="200" height="112">
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目描述：</label>
						<div class="col-xs-8">
							<p class="form-control-static l-h-25" style="padding-top: 3px;">${lb.descr }</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>