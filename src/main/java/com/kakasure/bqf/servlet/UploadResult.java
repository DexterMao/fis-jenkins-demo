
package com.kakasure.bqf.servlet;

/**
 * 上传返回结果
 * 
 * @author zhangb
 *
 */
public class UploadResult {

	private Integer	code;
	private String	msg;
	private String	name;
	private String	path;

	public UploadResult() {
		this.code = 200000;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("code:" + this.code + "\n");
		sb.append("msg:" + this.msg + "\n");
		sb.append("name:" + this.name + "\n");
		sb.append("path:" + this.path + "\n");
		return sb.toString();
	}
}
