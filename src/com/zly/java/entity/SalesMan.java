package com.zly.java.entity;

/**
 * SalesMan 售货员实体类
 * @author 赵林洋
 * @version 1.0
 */
public final class SalesMan {

	private int sId;
	private String sName;
	private String sPassword;
	
	/**
	 * 验证用户登陆
	 * @param sId,sPassword
	 */
	public SalesMan(int sId, String sPassword) {
		this.sId = sId;
		this.sPassword = sPassword;
	}
	
	/**
	 * 查询用户、更改用户密码
	 * @param sId,sName,sPassword
	 */
	public SalesMan(int sId, String sName, String sPassword) {
		this.sId = sId;
		this.sName = sName;
		this.sPassword = sPassword;
	}
	
	/**
	 * 添加用户
	 * @param sNameame,sPassword
	 */
	public SalesMan(String sNameame, String sPassword) {
		this.sName = sNameame;
		this.sPassword = sPassword;
	}
	
	// get.set方法
	public int getSId() {
		return sId;
	}
	public void setSId(int sId) {
		this.sId = sId;
	}
	
	public String getSName() {
		return sName;
	}
	public void setSName(String sName) {
		this.sName = sName;
	}
	
	public String getSPassword() {
		return sPassword;
	}
	public void setSPassword(String sPassword) {
		this.sPassword = sPassword;
	}
}















