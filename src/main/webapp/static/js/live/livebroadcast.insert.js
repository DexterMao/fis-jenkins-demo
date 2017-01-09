$(function() {
	checkpayType('0');
	// 上传图片
	$(".upload_img").on(
			"click",
			"li:not('.ico_del')",
			function() {
				layer.open({
					type : 2,
					title : '上传图片',
					shadeClose : true,
					area : [ '820px', '505px' ],
					content : '/liveBroadcast/cropperImg?imgUrl='
							+ $('#picturePath').val()
				});
			})
	$(".upload_img").on("click", ".ico_del", function(event) {
		$(this).parent().parent().parent().remove();
		$(".upload_img li").removeClass("hidden");
		event.stopPropagation();
	})
	
	// 同意协议
	$('#checkbox1').click(function() {
		layer.open({
			type : 2,
			title : '版权方直播合作协议',
			shadeClose : true,
			area : [ '820px', '80%' ],
			content : '/liveBroadcast/goxieyi'
		});
	})

	// 选择时间
	$(".layer-time").clockpicker();
});

function showImage(image) {
	$("#img_add").addClass("hidden");
	$("#img_list").remove();
	$(".upload_img")
			.append(
					'<li id="img_list"><a href="javascript:;" class="add_pic"><input type="text" name="img_upload" class="img_validate" value="'
							+ image.url
							+ '"><img src="'
							+ image.url
							+ '" width="200" height="112" class="show"><span class="show">修改图片<i class="iconfont ico_del">&#xe609;</i></span></a></li>')
	$('#picturePath').val(image.url)
}
function checkpayType(payType) {
	if (payType == '0') {
		$('#jg').hide();
	} else {
		$('#jg').show();
	}
}

function sava() {
	var name = $('#name').val();
	if (!isNotNull(name)) {
		layer.msg('请输入节目名称', {
			time : 1500
		})
		return false;
	}
	var dateStartTime = $('#dateStartTime').val();
	if (!isNotNull(dateStartTime)) {
		layer.msg('节目开始时间不能为空', {
			time : 1500
		})
		return false;
	}
	var dateEndTime = $('#dateEndTime').val();
	if (!isNotNull(dateEndTime)) {
		layer.msg('节目结束时间不能为空', {
			time : 1500
		})
		return false;
	}

	var dateStartInterval = $('#dateStartInterval').val();
	if (!isNotNull(dateStartInterval)) {
		layer.msg('节目开始时间段不能为空', {
			time : 1500
		})
		return false;
	}
	var dateEndInterval = $('#dateEndInterval').val();
	if (!isNotNull(dateEndInterval)) {
		layer.msg('节目结束时间段不能为空', {
			time : 1500
		})
		return false;
	}
	
	if (dateStartInterval >= dateEndInterval){
		layer.msg('节目时段不正确！', {
			time : 1500
		})
		return false;
	}
	
	//var dt = new Date(dateStartTime.replace(/-/,"/"));
	var dt = new Date(dateStartTime.replace("-", "/").replace("-", "/"));
	var houArr = dateStartInterval.split(":");
	dt.setHours(houArr[0], houArr[1],0);
	var now = new Date().getTime();
	if(now >= dt.getTime()){
		layer.msg('节目开始时间必须大于当前时间！', {
			time : 1500
		})
		return false;
	}

	var productConcession = $('#productConcession').val();
	if (!isNotNull(productConcession)||!validateNumber(productConcession)) {
		layer.msg('请输入正确商品佣金', {
			time : 1500
		})
		return false;
	}
	
	if(productConcession < 0){
		layer.msg('佣金不能为负数', {
			time : 1500
		})
		return false;
	}
	
	if (productConcession > 100) {
		layer.msg('商品佣金不能大于100', {
			time : 1500
		})
		return false;
	}

	var payType = $('input[name="payType"]:checked').val();
	//var channelType = $('input[name="channelType"]:checked').val();
	var price = $('#price').val();
	if (payType == '0') {

	} else {
		if (!isNotNull(price) || price == '0') {
			layer.msg('请输入视频价格', {
				time : 1500
			})
			return;
		}
		
		if (!validateNumber0(price) || price < 0) {
			layer.msg('视频价格输入有误', {
				time : 1500
			})
			return;
		}
	}

	var picturePath = $('#picturePath').val();
	if (!isNotNull(picturePath)) {
		layer.msg('请上传图片', {
			time : 1500
		})
		return false;
	}
	var descr = $('#descr').val();
	if ($('#xieyi').prop("checked")) {
	} else {
		layer.msg('请勾选节目开通协议', {
			time : 1500
		})
		return false;
	}
	var params = {
		name : name,
		channelType:'02',//增加渠道
		dateStartTimeStr : dateStartTime +' '+dateStartInterval+':00',
		dateEndTimeStr : dateEndTime+' '+dateEndInterval+':00',
		dateStartIntervalStr : dateStartInterval,
		dateEndIntervalStr : dateEndInterval,
		payType : payType,
		productConcession : productConcession,
		price : price,
		picturePath : picturePath,
		descr : descr
	};
	kakaAjax('/liveBroadcast/saveLive.json', params, function(data) {
		window.location.href = '/liveBroadcast/golivelist';
	},'POST');
}