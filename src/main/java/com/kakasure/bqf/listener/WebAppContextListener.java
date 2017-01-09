
package com.kakasure.bqf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kakasure.exception.BaseException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.ServiceHelper;
import com.kakasure.oauth.util.OauthConstants;

public class WebAppContextListener implements ServletContextListener {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void contextInitialized(ServletContextEvent event) {
		logger.info("WebApplicationContext init.");

		ServiceHelper.content = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

		PropertyManager propertyManager = ServiceHelper.getPropertyManager();

		OauthConstants.client_id = propertyManager.urlSsoName + "qimanet";
		OauthConstants.client_secret = "kakasure_secret_qimanet";

		BaseException.exceptionUtil = ServiceHelper.getExceptionUtil();

	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}