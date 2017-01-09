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
			<li class="active"><a href="javascript:;">多媒体详情</a></li>
		</ul>
		<div class="clearfix"></div>
		
        <div class="kks_content">
			
			<div class="form-horizontal m_b0" >
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">多媒体类型：</label>
					<div class="col-xs-8">
						<p class="form-control-static">${cm.type=="01"?"视频":"音频"}</p>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">多媒体名称：</label>
					<div class="col-xs-7">
						<p class="form-control-static">${cm.mediaName}</p>		
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">所属类目：</label>
					<div class="col-xs-3">
						<p class="form-control-static">${cm.categoryName }</p>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">标签：</label>
					<div class="col-xs-7">
						<p class="form-control-static">${cm.labelStr }</p>			
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">关键字：</label>
					<div class="col-xs-7">
						<p class="form-control-static">${cm.keywords }</p>	
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">收费类型：</label>
					<div class="col-xs-8">
						<p class="form-control-static">${cm.payType=="0"?"免费":"收费"}</p>
					</div>
				</div>
				<c:if test="${cm.payType== '0'}">
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">商品佣金：</label>
					<div class="col-xs-3">
						<p class="form-control-static">${cm.productConcession}%</p>
					</div>
				</div>
				</c:if>
				<c:if test="${cm.payType== '1'}">
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">多媒体价格：</label>
					<div class="col-xs-3">
						<p class="form-control-static">${cm.price }元</p>
					</div>
				</div>
				</c:if>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">图片：</label>
					<div class="col-xs-8">
                    	<p class="form-control-static"><img src="${cm.picturepath }" width=400 height="225"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">多媒体：</label>
					<div class="col-xs-8">
						<div class="form-control-static">
                            <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="480" height="272" poster="" data-setup="{}">
                                <source src="${cm.path }" type='video/mp4' />
                            </video>
                            <!-- mp3的用法一样的，前面的视频改成音频 -->
                            <!-- <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="480" height="272" poster="" data-setup="{}">
                                <source src="http://192.168.0.188/kks/upload/1.mp3" type='video/mp4' />
                            </video> -->
                        </div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">多媒体描述：</label>
					<div class="col-xs-7">
						<p class="form-control-static">${cm.descr}</p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-7">
						<a href="/copyrightmulti/goCopyrightList" class="btn btn-default">返回</a>
					</div>
				</div>

			</div>

		</div>

	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script src="/static/page/js/jquery-1.11.0.min.js"></script>
<script src="/static/page/js/bootstrap.min.js"></script>
<script src="/static/page/js/common.js"></script>
<script src="/static/js/plugins/layer/layer.js"></script>

<link href="/static/js/videojs/video-js.min.css" rel="stylesheet" type="text/css">
<script src="/static/js/videojs/video.js"></script>
<script>
	videojs.options.flash.swf = "/static/js/videojs/video-js.swf";
</script>
</html>