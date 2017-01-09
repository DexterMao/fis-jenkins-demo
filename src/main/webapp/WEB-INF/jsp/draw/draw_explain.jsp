<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>

<body class="bg-white">

<div class="kks_modal">
		<div class="l-h-25">
		<p><b>手续费说明：</b></p>
		<p><b>&nbsp;一、不退还手续费</b></p>
		<p><b>&nbsp;&nbsp;1、用户提现信息正确，提现成功</b></p>
		<p><b>&nbsp;&nbsp;2、用户提现信息不正确</b></p>
		 <p><b>&nbsp;</b></p>
		<p><b>&nbsp;二、退还手续费</b></p>
		<p><b>&nbsp;&nbsp;1、用户提现信息正确，出现不可控原因，导致银行不出账</b></p>
		<p><b>&nbsp;&nbsp;2、用户申请提现，管理平台拒绝提现申请</b></p>
		<p><b>&nbsp;&nbsp;3、因其他原因提现不成功，手续费不予扣除</b></p>
		<p><b>&nbsp;</b></p>

		<p><b>支付宝</b></p>
		<p>提现周期：1~2个工作日</p>
		<p>手续费如下：</p>
		<table class="table table-bordered kks_table">
			<thead>
				<tr>
					<th width="220">金额</th>
					<th>手续费</th>
					<th width="220">说明</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>200元以下（含）</td>
					<td>1元</td>
					<td rowspan="3">1元起，25元封顶。5‰的手续</td>
				</tr>
				<tr>
					<td>200元~5000元</td>
					<td>5‰</td>
				</tr>
				<tr>
					<td>5000以上（含）</td>
					<td>25元</td>
				</tr>
			</tbody>
		</table>
		<p class="clearfix h-10"></p>
		<p><b>银联</b></p>
		<p>提现周期：1~2个工作日</p>
		<p>手续费如下：</p>
		<p><i class="iconfont col-blue f-s16">&#xe60a;</i>对公账户</p>
		<table class="table table-bordered kks_table">
			<thead>
				<tr>
					<th width="150">交易费类型</th>
					<th width="170">手续费</th>
					<th>说明</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>上海交行</td>
					<td>1元/笔</td>
					<td></td>
				</tr>
				<tr>
					<td>上海其他银行</td>
					<td>1.2元/笔</td>
					<td></td>
				</tr>
				<tr>
					<td>其他城市交行</td>
					<td>人行电汇费+手续费</td>
					<td rowspan="2" class="text-left l-h-20">手续费0.5元/笔。电汇费如下：1万元以下（含):5.00元1万元至10万元（含）:10.00元；10万元至50万元（含）:15.00元；50万至100万元（含）:20.00元；100万元以上：0.02％，最高不超过200元/笔</td>
				</tr>
				<tr>
					<td>其他城市其他银行</td>
					<td>人行电汇费+手续费</td>
				</tr>
			</tbody>
		</table>
		<p class="clearfix h-10"></p>
		<p><i class="iconfont col-blue f-s16">&#xe60a;</i>对私账户</p>
		<table class="table table-bordered kks_table">
			<thead>
				<tr>
					<th width="150">交易费类型</th>
					<th width="170">手续费</th>
					<th>说明</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>交行</td>
					<td class="text-left l-h-20">普通0.7元/笔，加急2元/笔</td>
					<td class="text-left l-h-20"></td>
				</tr>
				<tr>
					<td>其他银行</td>
					<td class="text-left l-h-20">1、上海1.2元/笔，如为其他城市则是人行电汇费+手续费<br>2、其他城市人行电汇费2元/笔</td>
					<td class="text-left l-h-20">1、走的通道为对公转账中的他行转账，需要提供收款人开户网点<br>2、走的是超级网银系统，无需提供开户网点。单笔有最高5万元限制。</td>
				</tr>
			</tbody>
		</table>
		<p class="clearfix h-10"></p>

	</div>
		
</div>

</body>
</html>