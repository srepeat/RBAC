package com.atguigu.atcrowdfunding.bean;

/**
 * 回调结果集
 * @author 鲜磊
 *
 */
public class AJAXResult {
	private boolean success;
	private Object data;

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
