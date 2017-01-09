$(document).ready(function(){
	loadData();
	
	//提现说明
	$("#txsm").click(function(){
		layer.open({
		    type: 2,
		    skin: 'kks_layer',
		    title: '提现说明',
		    shadeClose: true,
		    shade: 0.7,
		    area: ['750px', '80%'],
		    content: '/draw/drawexplain' //iframe的url
		});	
	})
	
	//失败原因
    $(document).on("click",".btn-fail",function(){
    	var that = this;
    	var type = $(this).attr("m_type");
    	if(type == 10){
    		type = "账号信息错误（不退还手续费）";
    	}else if(type == 11){
    		type = "银行处理失败（退还手续费）";
    	}else{
    		type = "其它原因（退还手续费）";
    	}
    	var message = $(this).attr("m_message");
    	layer.open({
            type: 1,
            title: '提现失败原因',
            area: ['500px', '240px'],
            shadeClose: true,
            content: '<form method="post" class="form-horizontal m_b5" id="add_Form" style="margin:10px 20px !important;font-size:14px;"><div class="form-group"><label for="" class="col-xs-3 control-label text-right">失败原因：</label><div class="col-xs-9"><p class="form-control-static text-red">'+type+'</p></div></div><div class="form-group"><label for="" class="col-xs-3 control-label text-right">原因说明：</label><div class="col-xs-8"><p class="form-control-static l-h-25 p-t5">'+message+'</p></div></div></form>'
        });
    })
});

function loadData(curr) {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var status = $("#status").val();
	var params = {
			pageNo : curr || 1,
			pageSize : 10,
			startTime : startTime,
			endTime : endTime,
			status : status
		};
	kakaAjax('/draw/getDrawPages.json',params,function(data) {
		// 初始化加载数据
		$('#tbody_content').html(getHtml(data.results));
		$('#totalRecord').html(data.totalRecord);
		// 显示分页
		laypage({  
	        cont: $("#div_page"), // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div
									// id="page1"></div>
	        pages: data.totalPage, // 通过后台拿到的总页数
	        curr: curr || 1, // 初始化当前页
	        jump: function(obj, first){ // 触发分页后的回调
	        	if (!first) {// 点击跳页触发函数自身，并传递当前页：obj.curr
	        		loadData(obj.curr);
				}
	        }
	    });
	});
};

function getHtml(datas){
	var htmls = "";
	if(datas != null && datas.length > 0){
		for(var i=0;i<datas.length;i++){
			htmls += '<tr>';
			htmls +='<td>'+datas[i].drawNumber+'</td>';
			htmls +='<td>'+datas[i].drawHolder+'</td>';
			htmls +='<td>'+datas[i].amount+'</td>';
			htmls +='<td>'+datas[i].poundage+'</td>';
			htmls +='<td>';
			htmls += datas[i].remarks == null?'/':datas[i].remarks;
			htmls+='</td>';
			
			htmls +='<td>'+getFormatDateByLong(datas[i].dateCreate,"yyyy.MM.dd hh:mm:ss")+'</td>';
			if(datas[i].status == '0'){
				htmls +='<td>审核中</td>';
			}else if(datas[i].status == '2'){
				htmls +='<td>提现成功</td>';
			}else if(datas[i].status == '1'){
				htmls +='<td>提现失败</br><a href="javascript:;" class="btn btn-link btn-fail" m_type="'+datas[i].type+'" m_message="'+datas[i].message+'">查看原因</a></td>';
			}else{
				htmls +='<td>银行直连处理</td>';
			}
			htmls += '</tr>';
		}
	} else {
		htmls += '<tr><td colspan="7">暂无数据</td></tr>';
	}
	return htmls;
}

//跳转申请提现
function drawshenhe() {
	var alipayaccount = $('#alipayaccount').val();
	var alipayname = $('#alipayname').val();
	var bankaccount = $('#bankaccount').val();
	var bankname = $('#bankname').val();
	var bankusername = $('#bankusername').val();
	var canpay = $('#canpay').val();
	var phone = $('#phone').val();
	if(!isNotNull(phone)){
		layer.confirm('需先绑定手机号以后才可以申请提现', {
			btn : [ '现在就去', '先不完善' ]
		}, function() {
			goAffiliateInfo();
		}, function() {

		});
	}else{
		var isSaveInfo = false;
		if(isNotNull(alipayaccount)){
			if(isNotNull(alipayname)){
				isSaveInfo = true;
			}
		}
		if(isNotNull(bankaccount)){
			if(isNotNull(bankname)){
				if(isNotNull(bankusername)){
					isSaveInfo = true;
				}
			}
		}
		if (isSaveInfo == true) {
			layer.open({
				type : 2,
				skin : 'kks_layer',
				title : '申请提现',
				shadeClose : true,
				shade : 0.7,
				area : [ '520px', '530px' ],
				content : [ '/draw/draw/balance', 'no' ]
			});
			return false;
		}
		layer.confirm('需先完善资料后才能提现', {
			btn : [ '现在就去', '先不完善' ]
		}, function() {
			// 此处跳转页面完善资料
			goAffiliateInfo();
		}, function() {

		});
	}
	
};
function goAffiliateInfo() {
	window.location.href = "/bqfCon/goBqfDetail";
}