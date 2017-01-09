
package com.kakasure.bqf.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kakasure.bqf.util.Const;
import com.kakasure.bqf.util.NginxUtil;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.ServiceHelper;
import com.kakasure.lang.util.DateUtil;

/**
 * 上传servlet
 * 
 * @author zhangb
 *
 */
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	private Logger				logger		= LoggerFactory.getLogger(UploadServlet.class);
	private String				PATH		= "/" + Const.FILEPATH;

	private static List<String>	pictureList	= new ArrayList<String>();
	private static List<String>	videoList	= new ArrayList<String>();
	private static List<String>	audioList	= new ArrayList<String>();

	private PropertyManager		propertyManager;

	public UploadServlet() {
		propertyManager = (PropertyManager) ServiceHelper.getService("propertyManager");

		// 图片
		pictureList.add(".jpg");
		pictureList.add(".jpeg");
		pictureList.add(".png");

		// 视频
		videoList.add(".mp4");
		videoList.add(".webm");
		videoList.add(".ogg");
		videoList.add(".mpeg");
		videoList.add(".wmv");

		// 音频
		// audioList.add(".wma");
		audioList.add(".mp3");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("GET METHOD NOT SUPPORT");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 未来可能要写删除
	};

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("uploadType");

		logger.debug("parameter type: " + type);
		if ("1".equals(type)) {
			upload(request, response, pictureList);
		} else if ("2".equals(type)) {
			upload(request, response, videoList);
		} else if ("3".equals(type)) {
			upload(request, response, audioList);
		} else {
			UploadResult result = new UploadResult();
			result.setCode(200444);
			result.setMsg("缺少参数!");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(JSON.toJSONString(result));
			return;
		}

	}

	public void upload(HttpServletRequest request, HttpServletResponse response, List<String> typeList) throws ServletException, IOException {
		UploadResult result = new UploadResult();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String savePath = propertyManager.staticPath + PATH + DateUtil.getDays()
												+ "/";

		File f1 = new File(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			return;
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		String realName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				logger.debug("name: " + item.getName());
				realName = item.getName();
				realName = realName.substring(0, realName.lastIndexOf("."));

				if (name == null || name.trim().equals("")) {
					continue;
				}

				// 扩展名格式：
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}

				logger.debug("size: " + item.getSize());
				logger.debug("type: " + item.getContentType());
				logger.debug("extName: " + extName);
				if (!typeList.contains(extName.toLowerCase())) {
					result.setCode(200444);
					result.setMsg(extName + "类型的文件不允许上传!");
					// response.setStatus(400);
					response.getWriter().print(JSON.toJSONString(result));
					return;
				}

				File file = null;
				do {
					// 生成文件名：
					name = new SimpleDateFormat("hhmmss").format(new Date()); // 获取当前日期
					name = name + (int) (Math.random() * 9000 + 1000);
					file = new File(savePath + name + extName);
				} while (file.exists());

				File saveFile = new File(savePath + name + extName);
				logger.debug("saveFile:" + saveFile.getPath());
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		result.setName(realName);
		result.setPath(PATH + DateUtil.getDays() + "/" + (name.trim()
												+ extName.trim()).trim());
		result.setPath(NginxUtil.addSecurelinkForApply(result.getPath()));
		logger.info("Upload Result:\n" + result.toString());
		response.getWriter().print(JSON.toJSONString(result));
	}
}
