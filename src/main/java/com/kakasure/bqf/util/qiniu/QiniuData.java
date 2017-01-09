
package com.kakasure.bqf.util.qiniu;

import java.io.Serializable;

/**
 * 七牛数据对象
 * 
 * @author zhangb
 *
 */
public class QiniuData implements Serializable {
	private static final long	serialVersionUID	= -8083095329853020187L;
	private String				rootPath;
	private String				sourceFilePath;
	private String				sourceFilePathOld;
	private String				remoteFileName;

	public QiniuData() {
	}

	/**
	 * 初始化
	 * 
	 * @param rootPath
	 * @param sourceFilePath
	 * @param sourceFilePathOld
	 * @param remoteFileName
	 */
	public QiniuData(String rootPath, String sourceFilePath, String sourceFilePathOld,
			String remoteFileName) {
		this.rootPath = rootPath;
		this.sourceFilePath = sourceFilePath;
		this.sourceFilePathOld = sourceFilePathOld;
		this.remoteFileName = remoteFileName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getSourceFilePathOld() {
		return sourceFilePathOld;
	}

	public void setSourceFilePathOld(String sourceFilePathOld) {
		this.sourceFilePathOld = sourceFilePathOld;
	}

	public String getRemoteFileName() {
		return remoteFileName;
	}

	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}

}
