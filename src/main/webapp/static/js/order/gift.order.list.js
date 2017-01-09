$(function(){
	loadData();
});

function loadData(curr) {
	var name = $("#name").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var params = {
			pageNo : curr || 1,
			pageSize : 10,
			name : name,
			startTime:startTime,
			endTime : endTime
		};
	kakaAjax('/order/getLiveBqfGiftList.json',params,function(data) {
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
			htmls +='<td>'+datas[i].name+'</td>';
			
			htmls +='<td>';
			htmls += datas[i].giftName;
			htmls+='</td>';
			
			htmls +='<td>';
			htmls += datas[i].giftNum;
			htmls+='</td>';
			
			htmls +='<td>'+datas[i].pointCopyright+'</td>';
			
			htmls +='<td>'+datas[i].cashCopyright+'</td>';
			
			htmls +='<td>'+getFormatDateByLong(datas[i].dateCreate,"yyyy.MM.dd hh:mm:ss")+'</td>';
			
			htmls += '</tr>';
		}
	} else {
		htmls += '<tr><td colspan="6">暂无数据</td></tr>';
	}
	return htmls;
}

function intoBalance(){
	var canPay = $('#canPay').val();
	if(!isNotNull(canPay) || canPay == 0.00){
		layer.msg('没有可转入金额',{
			time:1500
		})
		return false;
	}
	kakaAjax('/order/intoBalance.json',{},function(data) {
		layer.msg('转入金额成功',{
			time:1500
		})
		$('#canPay_span').html('0.00')
	    });
}