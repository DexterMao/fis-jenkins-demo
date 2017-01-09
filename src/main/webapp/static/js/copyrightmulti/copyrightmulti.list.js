$(document).ready(function(){
	loadData();
});

function loadData(curr) {
	var mediaName = $("#mediaName").val();
	var payType = $("#payType").val();
	var type = $("#type").val();
	var auditStatus = $("#auditStatus").val();
	var params = {
			pageNo : curr || 1,
			pageSize : 10,
			mediaName : mediaName,
			type : type,
			payType:payType,
			auditStatus : auditStatus
		};
	kakaAjax('/copyrightmulti/getCopyrightmultiList.json',params,function(data) {
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
			htmls +='<td><a href="/copyrightmulti/goCopyrightiLock?copyrightmultiId='+datas[i].copyrightmultiId+'">'+datas[i].mediaName+'</a></td>';
			
			htmls +='<td>';
			htmls += datas[i].type == '01' ?'视频':'音频';
			htmls +='</td>';
			if(datas[i].payType == '0'){
				htmls +='<td>免费</td>';
			}else{
				htmls +='<td>'+datas[i].price+'</td>';
			}
			if(datas[i].payType == '0'){
				htmls +='<td>'+datas[i].productConcession+'％</td>';
			}else{
				htmls +='<td>/</td>';
			}
			htmls +='<td>'+datas[i].spreadNum+'</td>';
			htmls +='<td>'+datas[i].scanCodeNum+'</td>';
			htmls +='<td>'+datas[i].clickNum+'</td>';
			if(datas[i].auditStatus == '99'){
				htmls +='<td>待审核</td>';
			}else if(datas[i].auditStatus == '00'){
				htmls +='<td>审核通过</td>';
			}else{
				htmls +='<td>审核不通过</td>';
			}
			htmls +='<td><a href="/copyrightmulti/goUpdateMulti?copyrightmultiId='+datas[i].copyrightmultiId+'" class="btn btn-primary">编辑</a><p class="clearfix h-10"></p>'
				+'<a href="javascript:deleted(\''+datas[i].copyrightmultiId+'\');" class="btn btn-default btn-del" >删除</a></td>';
			htmls += '</tr>';
		}
	} else {
		htmls += '<tr><td colspan="9">暂无数据</td></tr>';
	}
	return htmls;
}

function deleted(id){
	layer.confirm('确定要删除该多媒体吗？', {
		btn: ['确认','取消'],
		shadeClose:true
	}, function(){
		kakaAjax('/copyrightmulti/deleteCopyrightmulti.json',{id:id},function(data) {
			layer.msg('该多媒体已删除！', {
		        time: 1500, //3s后自动关闭
		    }, function() {
		    	loadData();
		    })
		});
	}, function(){
		layer.close();
	});
}
