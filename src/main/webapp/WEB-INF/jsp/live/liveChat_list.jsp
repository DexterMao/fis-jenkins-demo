<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="kks_main">
	<jsp:include page="../left.jsp"></jsp:include>
	<div class="m_right">

		<h3 class="m_r_title">节目管理</h3>

		<ul class="nav nav-tabs m_r_tab">
			<li class="active"><a href="javascript:;">聊天记录</a></li>
		</ul>
		<div class="clearfix"></div>
		<input type="hidden" id="webcastId" value="${webcastId}">
        <div class="kks_content">
			
			 <div class="s_chat">
		        <ul>
		            <!-- <li><span class="c0">【码商名称】</span><span class="c1">小白不白</span>看着好好吃！</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">丫丫</span><span class="c2">送10支[红玫瑰]</span></li>
		            <li><span class="c0">【码商名称】</span><span class="c1">阿依腊</span>超带感的！</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">我就是小黄鱼</span>小时候的回忆小时候的回忆小时候的回忆小时候的回忆小时候的回忆小时候的回忆小时候的回忆小时候的回忆</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">丫丫</span><span class="c2">送1个[棒棒糖]</span></li>
		            <li><span class="c0">【码商名称】</span><span class="c1">小白不白</span>看着好好吃！</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">丫丫</span><span class="c2">送10支[红玫瑰]</span></li>
		            <li><span class="c0">【码商名称】</span><span class="c1">阿依腊</span>超带感的！</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">我就是小黄鱼</span>小时候的回忆</li>
		            <li><span class="c0">【码商名称】</span><span class="c1">丫丫</span><span class="c2">送1个[棒棒糖]</span></li> -->
		        </ul>
		    </div>
		</div>

	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		setInterval(function(){
			console.log($("#webcastId").val())
			kakaAjax('/liveBroadcast/getChats.json',{webcastId:encodeURIComponent($("#webcastId").val())},function(data) {
				if(data != null && data.length > 0){
					for(var i=0;i < data.length;i++){
						if(data[i].type == 1){
							$(".s_chat ul").append('<li><span class="c1">'+data[i].username+'</span>'+data[i].content+'</li>')
						}else{
							$(".s_chat ul").append('<li><span class="c1">'+data[i].username+'</span><span class="c2">'+data[i].content+'</span></li>')
						}
						chat_refresh();
					}
				}
			},'POST')}
		,2000);
		
	});
    function chat_refresh(){
        var chat_num = $(".s_chat ul li").length;
        if(chat_num > 20){$(".s_chat ul li").eq(chat_num-20).prevAll().remove();}
    }
</script>

</body>
</html>