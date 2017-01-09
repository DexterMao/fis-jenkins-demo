
package com.kakasure.bqf.util;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;

/**
 * 常量类
 */
public class Const {
	public static final String			SESSION_SECURITY_CODE			= "sessionSecCode";
	public static final String			SESSION_USER_ID					= "kks_sess_uid";
	public static final String			SESSION_USER					= "sessionUser";
	public static final String			SESSION_ROLE_RIGHTS				= "sessionRoleRights";

	public static final String			SESSION_ANNOUNCEMULTI_ID		= "sessionAnnounceMulitid";

	/**
	 * 当前菜单
	 */
	public static final String			SESSION_menuList				= "menuList";
	/**
	 * 全部菜单
	 */
	public static final String			SESSION_allmenuList				= "allmenuList";

	public static final String			SESSION_QX						= "QX";
	public static final String			SESSION_userpds					= "userpds";

	/**
	 * 用户对象
	 */
	public static final String			SESSION_USERROL					= "USERROL";
	/**
	 * 用户名
	 */
	public static final String			SESSION_USERNAME				= "USERNAME";

	public static final String			TRUE							= "T";
	public static final String			FALSE							= "F";
	/**
	 * 登录地址
	 */
	public static final String			LOGIN							= "/login_toLogin.do";

	public static final String			CONFIG_PATH						= "config/system/";

	public static final String			GO_URL							= "redirect:/";

	/**
	 * 文件上传路径
	 */
	public static final String			FILEPATH						= "uploadify/uploads/";
	/**
	 * 文件上传转码路径
	 */
	public static final String			CHANGEFILEPATH					= "constants/constants/";
	/**
	 * 二维码相对路径前缀
	 */
	public static final String			QRCODE_PATH						= "/qrcode/";
	/**
	 * 不对匹配该值的访问路径拦截（正则）
	 */
	public static final String			NO_INTERCEPTOR_PATH				= ".*/((login)|(logout)|(code)|(app)|(websocket)|(wechat/qrcode)|(api)|(main)|(mulitpay)|(index)|(pay)|(oauth)).*";
	/**
	 * 该值会在web容器启动时由WebAppContextListener初始化
	 */
	public static ApplicationContext	WEB_APP_CONTEXT					= null;

	/****************************************
	 * APP Constants
	 ************************************************/
	// app注册接口_请求协议参数)
	public static final String[]		APP_REGISTERED_PARAM_ARRAY		= new String[] {
											"countries", "uname", "passwd",
											"title", "full_name",
											"company_name", "countries_code",
											"area_code", "telephone",
											"mobile" };
	public static final String[]		APP_REGISTERED_VALUE_ARRAY		= new String[] {
											"国籍", "邮箱帐号", "密码", "称谓", "名称",
											"公司名称", "国家编号", "区号", "电话", "手机号" };

	/**
	 * app登录接口_请求协议中的参数(key)
	 */
	public static final String[]		APP_LOGIN_PARAM_ARRAY			= new String[] {
											"uname", "passwd" };
	/**
	 * app登录接口_请求协议中的参数(value)
	 */
	public static final String[]		APP_LOGIN_VALUE_ARRAY			= new String[] {
											"邮箱账号", "密码" };

	/**
	 * app登录状态接口_请求协议中的参数(key)
	 */
	public static final String[]		APP_LOGINSTATUS_PARAM_ARRAY		= new String[] {
											"app_id", "status" };
	/**
	 * app登录状态接口_请求协议中的参数(value)
	 */
	public static final String[]		APP_LOGINSTATUS_VALUE_ARRAY		= new String[] {
											"app登录用户ID", "登录状态" };

	/**
	 * 忘记密码,查找用户账户是否存在接口_请求协议中的参数(key)
	 */
	public static final String[]		APP_FORGOTPASSWORD_PARAM_ARRAY	= new String[] {
											"uname" };
	/**
	 * 忘记密码,查找用户账户是否存在接口_请求协议中的参数(value)
	 */
	public static final String[]		APP_FORGOTPASSWORD_VALUE_ARRAY	= new String[] {
											"邮箱账号" };

	/************************************************** 业务相关 *************************************************************/
	/**
	 * 奇码网角色code
	 */
	public static enum RoleCode {

		COPYRIGHT(1), // 版权方
		ANNOUNCE(2), // 发布方
		YUNEAR(3), // 奇码网平台
		CLIENT(4);// 客户端用户
		public Integer code;

		private RoleCode(Integer code) {
			this.code = code;
		}
	}

	public static String			COPYRIGHT_ID;
	public static String			ANNOUNCE_ID;
	public static String			YUNEAR_ID;
	public static String			CLIENT_ID;
	/**
	 * 奇码网分成比例
	 */
	public static final BigDecimal	ORDER_RATIO_YUNEAR		= new BigDecimal("0.2");
	/**
	 * 发布方分成比例
	 */
	public static final BigDecimal	ORDER_RATIO_PUBLISH		= new BigDecimal("0.4");
	/**
	 * 版权方分成比例
	 */
	public static final BigDecimal	ORDER_RATIO_COPYRIGHT	= new BigDecimal("0.4");

	/**
	 * 咔咔硕接口，正确的code:200000
	 */
	public static final Integer		CODE_SUCCESS			= 200000;
	/**
	 * 咔咔硕接口，系统异常的code:200444
	 */
	public static final Integer		CODE_FAIL				= 200444;

	/**
	 * 订单状态
	 */
	public static enum OrderStateCode {

		CREATE(0), // 待处理（刚创建）
		FAIL(4), // 交易失败
		SUCCESS(100);// 交易成功

		public Integer code;

		private OrderStateCode(Integer code) {
			this.code = code;
		}
	}

	/**
	 * 首页控制位置的状态
	 */
	public static enum HomeManagerType {

		PARTNERS("Partners"),
		PUBLICITY("Publicity"),
		LEARNING("Learning"),
		POSTER("Poster"),
		DEMONSTRATION("Demonstration"),
		BANNER("Banner");

		public String type;

		private HomeManagerType(String type) {
			this.type = type;
		}
	}

	/**
	 * 咔咔硕登录后回调奇码网的登录链接
	 */
	public static final String	URL_YUNEAR_LOGIN	= "/wechat/login.do?1=1";
	/**
	 * 起码网付费视频的支付页面链接
	 */
	public static final String	URL_YUNEAR_BUY		= "/wechat/media.do";
	/**
	 * 咔咔硕同步订单链接
	 */
	public static final String	URL_KAKASURE_SYN	= "/api_yunear_pub/order/syn";
	/**
	 * 起码网扫码链接
	 */
	public static final String	URL_YUNEAR_RCODE	= "/wechat/qrcode.do?id=";
	/**
	 * ffmpeg.exe 物理地址
	 */
	public static final String	FFMPEG_URL			= "c:\\ffmpeg\\ffmpeg.exe";
}
