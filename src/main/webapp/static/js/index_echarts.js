
var myChart1 = echarts.init(document.getElementById('ec1'));
var option1 = {
    title: {
        show:false,
        text: '本月成交金额趋势图',
        subtext:'（金额/日期）',
        textStyle: {
            color: '#333',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 13,
        },
        left:'center'
    },
    backgroundColor: '#fff',
    tooltip: {
        trigger:'axis',
        axisPointer: {lineStyle:{width:'0'}},
        position:'top',
        backgroundColor:'#f4c201',
        extraCssText: 'border-radius: 3px;',
        //formatter: '{c0}笔'
        formatter:function(params){
       		var tar = params[0].value;
       		if(typeof(tar)=='undefined'){
       			tar = '0';	
       		}
       		return '￥'+tar;
        }
        	
    },
    xAxis: {
        nameLocation:'start',
        nameGap:'30',
        boundaryGap: false,
        axisLine:{show:false},
        axisTick:{show:false},
        splitLine:{lineStyle:{color:'#e8e8e8'}},
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        axisLine:{show:false},
        axisTick:{show:false},
        splitLine:{lineStyle:{color:'#e8e8e8'}},
    },
    series: [{
        type: 'line',
        smooth: true,
        itemStyle:{normal:{color:'#d6af10',borderWidth:3},emphasis:{color:'#d6af10',shadowColor: 'rgba(244, 194, 1, 0.6)',shadowBlur: 2}},
        lineStyle:{normal: {color: '#f4c201'}},
        areaStyle: {normal: {color: "rgba(244, 194, 1, 0.3)"}},
        data: [20, 33, 14, 44, 50, 90, 75, 67, 56, 86, 94, 67, 65, 48, 32, 76, 49, 48, 56, 34, 15, 75, 64, 48, 21, 28, 37, 82, 18, 67]
    }]
};
//myChart1.setOption(option1);



var myChart2 = echarts.init(document.getElementById('ec2'));
var option2 = {
    title: {
        show:false,
        text: '本月成交订单数趋势图',
        subtext:'（订单数/日期）',
        textStyle: {
            color: '#333',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 13,
        },
        left:'center'
    },
    backgroundColor: '#fff',
    tooltip: {
        trigger:'axis',
        axisPointer: {lineStyle:{width:'0'}},
        position:'top',
        backgroundColor:'#38c382',
        extraCssText: 'border-radius: 3px;',
        //formatter: '{c0}笔'
        formatter:function(params){
       		var tar = params[0].value;
       		if(typeof(tar)=='undefined'){
       			tar = '0';	
       		}
       		return tar+'笔';
        }
    },
    xAxis: {
        nameLocation:'start',
        nameGap:'30',
        boundaryGap: false,
        axisLine:{show:false},
        axisTick:{show:false},
        splitLine:{lineStyle:{color:'#e8e8e8'}},
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        axisLine:{show:false},
        axisTick:{show:false},
        splitLine:{lineStyle:{color:'#e8e8e8'}},
    },
    series: [{
        type: 'line',
        smooth: true,
        itemStyle:{normal:{color:'#349467',borderWidth:3},emphasis:{color:'#38c382',shadowColor: 'rgba(56, 295, 130, 0.6)',shadowBlur: 2}},
        lineStyle:{normal: {color: '#38c382'}},
        areaStyle: {normal: {color: "rgba(56, 295, 130, 0.3)"}},
        data: [20, 33, 14, 44, 50, 90, 75, 67, 56, 86, 94, 67, 65, 48, 32, 76, 49, 48, 56, 34, 15, 75, 64, 48, 21, 28, 37, 82, 18, 67]
    }]
};
//myChart2.setOption(option2);
kakaAjax('stat/vendorMonthStat.json',null,function(data) {
	option1.xAxis.data=data.xAxis;
	option1.series[0].data=data.seriesMonthAmts;
	myChart1.setOption(option1);
	option2.xAxis.data=data.xAxis;
	option2.series[0].data=data.seriesMonthOrders;
	myChart2.setOption(option2);
},'GET');
