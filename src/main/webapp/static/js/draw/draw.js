$(function (){
	$('#amount').val('');
	$('#alifee2').html(0);
	$('#description').val('');
	drawType(1);
});
function closeIframe() {
	var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	parent.layer.close(index); // 再执行关闭
}
function parentIframe() {
	var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	var amount = $("#amount").val();
	var description = $("#description").val();
	var alifee = $("#alifee").val();
	var canpay = $("#canpay").val();
	var paytype =  $('input[name="one"]:checked ').val();
	if(!validateNumber(amount)){
		layer.msg('提现金额输入有误' ,{
			time : 1500
		});
		return false;
	}
	canpay -= 0;
	amount -= 0;
	if( amount > canpay){
		layer.msg('提现金额必须小于等于可提现金额', {
			time : 500
		});
		return false;
	}
	if( amount <= 0){
		layer.msg('可提现金额应大于零', {
			time : 1500
		});
		return false;
	}
	kakaAjax('/draw/save.json', {
		amount : amount,
		remarks : description,
		poundage :alifee,
		drawType :paytype
	}, function(data) {
		parent.window.location.reload();
		parent.layer.close(index); // 再执行关闭
	});
	
}
function drawType(type){
	if(type == "1"){
		$('#canpay1').hide();
		$('#alipay').show();
		$('#amount').val('');
		$('#alifee2').html(0);
		$('#description').val('');
	}else{
		$('#alipay').hide();
		$('#canpay1').show();
		$('#amount').val('');
		$('#alifee2').html(0);
		$('#description').val('');
	}
}

function alifee(){
	var paytype =  $('input[name="one"]:checked ').val();
	var amount = $("#amount").val();
	var forCompany = $("#forCompany").val();
	var banklocation = $("#banklocation").val();
	var banktype = $("#banktype").val();
	var canpay = $('#canpay').val();
	if(!validateNumber(amount)){
		layer.msg('提现金额输入有误' ,{
			time : 1500
		});
		return false;
	}
	canpay -= 0;
	amount -= 0;
	if(canpay < amount){
		layer.msg('最大可提现金额为'+canpay, {
			time : 1500
		});
		return false;
	}
	if( amount <= 0){
		layer.msg('可提现金额应大于零', {
			time : 1500
		});
		return false;
	}
	var alifee2 = 0;
	if(paytype == "00"){
		if(amount <= 200){
			alifee2 = 1;	
		}else if(200 <amount && amount<5000){
			alifee2 = amount * 0.005;
		}else{
			alifee2 = 25;
		}
		$('#alifee').val(alifee2);
		$('#alifee2').html(alifee2.toFixed(3));
	}
	if(paytype == "01"){
		if(forCompany == "y"){
			if(banktype == "y"){
				if(banklocation == "y"){
					alifee2 = 1;
				}else{
					alifee2 = 1.2;
				}
			}else{
				alifee2 = 0.5;
				if(amount <= 10000){
					alifee2 = alifee2 +5;
				}else if(10000<amount && amount<=100000){
					alifee2 = alifee2 +10;
				}else if(100000<amount && amount <=500000){
					alifee2 = alifee2 +15;
				}else if(500000<amount && amount <=1000000){
					alifee2 = alifee2 +20;
				}else{
					alifee2 = alifee2 + amount *0.0002;
					if(alifee2 > 200){
						alifee2 = 200;
					}
				}
			}
		}else{
			if(banklocation == "y"){
				alifee2 =0.7;
			}else{
				if(banktype == "y"){
					alifee2 =1.2;
				}else{
					alifee2 =2;
				}
			}
		}
		$('#alifee').val(alifee2);
		$('#alifee2').html(alifee2.toFixed(3));
	}
	if(!isNotNull(amount)){
		$('#alifee').val(0);
		$('#alifee2').html(0);
	}
}
