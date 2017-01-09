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
				<li class="active"><a href="javascript:;">新增节目</a></li>
			</ul>
			<div class="clearfix"></div>

			<div class="kks_content">

				<div class="form-horizontal">
					<!-- <div class="form-group">
						<label for="" class="col-xs-2 control-label">选择来源：</label>
						<div class="col-xs-8">
							<label class="radio-inline m-r20 pull-left"><input type="radio" name="channelType" id="source1" value="02" checked> 腾讯</label>
							<label class="radio-inline m-r20 pull-left"><input type="radio" name="channelType" id="source2" value="01"> 展视</label>
						</div>
					</div> -->
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目名称：</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" placeholder="最多输入32个字符"
								maxlength="32" id="name">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目日期：</label>
						<div class="col-xs-3">
							<input type="text" class="form-control layer-date laydate-icon-default"
								placeholder="选择开始日期" id="dateStartTime" readOnly="true">
						</div>
						<div class="col-xs-3">
							<input type="text"
								class="form-control layer-date laydate-icon-default"
								placeholder="选择结束日期" id="dateEndTime" readOnly="true">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目时段：</label>
						<div class="col-xs-3">
							<input type="text"
								class="form-control layer-time laydate-icon-default"
								placeholder="选择开始时间段" data-autoclose="true"
								id="dateStartInterval" readOnly="true">
						</div>
						<div class="col-xs-3">
							<input type="text"
								class="form-control layer-time laydate-icon-default"
								placeholder="选择结束时间段" data-autoclose="true" id="dateEndInterval" readOnly="true">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">商品佣金：</label>
						<div class="col-xs-3">
							<div class="input-group">
								<input type="number" class="form-control" placeholder=""
									id="productConcession"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">收费类型：</label>
						<div class="col-xs-8">
							<label class="radio-inline m-r20 pull-left"><input
								type="radio" name="payType" id="type11" value="0"
								checked="checked" onclick="checkpayType(0);"> 免费</label> <label
								class="radio-inline m-r20 pull-left"><input type="radio"
								name="payType" id="type12" value="1" onclick="checkpayType(1);">
								收费</label>
						</div>
					</div>
					<div class="form-group" id="jg">
						<label for="" class="col-xs-2 control-label">价格：</label>
						<div class="col-xs-3">
							<div class="input-group">
								<input type="number" class="form-control" placeholder="输入价格"
									id="price"> <span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">上传图片：</label> <input
							type="hidden" name="picturePath" id="picturePath" value=""
							placeholder="多媒体路径" title="多媒体路径" readonly="readonly" />
						<div class="col-xs-8">
							<ul class="upload_img w1">
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
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">节目描述：</label>
						<div class="col-xs-7">
							<textarea name="" class="form-control" rows="5"
								placeholder="最多输入2000个字符" id="descr" maxlength="2000"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-2 col-xs-7">
							<div class="checkbox">
								<label><input type="checkbox" id="xieyi" checked> <a
									href="javascript:;" id="checkbox1">我已阅读并同意版权方直播合作协议</a></label>
							</div>
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
<input type="hidden" id="host_image" value="${host_image }"/>
<script src="/static/xdomain/xdomain.min.js" slave="http://image.kakasure.cn/proxy.html"></script>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
<link
	href="/static/js/plugins/clockpicker/bootstrap-clockpicker.min.css"
	rel="stylesheet">
<script src="/static/js/plugins/laydate/laydate.js"></script>
<script src="/static/js/plugins/layer/layer.js"></script>
<script src="/static/js/plugins/clockpicker/bootstrap-clockpicker.min.js"></script>

<script type="text/javascript"
	src="/static/js/live/livebroadcast.insert.js"></script>
	

<script>
var start ={
				elem : '#dateStartTime',
				format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
				min : laydate.now(), //设定最小日期为当前日期
				max : '2099-06-16', //最大日期
				istime : true,
				istoday : true,
				choose : function(datas) { //选择日期完毕的回调
					end.min = datas; //开始日选好后，重置结束日的最小日期
		            end.start = datas;
				}
			};
var end = {
				elem : "#dateEndTime",
				format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
				min : laydate.now(),
				max : '2099-06-16',
				istime : true,
				istoday : true,
				choose : function(datas) { //选择日期完毕的回调
					start.max = datas;
				}
			};
laydate(start);
laydate(end);
</script>
</html>