$(function(){
    //绑定手机号
    $("#add_binding_tel").click(function(){
		layer.open({
		    type: 2,
		    title: '绑定手机号',
		    shadeClose: true,
		    area: ['550px', '400px'],
		    content: '/bqfCon/goBindMobile'
		}); 
	})

    //修改手机号
    $("#update_binding_tel").click(function(){
		layer.open({
		    type: 2,
		    title: '修改手机号',
		    shadeClose: true,
		    area: ['550px', '400px'],
		    content: '/bqfCon/goBindMobile'
		}); 
	})

	$("#u1 .ico_update1").click(function(){
		changeCode(document.getElementById('codeImg1'));
		$("#u1").removeClass("show").addClass("hidden")
		$("#u1x").removeClass("hidden").addClass("show")
	})
	$("#u1_cancel").click(function(){
		$("#u1").removeClass("hidden").addClass("show")
		$("#u1x").removeClass("show").addClass("hidden")
	})
	$("#u2 .ico_update1").click(function(){
		changeCode(document.getElementById('codeImg2'));
		$("#u2").removeClass("show").addClass("hidden")
		$("#u2x").removeClass("hidden").addClass("show")
	})
	$("#u2_cancel").click(function(){
		$("#u2").removeClass("hidden").addClass("show")
		$("#u2x").removeClass("show").addClass("hidden")
	})
});

function save1() {
	var code = $('#code1').val();
	var phone = $('#phone').val();
	var alipayAccount = $('#alipayAccount').val();
	var alipayname = $('#alipayname').val();
	var param = {
		alipayAccount : alipayAccount,
		alipayname : alipayname,
		code:code,
		phone:phone,
		saveType : 1
	};
	kakaAjax('/bqfCon/editUserplus.json', param, function(data) {
		$('#span_alipayaccount').html(alipayAccount);
		$('#span_alipayname').html(alipayname);
		$("#u1").removeClass("hidden").addClass("show");
		$("#u1x").removeClass("show").addClass("hidden");
		$('#code1').val('');
	});

}
function save2() {
	var code = $('#code2').val();
	var phone = $('#phone').val();
	var forcompny = $('input[name="forcompny"]:checked ').val();
	var bankType = $('input[name="bankType"]:checked ').val();
	var bankLocation = $('input[name="bankLocation"]:checked ').val();
	var bankAccount = $('#bankAccount').val();
	var bankName = $('#bankName').val();
	var bankUserName = $('#bankUserName').val();
	var param = {
		forcompny : forcompny,
		bankAccount : bankAccount,
		bankLocation : bankLocation,
		bankType : bankType,
		bankName : bankName,
		bankUserName : bankUserName,
		code:code,
		phone:phone,
		saveType : 2
	};
	kakaAjax('/bqfCon/editUserplus.json', param, function(data) {
		$('#span_bankaccount').html(bankAccount);
		$('#span_banklocation').html(bankLocation == "n"?"其他银行":"交通银行");
		$('#span_banktype').html(bankType =="n"?"其他":"上海");
		$('#span_bankname').html(bankName);
		$('#span_bankUserName').html(bankUserName);
		$('#span_forCompany').html(forcompny == "y" ? "是" : "否");
		$("#u2").removeClass("hidden").addClass("show");
		$("#u2x").removeClass("show").addClass("hidden");
		$('#code2').val('');
	});
}

function getCode(status) {
	var id = 'get_code' + status;
	
	var phone = $('#phone').val();
	if(!isNotNull(phone)){
		layer.msg('请先绑定手机号后再修改账号信息!',{
			time:1500
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
		phone: phone,
		codeStatus: 'Ephone',
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