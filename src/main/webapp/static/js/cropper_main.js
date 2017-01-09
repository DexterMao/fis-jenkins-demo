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
		aspectRatio: 16 / 9,
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
			//console.log(e.type);
		},
		'built.cropper': function (e) {
			//console.log(e.type);
		},
		'cropstart.cropper': function (e) {
			//console.log(e.type, e.action);
		},
		'cropmove.cropper': function (e) {
			//console.log(e.type, e.action);
		},
		'cropend.cropper': function (e) {
			//console.log(e.type, e.action);
		},
		'crop.cropper': function (e) {
			//console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
		},
		'zoom.cropper': function (e) {
			//console.log(e.type, e.ratio);
		}
	}).cropper(options);

	// Import image
	var $inputImage = $('#inputImage');
	var URL = window.URL || window.webkitURL;
	var blobURL;
	var file;
	var fileName;
	if (URL) {
		$inputImage.change(function () {
			var files = this.files;
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
	
	//点击保存按钮
	$('#button').on('click', function () {
		var croppedCanvas;
    	var roundedCanvas;
    	// Crop
    	croppedCanvas = $image.cropper('getCroppedCanvas');
    	// Round
    	roundedCanvas = getRoundedCanvas(croppedCanvas);
    	var dataurl = roundedCanvas.toDataURL("image/png");
    	
    	function dataURLtoBlob(dataurl) {
    	    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
    	        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    	    while(n--){
    	        u8arr[n] = bstr.charCodeAt(n);
    	    }
    	    return new Blob([u8arr], {type:mime});
    	}
    	//test:
    	var blob = dataURLtoBlob(dataurl);
    	
    	var fileName, strs;
    	if(file){
    		fileName = file.name;
    	} else {
    		try {
    			var tmp = $('#image').attr('src').split('\\/');	
    			fileName = tmp[tmp.length-1];
			} catch (e) {
				fileName = 'kkimage'+new Date().getTime();
			}
    		
    		
    	}
    	
		strs = fileName.split(".");
        strs = strs[strs.length-1];
        
        if(strs != "jpg" && strs != "jpeg" && strs != "png"){
        	layer.msg('上传格式有误！', {
				time : 1500, // 3s后自动关闭
			})
			return false;
        }
    	var formData = new FormData();
  	  	formData.append('myFile', blob, fileName);
  	  	
        var xhr = new XMLHttpRequest();
        xhr.open('post', parent.$('#host_image').val(), true);

        xhr.upload.onprogress = function(e) {
            if (e.lengthComputable) {
                var percentage = (e.loaded / e.total) * 100;
                //可以设置进度条
                //console.log(percentage + '%');
            }
        };

        xhr.onerror = function(e) {
            console.error('An error occurred while submitting the form. Maybe your file is too big');
        };
        xhr.onload = function() {
            if (xhr.status === 200) {
                var obj = JSON.parse(xhr.responseText);
                parent.showImage(obj.files[0])
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            	parent.layer.close(index);
            } else {
                console.error('Something went terribly wrong...');
            }
        };
        xhr.send(formData);
    	
  	  	
    });
} else {
    $inputImage.prop('disabled', true).parent().addClass('disabled');
  }
});



function getRoundedCanvas(sourceCanvas) {
	var canvas = document.createElement('canvas');
	var context = canvas.getContext('2d');
	var width = 800;
	var height = 450;
	
	canvas.width = width;
	canvas.height = height;
	context.beginPath();
	context.arc(width / 2, height / 2, Math.min(width, height), 0, 2 * Math.PI);
	context.stroke();
	//context.clip();
	context.drawImage(sourceCanvas, 0, 0, width, height);
	
	return canvas;
}