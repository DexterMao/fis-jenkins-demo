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

			<h3 class="m_r_title">多媒体管理</h3>

			<ul class="nav nav-tabs m_r_tab">
				<li class="active"><a href="javascript:;">上传多媒体</a></li>
			</ul>
			<div class="clearfix"></div>

			<div class="kks_content">

				<div class="form-horizontal">
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">多媒体类型：</label>
						<div class="col-xs-8">
							<label class="radio-inline m-r20"><input type="radio"
								name="type" id="type1" value="01" checked="checked"
								onclick="checkType('01');"> 视频</label> <label
								class="radio-inline m-r20"><input type="radio"
								name="type" id="type2" value="02" onclick="checkType('02');">
								音频</label>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">多媒体名称：</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" placeholder="最多输入32个字符"
								id="mediaName" maxlength="32">
						</div>
					</div>
					<c:if test="${not empty categories}">
						<div class="form-group">
							<label for="" class="col-xs-2 control-label">所属类目：</label>
							<div class="col-xs-3">
								<select class="form-control" id="videoCategoryId">
									<option value="">请选择类目</option>
									<c:forEach items="${categories}" var="v">
										<option value="${v.videoCategoryId}">${v.categoryName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">标签：</label>
						<div class="col-xs-7">
							<input type="text" class="form-control"
								placeholder="多个标签用，隔开.每个最多10字" id="labelStr">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">关键字：</label>
						<div class="col-xs-7">
							<input type="text" class="form-control"
								placeholder="请输入关键字,最多64字" id="keywords" maxlength="64">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">收费类型：</label>
						<div class="col-xs-8">
							<label class="radio-inline m-r20"><input type="radio"
								name="payType" id="type11" value="0" checked="checked"
								onclick="checkpayType('0');"> 免费</label> <label
								class="radio-inline m-r20"><input type="radio"
								name="payType" id="type12" value="1"
								onclick="checkpayType('1');"> 收费</label>
						</div>
					</div>
					<div class="form-group" id="mf">
						<label for="" class="col-xs-2 control-label">商品佣金：</label>
						<div class="col-xs-3">
							<div class="input-group">
								<input type="number" class="form-control" placeholder="码商所得佣金"
									id="productConcession"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group" id="sf">
						<label for="" class="col-xs-2 control-label">多媒体价格：</label>
						<div class="col-xs-3">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="请输入价格"
									id="price"> <span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">上传图片：</label> <input
							type="hidden" name="picturepath" id="picturepath" value=""
							placeholder="图片路径" title="图片路径" readonly="readonly" />
						<div class="col-xs-8">
							<ul class="upload_img">
								<li id="img_add"><a href="javascript:;" class="add_pic"><input
										type="text" name="img_upload" class="img_validate" value=""><img
										src="" width="200" height="112"><span><i
											class="iconfont ico_del">&#xe609;</i></span><i
										class="iconfont ico_add">&#xe608;</i><br>添加图片</a></li>
							</ul>
							<p class="l-h-20 col-gray">
								仅能上传一张图片，大小不能超过3M，<br>建议尺寸：800*450，格式支持：jpeg、jpg、png
							</p>
						</div>
					</div>
					<div class="form-group" id="type_video">
						<label for="" class="col-xs-2 control-label">上传视频：</label> <input
							type="hidden" name="path" id="path" value="" placeholder="多媒体路径"
							readonly="readonly" />
						<div class="col-xs-3">
							<input type="file" class="form-control" id="video"
								accept=".mp4,.webm,.mpeg,.ogg,.wmv" />
						</div>
						<p class="col-gray">
							建议尺寸：480*272、640*360、672*378、720*480<br>格式支持：mp4、webm、mpeg、ogg、wmv
						</p>
					</div>
					<div class="form-group" id="type_music">
						<label for="" class="col-xs-2 control-label">上传音频：</label>
						<input type="hidden" name="audioPath" id="audioPath"
								readonly="readonly" />
						<div class="col-xs-3">
							<input type="file" id="audio" class="form-control" accept=".mp3" />
						</div>
						<p class="col-gray">格式支持：mp3</p>
					</div>
					<input type="hidden" id="input_progressbar">
					<div class="form-group hidden">
						<div class="col-xs-offset-2 col-xs-7">
							<div class="progress m-0" id="progressbar">
								
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">多媒体描述：</label>
						<div class="col-xs-7">
							<textarea name="" class="form-control" rows="5"
								placeholder="最多输入2000个字符" id="descr" maxlength="2000"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-2 col-xs-7">
							<button type="button" class="btn btn-primary pull-left f-s14"
								onclick="sava();">保存</button>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>
	<input type="hidden" id="host_image" value="${host_image }" />
	<script src="/static/xdomain/xdomain.min.js"
		slave="http://image.kakasure.cn/proxy.html"></script>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
<link href="/static/uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script src="/static/uploadify/jquery.uploadify.v2.1.4.min.js"
	type="text/javascript"></script>
<script src="/static/uploadify/swfobject.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/static/js/copyrightmulti/copyrightmulti.insert.js"></script>

</html>