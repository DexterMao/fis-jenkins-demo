$(function() {
	// 输入字数变化
	$("#name").keyup(function() {
		var len = $(this).val().length;
		$(".name_num").html(len);
	})
	$(".name_num").html($('#name').val().length);
	// 发送手机号
	var codeBtn = $("#get_code")[0];
	var $tel = $("input[name='tel']");
	if (codeBtn) {
		codeBtn
				.addEventListener(
						"click",
						function() {
							var telN = $tel.val();
							var ths = $(this);
							if ($.trim(telN) == "") {
								tel.focus();
								layer.msg("请输入手机号！", {
									time : 1500, // 3s后自动关闭
								});
								return;
							}
							var length = telN.length;
							var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
							if (!mobile.test(telN) || length != 11) {
								layer.msg('手机号码输入有误！', {
									time : 1500, // 3s后自动关闭
								})
								return false;
							}

							var $yzm = $('#yzm');
							if ($(this).next("div").is(":hidden")) {
								$(this).next("div").fadeIn();
								return false;
							} else {
								if (!$yzm.val()) {
									layer.msg('请输入答案！', {
										time : 1500, // 3s后自动关闭
									})
									return false;
								}
							}

							kakaAjax('/index/bqf/mobileCode.json', {
								phone : telN,
								codeStatus : 'register',
								code : $yzm.val()
							}, function(data) {
								layer.msg('短信已发送，请注意查收！', {
									time : 1500, // 3s后自动关闭
								})

								var time = 60;
								function timeCountDown() {
									if (time == 0) {
										clearInterval(timer);
										$("#get_code").removeClass("disabled")
												.html('发送验证码');
										return true;
									}
									$("#get_code").addClass("disabled").html(
											time + 'S重新发送');
									time--;
									return false;
								}
								$("#get_code").addClass("disabled");
								timeCountDown();
								var timer = setInterval(timeCountDown, 1000);
							}, null, null, null, function(respCode){
								$yzm.val('');
								changeCode();
								if(200000 == respCode || 500016 == respCode){
									$(codeBtn).next("div").fadeOut();
								}
							});
						}, false);
	}

});

function register() {
	var name = $('#name').val();
	var password = $('#password').val();
	var password1 = $('#password1').val();
	var tel = $('#tel').val();
	var code = $('#code').val();
	if (!name) {
		layer.msg('用户名不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return;
	}
	if (!password) {
		layer.msg('密码不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return;
	}
	if (password != password1) {
		layer.msg('两次密码输入有误！', {
			time : 1500, // 3s后自动关闭
		})
		return;
	}
	if (!tel) {
		layer.msg('手机号不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return;
	}
	if (!code) {
		layer.msg('验证码不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return;
	}

	kakaAjax('/register/bqf.json', {
		username : name,
		phone : tel,
		password : password,
		password2 : password1,
		code : code
	}, function(data) {
		if (data) {
			window.location.reload();
		}
	});

}
