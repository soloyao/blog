package com.zmy.pojo;

/**
 * @ClassName:Result
 * @Description:返回统一的响应格式
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:25:13
 * @param <T>
 */
public class Result<T> {
	private Integer code;
	private String msg;
	private T data;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Result() {
		super();
	}
	public Result(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
