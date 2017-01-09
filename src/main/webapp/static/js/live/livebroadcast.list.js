$(function(){
	loadData();
});
var pageCur = 1;
function loadData(curr) {
	var name = $("#name").val();
	var payType = $("#payType").val();
	var status = $("#status").val();
	var params = {
			pageNo : curr || 1,
			pageSize : 10,
			name : name,
			payType:payType,
			status : status
		};
	kakaAjax('/liveBroadcast/getLiveBroList.json',params,function(data) {
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
	        		pageCur = obj.curr;
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
			
			htmls +='<td>'+getFormatDateByLong(datas[i].dateStartTime)+'~'+getFormatDateByLong(datas[i].dateEndTime)+'</td>';
			
			htmls +='<td>'+datas[i].dateStartIntervalStr+'~'+datas[i].dateEndIntervalStr+'</td>';
			
			if(datas[i].payType == '0'){
				htmls +='<td>免费</td>';
			}else{
				htmls +='<td>'+datas[i].price+'</td>';
			}
			htmls +='<td>'+datas[i].productConcession+'％</td>';
			
			var channelType = datas[i].channelType;
			htmls +='<td>'+getChannelName(datas[i].channelType)+'</td>';
			
			if(datas[i].status == '11'){
				htmls +='<td>未开始</td>';
			}else if(datas[i].status == '22'){
				htmls +='<td>进行中</td>';
			}else{
				htmls +='<td>已结束</td>';
			}
			htmls +='<td>';
			if( channelType != null && channelType == '02'){
				htmls +='<a href="javascript:showTecentLive(\''+datas[i].anchorPath+'\',\''+datas[i].guestQrcodeUrl+'\',\''+datas[i].liveBroadcastId+'\');" class="btn btn-link btn-see">主播入口</a>';
				htmls +='<a href="javascript:showAttendeeQrcode(\''+datas[i].attendeeQrcodeUrl+'\');" class="btn btn-link btn-see">观众入口</a>';
			}else{
				htmls +='<a href="javascript:showLive(\''+datas[i].anchorPassword+'\',\''+datas[i].anchorPath+'\',\''+datas[i].guestsCodePath+'\');" class="btn btn-link btn-see">主播入口</a>';
				htmls +='<a href="javascript:showPanelistLive(\''+datas[i].guestsPassword+'\',\''+datas[i].guestsPath+'\',\''+datas[i].guestQrcodeUrl+'\');" class="btn btn-link btn-see">嘉宾入口</a>';
			}
			htmls +='</td>';
			var webcastId = datas[i].webcastId;
			if(webcastId != null){
				htmls += '<td><a href="/liveBroadcast/toChatList?webcastId='+encodeURIComponent(webcastId)+'">查看记录</a></td>';
			}else{
				htmls += '<td>不支持</a></td>';
			}
			htmls +='<td><a href="/liveBroadcast/getLiveById?liveBroadcastId='+datas[i].liveBroadcastId+'" class="btn btn-primary">查看详情</a><p class="clearfix h-10"></p><a href="javascript:deleted(\''+datas[i].liveBroadcastId+'\');" class="btn btn-default btn-del">删除</a></td>';
			htmls += '</tr>';
		}
	} else {
		htmls += '<tr><td colspan="10">暂无数据</td></tr>';
	}
	return htmls;
}
function getChannelName(code){
	if(code == '02'){
		return '腾讯';
	}else{
		return '展视';
	}
}

function deleted(id){
	layer.confirm('确定要删除该直播吗？', {
		btn: ['确认','取消'],
		shadeClose:true
	}, function(){
		kakaAjax('/liveBroadcast/deleted.json',{liveBroadcastId:id},function(data) {
			layer.msg('该节目已删除！', {
		        time: 1500, //3s后自动关闭
		    }, function() {
		    	loadData();
		    })
		});
	}, function(){
		layer.close();
	});
}

function showLive(anchorPassword,anchorPath,guestsCodePath){
    layer.open({
        type: 1,
        title: '主播入口',
        shadeClose: true,
        area: ['500px', '400px'],
        content: '<div style="padding:20px 20px 0px;"><div class="form-horizontal m_b0"><div class="form-group"><label for="" class="col-xs-2 control-label text-right">主播口令：</label><div class="col-xs-9"><p class="form-control-static">'+anchorPassword+'</p></div></div><div class="form-group"><label for="" class="col-xs-2 control-label text-right">主播链接：</label><div class="col-xs-9"><div class="form-control-static text-center"><p class="text-left"><a href="'+anchorPath+'" target="_black">'+anchorPath+'</a></p><p class="p-t20"><img src="'+guestsCodePath+'" width="170" height="170"><br><br>主播可以通过咔咔硕买家APP扫码进行直播</p></div></div></div></div></div>'
    }); 
}

//观看入口地址
function showAttendeeQrcode(attendQrcodeUrl){
	layer.open({
        type: 1,
        title: '观看入口',
        shadeClose: true,
        area: ['500px', '450px'],
        content:'<div style="padding:20px 20px 0px;"><div class="form-horizontal m_b0"><div class="form-group text-center"><p class="p-t20"><div id="qrcodeId_attendee" style="padding:10px"></div><br><br>可用咔咔硕买家APP扫码观看直播</p></div></div></div>',
        success: function(){
	        $('#qrcodeId_attendee').qrcode({
	            render: "canvas", //table方式
	            width: 180, //宽度
	            height: 180, //高度
	            text: attendQrcodeUrl //任意内容
	        });	 
        }
    }); 
}

function showTecentLive(anchorPath, qrcodePath, liveBroadcastId){
	
	layer.open({
        type: 1,
        title: '主播入口',
        shadeClose: true,
        area: ['500px', '450px'],
       // content: '<div style="padding:20px 20px 0px;"><div class="form-horizontal m_b0"><div class="form-group"><label for="" class="col-xs-2 control-label text-right">主播链接：</label><div class="col-xs-9"><div class="form-control-static text-center"><p class="text-left" style="word-wrap: break-word;word-break: break-all;">'+anchorPath+'</p><p class="p-t20"><img src="'+qrcodePath+'" width="170" height="170"><br><br>主播可以通过咔咔硕买家APP扫码进行直播</p></div></div></div></div></div>',
        content:'<div style="padding:20px 20px 0px;"><div class="form-horizontal m_b0"><label for="" class="col-xs-2 control-label text-right">主播链接：</label><div class="col-xs-9"><div class="form-control-static text-center"><p style="word-wrap: break-word;word-break: break-all;"><a href="'+anchorPath+'" target="_black">'+anchorPath+'</a></p><p class="p-t20"><div id="qrcodeId" style="padding:10px"></div><br>主播可以通过咔咔硕买家APP扫码进行直播</p><a href="javascript:refreshQrcode(\''+liveBroadcastId+'\')" class="btn btn-primary" style="margin-top:10px;">重新生成</a></div></div></div></div></div>',
        success: function(){
	        $('#qrcodeId').qrcode({
	            render: "canvas", //table方式
	            width: 180, //宽度
	            height: 180, //高度
	            text: qrcodePath //任意内容
	        });	 
        }
    }); 
}

//刷新二维码
function refreshQrcode(id){
	kakaAjax('/liveBroadcast/refreshQrcode.json',{liveBroadcastId:id},function(data) {
		parent.layer.closeAll();
		loadData(pageCur);
		showTecentLive(data.anchorPath, data.guestQrcodeUrl, data.liveBroadcastId);
	});
}


//嘉宾入口
function showPanelistLive(guestPassword,guestPath,guestsCodePath){
    layer.open({
        type: 1,
        title: '嘉宾入口',
        shadeClose: true,
        area: ['500px', '400px'],
        content: '<div style="padding:20px 20px 0px;"><div class="form-horizontal m_b0"><div class="form-group"><label for="" class="col-xs-2 control-label text-right">嘉宾口令：</label><div class="col-xs-9"><p class="form-control-static">'+guestPassword+'</p></div></div><div class="form-group"><label for="" class="col-xs-2 control-label text-right">嘉宾链接：</label><div class="col-xs-9"><div class="form-control-static text-center"><p class="text-left"><a href="'+guestPath+'" target="_black">'+guestPath+'</a></p><p class="p-t20"><div id="qrcodeId" style="padding:20px"></div><br>嘉宾可以通过咔咔硕买家APP扫码进行直播</p></div></div></div></div></div>',
        success: function(){
	        $('#qrcodeId').qrcode({
	            render: "canvas", //table方式
	            width: 130, //宽度
	            height: 130, //高度
	            text: guestsCodePath //任意内容
	        });	 
        }
    }); 
}