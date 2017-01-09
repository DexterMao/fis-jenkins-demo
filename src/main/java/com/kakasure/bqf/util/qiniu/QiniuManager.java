
package com.kakasure.bqf.util.qiniu;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kakasure.lang.util.FileUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 七牛管理器
 * 
 * @author zhangb
 *
 */
public class QiniuManager {
	protected Logger						logger		= LoggerFactory.getLogger(getClass());
	private static QiniuManager				qiniuManager;
	private static Handler					handler;

	private static BlockingQueue<QiniuData>	uploadQueue	= new LinkedBlockingQueue<QiniuData>(1000);

	private QiniuManager() {
	}

	/**
	 * 获取七牛管理器实例
	 * 
	 * @return
	 */
	public static QiniuManager getInstance() {
		if (null == qiniuManager) {
			qiniuManager = new QiniuManager();
			handler = new Handler("七牛任务进程", uploadQueue);
			handler.start();
		}

		return qiniuManager;
	}

	/**
	 * 添加到上传队列
	 * 
	 * @param data
	 */
	public void addQueue(QiniuData data) {
		if (data == null)
			return;

		if (StringUtils.isBlank(data.getSourceFilePath())
												|| StringUtils.isBlank(data.getRemoteFileName())) {
			logger.warn("添加七牛上传队列失败，缺少SourceFilePath 或者 RemoteFileName");
			return;
		}

		uploadQueue.add(data);
	}

	public static void main(String[] args) {
		Auth auth = Auth.create("Hj9UFm_tXJMGDZgor0IDzZgnI5SSQi6qZ0AyfTSC", "hiX2oBEQa5_JfJYL-BVQzaSOKK2cC87ZxyVZF7Sl");
		BucketManager bucketManager = new BucketManager(auth);
		try {
			bucketManager.delete("qimanet", "/uploadify/uploads/20150824/0426473902.mp4");
		} catch (QiniuException e) {
			e.printStackTrace();
		}

	}
}

/**
 * 处理进程
 */
class Handler extends Thread {
	protected Logger					logger			= LoggerFactory.getLogger(getClass());
	private BlockingQueue<QiniuData>	uploadQueue;
	private Auth						auth			= Auth.create("Hj9UFm_tXJMGDZgor0IDzZgnI5SSQi6qZ0AyfTSC", "hiX2oBEQa5_JfJYL-BVQzaSOKK2cC87ZxyVZF7Sl");
	private UploadManager				uploadManager	= new UploadManager();
	private BucketManager				bucketManager	= new BucketManager(auth);
	private final String				BUCKET			= "qimanet";
	private static String				uploadToken;

	/**
	 * 获得上传凭证
	 * 
	 * @return
	 */
	private String getUploadToken() {
		if (StringUtils.isBlank(uploadToken)) {
			uploadToken = auth.uploadToken(BUCKET);// 简单上传，使用默认策略
		}
		return uploadToken;
	}

	/**
	 * 上传文件到七牛
	 * 
	 * @param file
	 * @param key
	 */
	private void uploadFile(File file, String key) {
		try {
			logger.info("开始上传文件到七牛...");
			Response response = uploadManager.put(file, key, getUploadToken());
			logger.info("上传文件完成");
			logger.info(response.statusCode + "");
			logger.info(response.bodyString());
			if (StringUtils.isNotBlank(response.error))
				logger.info(response.error);
		} catch (QiniuException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除七牛上的文件
	 * 
	 * @param key
	 */
	private void deleteFile(String key) {
		try {
			logger.info("删除七牛上的文件，key:" + key);
			bucketManager.delete(BUCKET, key);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}

	Handler(String name, BlockingQueue<QiniuData> uploadQueue) {
		super(name);
		this.uploadQueue = uploadQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);

			// 当上传队列的文件数量为空时，那么在while里面该上传队列会自动阻塞,wait状态,等待处理
			while (true) {
				logger.info("当前上传队列长度：" + uploadQueue.size());
				QiniuData data = uploadQueue.take();
				logger.info("root路径：" + data.getRootPath());
				logger.info("源文件路径：" + data.getSourceFilePath());
				logger.info("源文件路径旧：" + data.getSourceFilePathOld());
				logger.info("目标文件名称：" + data.getRemoteFileName());

				if (StringUtils.isNotBlank(data.getSourceFilePath())
														&& StringUtils.isNotBlank(data.getSourceFilePathOld())
														&& !data.getSourceFilePath().equals(data.getSourceFilePathOld())) {
					logger.warn("修改了视频文件，删除原来的文件");
					String deletePath = data.getSourceFilePathOld();

					FileUtil.delFile(data.getRootPath() + deletePath);// 删除原来的视频文件

					if (deletePath.indexOf("mp4") == -1 && deletePath.indexOf("MP4") == -1) {// 如果不是mp4，要删除转换的文件
						FileUtil.delFile(data.getRootPath() + deletePath.substring(0, deletePath.lastIndexOf("."))
																+ ".mp4");
					}
					if (StringUtils.isNotBlank(deletePath)) {
						deleteFile(deletePath);// 删除七牛上的文件
					}
					Thread.sleep(5000);// 5秒后再继续，同时请求删除、上传操作，七牛会报错。。。
				}

				File file = new File(data.getRootPath() + data.getSourceFilePath());
				uploadFile(file, data.getRemoteFileName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}