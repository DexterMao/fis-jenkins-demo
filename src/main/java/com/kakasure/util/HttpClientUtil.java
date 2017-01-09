package com.kakasure.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger		logger	= LoggerFactory.getLogger(HttpClientUtil.class);
	private final static String	UTF8	= "UTF-8";
	private final static int	TIMEOUT	= 20000;

	/**
	 * 发送请求
	 * 
	 * @param url
	 *            Request Url
	 * @param headers
	 *            Request Headers
	 * @param reqParam
	 *            Request Params
	 * @param charSet
	 *            字符集，默认UTF-8
	 * @param timeout
	 *            超时时间
	 * @return
	 */
	public static String postSend(String url, Map<String, String> headers, Object reqParam, String charSet, int timeout) throws Exception {
		HttpPost httpPost = getHttpPost(url, headers, reqParam, charSet, timeout);
		return sendPost(httpPost);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            Request Url
	 * @param headers
	 *            Request Headers
	 * @param reqParam
	 *            Request Params
	 * @return
	 */
	public static String postSend(String url, Map<String, String> headers, Object reqParam) throws Exception {
		return postSend(url, headers, reqParam, UTF8, TIMEOUT);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param reqParam
	 * @return
	 */
	public static String postSend(String url, Object reqParam) throws Exception {
		return postSend(url, null, reqParam, UTF8, TIMEOUT);
	}

	/**
	 * 发送请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendPost(HttpPost httpPost) throws Exception {
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		String str = "ERR";
		try {
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				str = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				logger.info("==>>HTTP返回信息：" + str);
			} else {
				str += ":" + statusCode;
				logger.info("系统异常：" + statusCode);
			}
		} catch (ClientProtocolException e1) {
			// e1.printStackTrace();
			str += ":" + e1.getMessage();
			logger.error("HttpClient Request ClientProtocolException!" + e1.getMessage(), e1);
			throw new ClientProtocolException(e1);
		} catch (IOException e2) {
			str += ":" + e2.getMessage();
			logger.error("HttpClient Request IOException!" + e2.getMessage(), e2);
			throw new IOException(e2);
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 设置请求参数List<NameValuePair> nvps
	 * 
	 * @param url
	 *            Request Url
	 * @param headers
	 *            Request Headers
	 * @param reqParam
	 *            Request Params
	 * @param charSet
	 *            字符集，默认UTF-8
	 * @param timeout
	 *            超时时间
	 */
	@SuppressWarnings("unchecked")
	private static HttpPost getHttpPost(String url, Map<String, String> headers, Object obj, String charset, int timeout) {
		HttpPost httpost = new HttpPost(url);
		try {
			// 请求参数
			if (obj != null) {
				HttpEntity httpEntity = null;
				if (obj instanceof Map) {
					List<NameValuePair> nameValues = new ArrayList<NameValuePair>();
					Map<String, String[]> params = (Map<String, String[]>) obj;
					for (Map.Entry<String, String[]> entry : params.entrySet()) {
						for (String value : entry.getValue()) {
							nameValues.add(new BasicNameValuePair(entry.getKey(), value));
						}
					}
					httpEntity = new UrlEncodedFormEntity(nameValues, charset);
				} else if (obj instanceof String) {
					String xmlStr = (String) obj;
					httpEntity = new StringEntity(xmlStr, charset);
				}
				httpost.setEntity(httpEntity);
			}
			// 请求头信息(待写)
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpost.addHeader(entry.getKey(), entry.getValue());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpost.setConfig(setHttpTimeOut(timeout));
		return httpost;
	}

	/**
	 * 设置默认请求和传输超时时间
	 * 
	 * @param timeout
	 *            默认20000
	 * @return
	 */
	private static RequestConfig setHttpTimeOut(int timeout) {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setStaleConnectionCheckEnabled(true).build();
		return defaultRequestConfig;
	}
}
