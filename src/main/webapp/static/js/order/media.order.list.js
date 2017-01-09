$(function(){
	loadData();
});

function loadData(curr) {
	var name = $("#name").val();
	var type = $("#type").val();
	var status = $("#status").val();
	var params = {
			pageNo : curr || 1,
			pageSize : 10,
			multiName : name,
			type:type,
			statusStr : status
		};
	kakaAjax('/order/getMediaOrderList.json',params,function(data) {
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
			htmls +='<td>';
			htmls += datas[i].multiName != null ? datas[i].multiName :'/';
			htmls+='</td>';
			
			if(datas[i].type == 1){
				htmls +='<td>多媒体</td>';
			}else{
				htmls +='<td>节目</td>';
			}
			htmls +='<td>';
			htmls+=datas[i].buyUserName != null ? datas[i].buyUserName :'/';
			htmls+='</td>';
			htmls +='<td>'+datas[i].commision+'</td>';
			htmls +='<td>'+datas[i].cashPublish+'</td>';
			htmls +='<td>'+datas[i].cashYunear+'</td>';
			htmls +='<td>'+datas[i].cashCopyright+'</td>';
			
			htmls +='<td>'+getFormatDateByLong(datas[i].dateCreate)+'</td>';
			
			htmls += '</tr>';
		}
	} else {
		htmls += '<tr><td colspan="8">暂无数据</td></tr>';
	}
	return htmls;
}