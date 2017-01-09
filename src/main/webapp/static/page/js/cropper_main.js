$(function () {

	'use strict';
	
	var console = window.console || { log: function () {} };
	var $image = $('#image');
	var $dataX = $('#dataX');
	var $dataY = $('#dataY');
	var $dataHeight = $('#dataHeight');
	var $dataWidth = $('#dataWidth');
	var $dataRotate = $('#dataRotate');
	var $dataScaleX = $('#dataScaleX');
	var $dataScaleY = $('#dataScaleY');
	var options = {
		aspectRatio: 1.22 / 1,
		preview: '.img-preview',
		crop: function (e) {
			$dataX.val(Math.round(e.x));
			$dataY.val(Math.round(e.y));
			$dataHeight.val(Math.round(e.height));
			$dataWidth.val(Math.round(e.width));
			$dataRotate.val(e.rotate);
			$dataScaleX.val(e.scaleX);
			$dataScaleY.val(e.scaleY);
		}
	};

	// Cropper
	$image.on({
		'build.cropper': function (e) {
			console.log(e.type);
		},
		'built.cropper': function (e) {
			console.log(e.type);
		},
		'cropstart.cropper': function (e) {
			console.log(e.type, e.action);
		},
		'cropmove.cropper': function (e) {
			console.log(e.type, e.action);
		},
		'cropend.cropper': function (e) {
			console.log(e.type, e.action);
		},
		'crop.cropper': function (e) {
			console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
		},
		'zoom.cropper': function (e) {
			console.log(e.type, e.ratio);
		}
	}).cropper(options);

	// Import image
	var $inputImage = $('#inputImage');
	var URL = window.URL || window.webkitURL;
	var blobURL;

	if (URL) {
		$inputImage.change(function () {
			var files = this.files;
			var file;
			
			if (!$image.data('cropper')) {
				return;
			}
			
			if (files && files.length) {
				file = files[0];
				
				if (/^image\/\w+$/.test(file.type)) {
					blobURL = URL.createObjectURL(file);
					$image.one('built.cropper', function () {
						
						// Revoke when load complete
						URL.revokeObjectURL(blobURL);
					}).cropper('reset').cropper('replace', blobURL);
					$inputImage.val('');
				} else {
					window.alert('上传文件类型不正确');
				}
			}
		});
	} else {
		$inputImage.prop('disabled', true).parent().addClass('disabled');
	}
	
	//点击保存按钮
	$('#button').on('click', function () {
		var croppedCanvas;
		var roundedCanvas;
		// Crop
		croppedCanvas = $image.cropper('getCroppedCanvas');
		// Show
		//$("#result").html('<img src="' + roundedCanvas.toDataURL() + '">');
		
		if(croppedCanvas.selector == '#image'){
			layer.msg("图片不能为空！")
		}else{
			// Round
			roundedCanvas = getRoundedCanvas(croppedCanvas);

			parent.$("#img_add").addClass("hidden");
			parent.$("#img_list").remove();
			parent.$(".upload_img").append('<li id="img_list"><a href="javascript:;" class="add_pic"><input type="text" name="img_upload" class="img_validate" value="'+roundedCanvas.toDataURL()+'"><img src="'+roundedCanvas.toDataURL()+'" width="122" height="100" class="show"><span class="show">修改图片<i class="iconfont ico_del">&#xe609;</i></span></a></li>')

			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭
		}

	})

	//点击其他按钮
	// $('.avatar-btns').on('click', '[data-method]', function () {
	// 	var $this = $(this);
	// 	var data = $this.data();
	// 	var $target;
	// 	var result;
	// 	if ($image.data('cropper') && data.method) {
	// 		data = $.extend({}, data); // Clone a new one
	// 		if (typeof data.target !== 'undefined') {
	// 			$target = $(data.target);
	// 			if (typeof data.option === 'undefined') {
	// 				try {
	// 					data.option = JSON.parse($target.val());
	// 				} catch (e) {
	// 					console.log(e.message);
	// 				}
	// 			}
	// 		}
	// 		result = $image.cropper(data.method, data.option, data.secondOption);
	// 	}
	// });

});



function getRoundedCanvas(sourceCanvas) {
	var canvas = document.createElement('canvas');
	var context = canvas.getContext('2d');
	var width = 610;
	var height = 500;
	
	canvas.width = width;
	canvas.height = height;
	context.beginPath();
	context.arc(width / 2, height / 2, Math.min(width, height), 0, 2 * Math.PI);
	context.stroke();
	context.clip();
	context.drawImage(sourceCanvas, 0, 0, width, height);
	
	return canvas;
}