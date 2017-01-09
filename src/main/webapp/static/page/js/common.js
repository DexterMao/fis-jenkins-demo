$(document).ready(function(e) {
	
    //<!-- 返回顶部 -->
    var lastRmenuStatus=false;
    $(window).scroll(function(){
        var _top=$(window).scrollTop();
        if(_top>150){
            $(".go_top").data("expanded",true);
        }else{
            $(".go_top").data("expanded",false);
        }
        if($(".go_top").data("expanded")!=lastRmenuStatus){
            lastRmenuStatus=$(".go_top").data("expanded");
            if(lastRmenuStatus){
                $(".go_top").slideDown();
            }else{
                $(".go_top").slideUp();
            }
        }
    });
    $(".go_top a").click(function(){
        $("html,body").animate({scrollTop: 0}, 600);
        return false;
    });

    //btn-group滑过
    $(document).on("mouseover mouseout",".btn-group",function(){
        if(event.type == "mouseover"){
            $(this).addClass("open");
        }else if(event.type == "mouseout"){
            $(this).removeClass("open");
        }
    })

    $(document).on("mouseover mouseout",".header1 .h1_nav li.dropdown",function(){
        if(event.type == "mouseover"){
            $(this).addClass("open");
        }else if(event.type == "mouseout"){
            $(this).removeClass("open");
        }
    })  
    
    //日期
    $(".layer-date").click(function() {
        laydate({
            format: 'YYYY-MM-DD',
            choose: function(dates){ //选择好日期的回调
            }
        })
    });

    var url = window.location.pathname.split('?')[0];
	if(url == '/copyrightmulti/goCopyrightList'){
		$('#nav_index').addClass("active");
	}else if(url == '/copyrightmulti/goUpdateMulti'){
		$('#nav_index').addClass("active");
	}else if(url == '/copyrightmulti/goCopyrightiLock'){
		$('#nav_index').addClass("active");
	}else if(url == '/copyrightmulti/goUploadMulti'){
		$('#nav_upload_video').addClass('active');
	}else if(url == '/liveBroadcast/golivelist'){
		$('#nav_program').addClass('active');
	}else if(url == '/liveBroadcast/getLiveById'){
		$('#nav_program').addClass('active');
	}else if(url == '/liveBroadcast/toChatList'){
		$('#nav_program').addClass('active');
	}else if(url == '/liveBroadcast/goAddLive'){
		$('#nav_add_program').addClass('active');
	}else if(url == '/order/goProductOrderlist'){
		$('#nav_commodity_income').addClass('active');
	}else if(url == '/order/goMediaOrderlist'){
		$('#nav_program_income').addClass('active');
	}else if(url == '/order/goGiftOrderlist'){
		$('#nav_gift_income').addClass('active');
	}else if(url == '/bqfCon/goBqfDetail'){
		$('#nav_copyright_party').addClass('active');
	}else if(url == '/draw/goDrawList'){
		$('#nav_cash_management').addClass('active');
	}
	
	//左侧二级导航
	$(".n_l_nav .nav1 a").each(function() {		//判断是否有二级增加标签
		if($(this).next("ul").length){
			$(this).append('<i class="iconfont c_o">&#xe605;</i>')
		}
		if($(this).next("ul").length  == 0){	//无二级栏目的一级栏目鼠标移上去效果
			$(this).hover(function(){
				$(this).addClass("hover");
			},function(){
				$(".n_l_nav li a").removeClass("hover");
			})
		}
		if($(this).hasClass("active")){
			$(this).parent().parent().show().parent().addClass("cur");
		}
	});
	$(".n_l_nav ul li").hover(function(){	//二级栏目鼠标移上去效果
		$(this).children("a").addClass("hover");
	},function(){
		$(this).children("a").removeClass("hover");
	})
	$(".n_l_nav>li>a").click(function(){		//有二级栏目的一级栏目鼠标移上去效果
		if($(this).next("ul").length  == 1){
			$(".n_l_nav li ul").slideUp();$(".n_l_nav>li").removeClass("cur")
			if($(this).next().is(":hidden")){$(this).parent().addClass("cur").end().next("ul").slideDown();return false;}
			else{$(this).parent().removeClass("cur").end().next("ul").slideUp();return false;}
		}
	})

	//获取页面显示区域的高度并设置
    $(window).resize();
});


//获取页面显示区域的高度并设置
$(window).resize(function() {
    $(".kks_main").css("min-height",$(window).height()-205)
});
