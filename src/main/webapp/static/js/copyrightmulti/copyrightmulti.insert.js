$(function() {
	checkType('01');
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
					content : '/copyrightmulti/cropperImg?imgUrl='
							+ $('#picturepath').val()
				});
			})
	$(".upload_img").on("click", ".ico_del", function(event) {
		$('#picturepath').val("");
		$(this).parent().parent().parent().remove();
		$(".upload_img li").removeClass("hidden");
		event.stopPropagation();
	})
	// 视频上传按钮事件
	$('#video').change(
			function(e) {
				var currentfile = e.target.files
						|| e.dataTransfer.files;
				var file = currentfile[0];
				var formData = new FormData();
				formData.append('myFile', file);

				var xhr = new XMLHttpRequest();
				xhr.open('post', '/upload?uploadType=2', true);

				xhr.upload.onprogress = function(e) {
					if (e.lengthComputable) {
						$('#progressbar').parent().parent().removeClass("hidden");
						var percentage = (e.loaded / e.total) * 100;
						percentage = percentage.toFixed(1);
						$('#progressbar').html('<div class="progress-bar" role="progressbar" aria-valuenow="'+percentage+'" aria-valuemin="0" aria-valuemax="100" style="width: '+percentage+'%;">'+percentage+'%</div>');
						$('#input_progressbar').val(percentage)
						if(percentage == '100.0'){
							layer.msg('上传成功', {
								time : 1000
							},function(){
								
							})
						}
					}
				};

				xhr.onerror = function(e) {
					console
							.error('An error occurred while submitting the form. Maybe your file is too big');
				};

				xhr.onload = function() {
					if (xhr.status === 200) {
						var obj = JSON.parse(xhr.responseText);
						$('#path').val(obj.path)
					} else {
						console
								.error('Something went terribly wrong...');
					}
				};
				xhr.send(formData);
			});
	
	// 音频上传按钮事件
	$('#audio').change(
			function(e) {
				var currentfile = e.target.files
						|| e.dataTransfer.files;
				var file = currentfile[0];
				var formData = new FormData();
				formData.append('myFile', file);

				var xhr = new XMLHttpRequest();
				xhr.open('post', '/upload?uploadType=3', true);

				xhr.upload.onprogress = function(e) {
					if (e.lengthComputable) {
						$('#progressbar').parent().parent().removeClass("hidden");
						var percentage = (e.loaded / e.total) * 100;
						percentage = percentage.toFixed(1);
						$('#progressbar').html('<div class="progress-bar" role="progressbar" aria-valuenow="'+percentage+'" aria-valuemin="0" aria-valuemax="100" style="width: '+percentage+'%;">'+percentage+'%</div>');
						$('#input_progressbar').val(percentage)
						if(percentage == '100.0'){
							layer.msg('上传成功', {
								time : 1000
							},function(){
								
							})
						}
					}
				};

				xhr.onerror = function(e) {
					console
							.error('An error occurred while submitting the form. Maybe your file is too big');
				};

				xhr.onload = function() {
					if (xhr.status === 200) {
						var obj = JSON.parse(xhr.responseText);
						$('#audioPath').val(obj.path)
					} else {
						console
								.error('Something went terribly wrong...');
					}
				};
				xhr.send(formData);
			});
});
function checkType(type) {
	if (type == '01') {
		$('#type_video').show();
		$('#type_music').hide();
	} else {
		$('#type_video').hide();
		$('#type_music').show();
	}
}

function checkpayType(payType) {
	if (payType == '0') {
		$('#mf').show();
		$('#sf').hide();
		$('#cmf').hide();
		$('#sfts').hide();
	} else {
		$('#mf').hide();
		$('#sf').show();
		$('#cmf').show();
		$('#sfts').show();
	}
}
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
	$('#picturepath').val(image.url)
}

function sava() {
	var type = $('input[name="type"]:checked').val();
	var mediaName = $('#mediaName').val();
	if (!isNotNull(mediaName)) {
		layer.msg('请输入多媒体名称', {
			time : 1500
		})
		return false;
	}
	var videoCategoryId = $('#videoCategoryId').val();
	if(!isNotNull(videoCategoryId)){
		layer.msg('请选择类目', {
			time : 1500
		})
		return false;
	}
	var labelStr = $('#labelStr').val();
	if (!isNotNull(labelStr)) {
		layer.msg('请输入标签', {
			time : 1500
		})
		return false;
	}
	var keywords = $('#keywords').val();
	if (!isNotNull(keywords)) {
		layer.msg('请输入关键字', {
			time : 1500
		})
		return false;
	}

	var payType = $('input[name="payType"]:checked').val();
	var productConcession = $('#productConcession').val();
	var price = $('#price').val();
	if (payType == '0') {
		if (!isNotNull(productConcession)) {
			layer.msg('请输入商品佣金', {
				time : 1500
			})
			return;
		}
	} else {
		if (!isNotNull(price) || price == '0'||price <=0) {
			layer.msg('请输入视频价格', {
				time : 1500
			})
			return;
		} else {
			if (!validateNumber0(price)) {
				layer.msg('视频价格输入有误', {
					time : 1500
				})
				return;
			}
		}
	}

	var picturepath = $('#picturepath').val();
	var path = $('#path').val();
	var audioPath = $('#audioPath').val();
	if (!isNotNull(picturepath)) {
		layer.msg('请上传图片', {
			time : 1500
		})
		return false;
	}
	if (type == '01') {
		if (!isNotNull(path)) {
			layer.msg('请上传视频', {
				time : 1500
			})
			return false;
		}
	} else {
		if (!isNotNull(audioPath)) {
			layer.msg('请上传音频', {
				time : 1500
			})
			return false;
		}
		path = audioPath;
	}
	var input_progressbar = $('#input_progressbar').val();
	if (input_progressbar != '100.0') {
		layer.msg('请等待视频上传成功后再保存', {
			time : 1500
		})
		return false;
	}
	var descr = $('#descr').val();
	if (!isNotNull(descr)) {
		layer.msg('请填写描述', {
			time : 1500
		})
		return false;
	}
	var params = {
		type : type,
		mediaName : mediaName,
		videoCategoryId : videoCategoryId,
		labelStr : labelStr,
		keywords : keywords,
		payType : payType,
		productConcession : productConcession,
		price : price,
		picturepath : picturepath,
		path : path,
		descr : descr
	};
	kakaAjax('/copyrightmulti/saveCM.json', params, function(data) {
		window.location.href = '/copyrightmulti/goCopyrightList';
	});
}