function changePassword() {
	var password = $('#password').val();
	var passwordNew = $('#passwordNew').val();
	var passwordNew2 = $('#passwordNew2').val();
	if (!isNotNull(password)) {
		layer.msg('旧密码不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	if (!isNotNull(passwordNew)) {
		layer.msg('密码不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	if (!isNotNull(passwordNew2)) {
		layer.msg('确认密码不能为空！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	if (passwordNew != passwordNew2) {
		layer.msg('两次密码不一致！', {
			time : 1500, // 3s后自动关闭
		})
		return false;
	}
	kakaAjax('/changePassword.json', {
		password : password,
		passwordNew : passwordNew,
		passwordNew2 : passwordNew2
	}, function(data) {
		if(data == "success"){
			layer.msg('修改密码成功！', {
				time : 1500, // 3s后自动关闭
			})
			setTimeout('closes()',1600); 
		}
	});
}
function closes(){
	window.close();
}