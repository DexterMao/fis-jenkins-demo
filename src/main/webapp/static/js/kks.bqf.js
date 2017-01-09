if (typeof (JSON) == 'undefined') {
	//如果浏览器不支持JSON，则载入git-json.js
	$.getScript('/static/js/git-json.js');
}

/**
 * 验证是否是数字（整数）
 * @returns {boolean}
 */
function validateNumberZ(numberString) {
	var reg = new RegExp("^[0-9]*$");
	if (!reg.test(numberString)) {
		return false;
	}
	return true;
}

/**
 * 验证是否是数字(最多2位小数)
 * @returns {boolean}
 */
function validateNumber(numberString) {
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]{1,2})?$");
	if (!reg.test(numberString)) {
		return false;
	}
	return true;
}

function validateNumber0(numberString) {
	if (numberString == 0 || numberString == '0')
		return true;
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]{1,2})?$");
	if (!reg.test(numberString)) {
		return false;
	}
	return true;
}

/**
 * 显示2位小数，如果小于0.01则显示4位小数
 * @param num
 * @returns {string|*}
 */
var kakaFixed = function(num) {
	if (typeof num != 'number') {
		try {
			num = new Number(num);
		} catch (e) {
			console.error(num + ' is not number,kakaFixed');
			return;
		}
	}

	if (!num) {
		num = 0;
	}
	return new Number(num.toFixed(6)).valueOf();
};

/**
 * 判断是否不为空
 * @param obj
 * @returns {boolean}
 */
function isNotNull(obj) {
	if (obj && obj != undefined && obj != 'undefined' && obj != '') {
		return true;
	}
	return false;
}

function showNumber(number, color) {
	if (number || number == 0) {
		if (color) {
			return '<font color="' + color + '">' + number + '</font>';
		} else {
			return number;
		}
	}
	return '';
}

function back(callback) {
	var index = parent.layer.getFrameIndex(window.name);
	if (index && parent.layer) {
		parent.layer.close(index);
		if (callback) {
			callback();
		}
	} else {
		if (history && history.length > 1) {
			history.back();
		} else {
			window.opener = null;
			window.open('', '_self');
			window.close();
		}
	}
}

function goMenus(url) {
	var index = parent.layer.getFrameIndex(window.name);
	if (index && parent.layer) {
		parent.window.location = url + '?rt=' + new Date().getTime();
	} else {
		window.location = url + '?rt=' + new Date().getTime();
	}
}

function showErrorMessage(data, callback) {
	if (data && 200401 == data.code) {
		var index = parent.layer.getFrameIndex(window.name);
        if(index && parent.layer){
            parent.window.location = parent.window.location;
        } else {
            window.location = window.location;
        }
	} else {
		if (data.msg) {
			layer.alert(data.msg ? data.msg : '系统繁忙，请稍后重试', {
//				skin : 'layui-layer-molv' //样式类名
//				,
				closeBtn : 0
			});
		}
	}
}

function showSystemErrorMessage() {
	layer.alert('系统繁忙，请稍后重试');
}

/**
 * ajax请求
 *
 * response的格式：{code:200000,data:'返回的数据',msg:'提示信息'}
 *
 * @param url 地址
 * @param params 参数，json格式
 * @param callback
 * @param type 请求方式：get/post，默认post
 * @param async 是否异步：true/false，默认是异步的
 * @param isLoading 是否显示loading，默认显示
 * @param finallyFunction 最后执行的操作，肯定会执行
 */
function kakaAjax(url, params, callback, type, async, isLoading, finallyFunction){
    if(!url){
        layer.alert('链接地址异常，请稍后重试');
        return;
    }
    if(!params){
        params = {};
    }
    params.randomT = new Date().getTime();
    if(!type){
        type = "post";
    }
    if(!async){
        async = true;
    }
    var loadLayer;
    var isShow = typeof isLoading == 'undefined' || null == isLoading || isLoading;
    if(isShow){
    	loadLayer = layer.load(2, {time: 60*1000});
    }
    $.ajax({
        type : type,
        url : url,
        async : async,
        data : params,
        timeout : 20000,
        dataType : "json",
        success : function(response) {
            if(typeof finallyFunction == 'function'){
            	finallyFunction(response ? response.code : 200444);
            }
        	if(isShow){
        		layer.close(loadLayer);
            }
            if(200000 == response.code){
                if(response.msg){
                    layer.msg(response.msg);
                    setTimeout(function(){
                    	callback(response.data);
                    }, 1500);
                } else {
                	callback(response.data);
                }
            } else {
            	showErrorMessage(response,function(){
                    callback(response.data)
                });
            }
        },
        error : function(req, textStatus, error) {
            if(typeof finallyFunction == 'function'){
            	finallyFunction(200444);
            }
            try{
                console.error(error && error.message?error.message:error);
            } catch (e){}
        }
    });
}

function userLogout() {
	layer.confirm('您确定要退出登录吗？', function() {
		var url = '/logout';
		var index = parent.layer.getFrameIndex(window.name);
		if (index && parent.layer) {
			parent.window.location = url;
		} else {
			window.location = url;
		}
	})
}

function loadUrl(divId, url, params) {
	$('#' + divId).load(url, params, function() {
	});
}

function getUrlQueryString(key) {
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return '';
}

/**
 * 初始化菜单
 * @param menus
 */
function initUserMenus(menus) {
	if (!menus)
		return;

	var $menus_ul = $(".bigbanner_right");

	for (var i = 0; i < menus.length; i++) {
		var liHtml = '<li>';
		liHtml += '<a href="' + menus[i].url + '" title="' + menus[i].name
				+ '">';
		liHtml += '<img flag="menu" src="' + menus[i].image + '"/>';
		liHtml += '</a>';

		if (menus[i].children && menus[i].children.length) {
			liHtml += '<ul>';
			for (var j = 0; j < menus[i].children.length; j++) {
				liHtml += '<li><a href="' + menus[i].children[j].url + '">'
						+ menus[i].children[j].name + '</a></li>';
			}
			liHtml += '</ul>';
		}

		liHtml += '</li>';
		$menus_ul.append(liHtml);
	}
	var defaultIndex = getMenuIndex();
	$menus_ul.kaka_bigmenu({
		selectindex : defaultIndex
	});//必须有kaka_bigmenu.js
}

/**
 * 获取默认选中菜单序号
 * @returns {*}
 */
function getMenuIndex() {
	var js = document.getElementsByTagName("script");
	for (var i = 0; i < js.length; i++) {
		if (js[i].src.indexOf("common.js") >= 0) {
			var arraytemp = new Array();
			arraytemp = js[i].src.split('?');
			if (arraytemp.length >= 2) {
				arraytemp = arraytemp[1].split('=');
				if ('i' == arraytemp[0]) {
					return arraytemp[1];
				}
				return 0;
			}
		}
	}
}

/**
 * 验证电话（包括手机号和座机）
 * @param str
 * @returns {*}
 */
var checkPhones = function(str) {
	return checkMobile(str) || checkPhone(str);
};

/**
 * 验证手机号
 * @param str
 * @returns {*}
 */
var checkMobile = function(str) {
	var is = false;
	var re = /^1\d{10}$/;
	if (re.test(str)) {
		is = true;
	} else {
		is = false;
	}
	return is;
};

/**
 * 验证座机电话
 * @param str
 * @returns {*}
 */
var checkPhone = function(str) {
	var is = false;
	var re = /^0\d{2,3}-?\d{7,8}$/;
	if (re.test(str)) {
		is = true;
	} else {
		is = false;
	}
	return is;
};

/**
 * 验证邮箱
 * @param str
 * @returns {*}
 */
var checkEmail = function(str) {
	var is = false;
	var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	if (re.test(str)) {
		is = true;
	} else {
		is = false;
	}
	return is;
};

/**
 * 验证邮编
 * @param str
 * @returns {boolean}
 */
var checkPost = function(str) {
	var is = false;
	var re = /^[1-9]\d{5}$/;
	if (re.test(str)) {
		is = true;
	} else {
		is = false;
	}
	return is;
};

function getTimeStrBytimes(times) {
	var html = '';
	if (times) {
		if (times > 60) {
			html += parseInt(times / 60) + '时'
					+ (times % 60 ? times % 60 + '分' : '');
		} else {
			html += times + '分';
		}
	}
	return html;
}

//日期格式转换
function getFormatDateByLong(l, pattern) {
	return getFormatDate(new Date(l-0), pattern);
};
function getFormatDate(date, pattern) {
	if (date == undefined) {
		date = new Date();
	}
	if (pattern == undefined) {
		pattern = "yyyy-MM-dd";
	}
	return date.format(pattern);
};
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

function changeCode(obj) {
	var $dist;
	if(obj){
		$dist = $(obj);
	} else {
		$dist = $('#codeImg');
	}
	$dist.attr("src", "/index/yzm?t=" + new Date().getTime());
}