<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="kks_main">
		<jsp:include page="../left.jsp"></jsp:include>
		<div class="m_right">
		<input type="hidden" id="phone" value="${ userplusDto.phone }" >
			<h3 class="m_r_title">个人中心</h3>

			<ul class="nav nav-tabs m_r_tab">
				<li class="active"><a href="javascript:;">版权方信息</a></li>
			</ul>
			<div class="clearfix"></div>

			<div class="kks_content">

				<div class="user_con">

					<div class="form-horizontal m_b0">
						<p class="u_c_title">
							<span>用户信息</span>
						</p>
						<div class="form-group">
							<label for="" class="col-xs-2 control-label">用户名：</label>
							<div class="col-xs-7">
								<p class="form-control-static">${userplusDto.userName }</p>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-2 control-label">手机号：</label>
							<div class="col-xs-7">
								<c:if test="${empty userplusDto.phone }">
									<p class="show">
										<a href="javascript:;" class="btn btn-default"
											id="add_binding_tel">立即绑定</a><span class="col-gray m-l20">绑定手机号可助您保障账户安全，找回密码</span>
									</p>
								</c:if>
								<c:if test="${not empty userplusDto.phone }">
									<p class="form-control-static">
										${ userplusDto.phone }<a href="javascript:;"
											class="text-blue m-l20" id="update_binding_tel">修改</a><span
											class="m-l20 col-gray">绑定手机号可助您保障账户安全，找回密码</span>
									</p>
								</c:if>
							</div>
						</div>
					</div>
					<div class="clearfix p-t20"></div>

					<div class="form-horizontal">


						<p class="u_c_title">
							<span>绑定交易账号<small class="col-gray p-l20">可选择一种交易账号进行绑定，也可二者同时绑定</small></span>
						</p>

						<div class="m_b0" id="u1">
							<p class="u_c_title">
								<span>支付宝</span><a href="javascript:;"
									class="btn btn-link ico_update1">编辑</a>
							</p>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">支付宝账号：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_alipayaccount">${userplusDto.alipayAccount }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">收款人：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_alipayname">${userplusDto.alipayname }</p>
								</div>
							</div>
						</div>

						<div class="hidden" id="u1x">

							<p class="u_c_title">
								<span>绑定支付宝</span>
							</p>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">支付宝账号：</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="alipayAccount"
										placeholder="请填写支付宝账号" value="${userplusDto.alipayAccount }">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">收款人：</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="alipayname"
										placeholder="请填写收款人" value="${userplusDto.alipayname }">
								</div>
							</div>
							<div class="form-group p-t10">
								<div class="col-xs-8 col-xs-offset-1">
									<div class="panel panel-default m-0">
										<div class="panel-heading">
											<h5 class="panel-title">账号安全验证</h5>
										</div>
										<div class="panel-body" style="padding-bottom: 0px;">
											<div class="form-group m_0">
												<label for="" class="col-xs-2 control-label"></label>
												<div class="col-xs-10">
													<img id="codeImg1" src="" style="float:left; margin-right:15px;" onclick="changeCode(this);">
                   	 								<input type="text" id="yzm1" class="form-control pull-left" style="width:110px" placeholder="请输入答案" maxlength="4">
                   	 								<a href="javascript:getCode(1);" class="btn btn-default m-l20" id="get_code1">获取短信验证码</a>
												</div>
											</div>
										</div>
										<div class="panel-body">
											<div class="form-group m-0">
												<label for="" class="col-xs-2 control-label">短信验证码：</label>
												<div class="col-xs-10">
													<input type="text" class="form-control pull-left w-150" placeholder="输入短信验证码" id="code1">
													<span class="m-l20 l-h-32 col-gray">手机号是您绑定的手机号</span> 
													<div class="w-b100 l-h-25 p-t10">
														没收到短信验证码？<br>1.网络通讯异常可能会造成短信丢失，请重新获取或稍后再试。<br>2.请核实手机是否已欠费停机，或者屏蔽了系统短信。<br>3.特殊情况（如手机丢失）无法获取验证码，
														请致电咔咔硕：400-690-0571
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-offset-2 col-xs-7">
									<button type="button" onclick="save1();" class="btn btn-primary f-s14 m-r20">保存</button>
									<a href="javascript:;" class="btn btn-default f-s14 m-r20"
										id="u1_cancel">取消</a>
								</div>
							</div>

						</div>
						<p class="clearfix h-10"></p>

						<div class="m_b0" id="u2">

							<p class="u_c_title">
								<span>银行卡</span><a href="javascript:;"
									class="btn btn-link ico_update1">编辑</a>
							</p>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">银行卡号：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_bankaccount">${userplusDto.bankAccount }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户银行：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_banklocation">${userplusDto.bankLocation == 'n' ? '其他银行':'交通银行' }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户行所在城市：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_banktype">${userplusDto.bankType == 'n' ? '其他' :'上海' }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户行：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_bankname">${userplusDto.bankName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户人：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_bankUserName">${userplusDto.bankUserName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">对公账户：</label>
								<div class="col-xs-7">
									<p class="form-control-static" id="span_forCompany">${userplusDto.forcompny == 'n' ? '否':'是' }</p>
								</div>
							</div>

						</div>
						<div class="hidden" id="u2x">

							<p class="u_c_title">
								<span>绑定银行卡</span>
							</p>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">银行卡号：</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="bankAccount"
										placeholder="" value="${userplusDto.bankAccount }">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户银行：</label>
								<div class="col-xs-7">
									<label class="radio-inline"><input type="radio"
										name="bankLocation" id="inlineRadio2" value="y"
										${userplusDto.bankLocation == 'y' ? 'checked':'' }>
										交通银行</label> <label class="radio-inline"><input type="radio"
										name="bankLocation" id="inlineRadio3" value="n"
										${userplusDto.bankLocation == 'n' ? 'checked':'' }>
										其他银行</label>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户行所在城市：</label>
								<div class="col-xs-7">
									<label class="radio-inline"><input type="radio"
										name="bankType" id="inlineRadio2" value="y"
										${userplusDto.bankType == 'y' ? 'checked':'' }> 上海</label> <label
										class="radio-inline"><input type="radio"
										name="bankType" id="inlineRadio3" value="n"
										${userplusDto.bankType == 'n' ? 'checked':'' }> 其他</label>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户行：</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="bankName"
										placeholder="" value="${userplusDto.bankName }">
										<p style="padding-top: 5px;">请务必填写开户行支行，例如<span class="col-red">农业银行杭州文三路支行</span></p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">开户人：</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="bankUserName"
										placeholder="" value="${userplusDto.bankUserName }">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-xs-2 control-label">对公账户：</label>
								<div class="col-xs-7">
									<label class="radio-inline"><input type="radio"
										name="forcompny" id="inlineRadio2" value="y"
										${userplusDto.forcompny == 'y' ? 'checked':'' }> 是</label> <label
										class="radio-inline"><input type="radio"
										name="forcompny" id="inlineRadio3" value="n"
										${userplusDto.forcompny == 'n' ? 'checked':'' }> 否</label>
								</div>
							</div>
							<div class="form-group p-t10">
								<div class="col-xs-8 col-xs-offset-1">
									<div class="panel panel-default m-0">
										<div class="panel-heading">
											<h5 class="panel-title">账号安全验证</h5>
										</div>
										<div class="panel-body" style="padding-bottom: 0px;">
											<div class="form-group m_0">
												<label for="" class="col-xs-2 control-label"></label>
												<div class="col-xs-10">
													<img id="codeImg2" src="" style="float:left; margin-right:15px;" onclick="changeCode(this);">
                   	 								<input type="text" id="yzm2" class="form-control pull-left" style="width:110px" placeholder="请输入答案" maxlength="4">
                   	 								<a href="javascript:getCode(2);" class="btn btn-default m-l20" id="get_code2">获取短信验证码</a>
												</div>
											</div>
										</div>
										<div class="panel-body">
											<div class="form-group m-0">
												<label for="" class="col-xs-2 control-label">短信验证码：</label>
												<div class="col-xs-10">
													<input type="text" class="form-control pull-left w-150" placeholder="输入短信验证码" id="code2">
													<span class="m-l20 l-h-32 col-gray">手机号是您绑定的手机号</span> 
													<div class="w-b100 l-h-25 p-t10">
														没收到短信验证码？<br>1.网络通讯异常可能会造成短信丢失，请重新获取或稍后再试。<br>2.请核实手机是否已欠费停机，或者屏蔽了系统短信。<br>3.特殊情况（如手机丢失）无法获取验证码，
														请致电咔咔硕：400-690-0571
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-offset-2 col-xs-7">
									<button type="button" class="btn btn-primary f-s14 m-r20" onclick="save2();">保存</button>
									<a href="javascript:;" class="btn btn-default f-s14 m-r20"
										id="u2_cancel">取消</a>
								</div>
							</div>

						</div>
					</div>

				</div>

			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="/static/js/bqfinfo/bqf.info.js?v=2016111818"></script>
</html>