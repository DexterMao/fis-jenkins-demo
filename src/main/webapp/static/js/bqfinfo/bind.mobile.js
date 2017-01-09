$(function() {
	var phone = $('#phone').val();
	if(isNotNull(phone)){
		changeCode(document.getElementById('codeImg1'));
		$("#step1").addClass("show").removeClass("hidden");
		$("#step2").addClass("hidden").removeClass("show");
	}else{
		changeCode(document.getElementById('codeImg2'));
		$("#step1").addClass("hidden").removeClass("show");
		$("#step2").addClass("show").removeClass("hidden");
	}
});
function hasCode() {
	var hasCode = $('#hasCode').val();
	var phone = $('#phone').val();

	var param = {
		code : hasCode,
		phone : phone
	};
	kakaAjax('/bqfCon/hasPhone.json', param, function(data) {
		
		changeCode(document.getElementById('codeImg2'));
		
		$("#step1").addClass("hidden").removeClass("show");
		$("#step2").addClass("show").removeClass("hidden");
	});
}
function editPhone() {
	var Code = $('#Code').val();
	var phone = $('#newPhone').val();

	var param = {
		code : Code,
		phone : phone
	};
	kakaAjax('/bqfCon/editPhone.json', param, function(data) {
		parent.window.location.reload();
	});
}

function getCode(status) {
	var phone;
	var id = 'get_code' + status;
	if (status == 1) {
		phone = $('#phone').val();
	} else {
		phone = $('#newPhone').val();
	}
	
	if (!phone) {
		layer.msg('请输入手机号！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	
	var $yzm = $('#yzm' + status);
	if (!$yzm.val()) {
		layer.msg('请输入答案！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	
	var param = {
		phone : phone,
		codeStatus : 'Ephone',
		code: $yzm.val()
	};
	
	kakaAjax('/index/bqf/mobileCode.json', param, function(data) {
		layer.msg('短信已发送，请注意查收！', {
			time : 1500, // 3s后自动关闭
		})
		var time = 60;
		function timeCountDown() {
			if (time == 0) {
				clearInterval(timer);
				$("#"+id).removeClass("disabled").html('发送验证码');
				return true;
			}
			$("#"+id).addClass("disabled").html(time + 'S重新发送');
			time--;
			return false;
		}
		$("#"+id).addClass("disabled");
		timeCountDown();
		var timer = setInterval(timeCountDown, 1000);
	}, null, null, null, function(respCode){
		$yzm.val('');
		changeCode(document.getElementById('codeImg' + status));
	});
}