package com.zly.java.entity;

/**
 * goods 商品实体类
 * @author 赵林洋
 */
public final class Goods {

	// 数据库Goods表主鍵
	private int gid;
	private String gname;
	private double gprice;
	private int gnum;
	
	/**
	 * 添加商品信息 构造函数
	 * @param gname, gprice, gnum
	 * */
	public Goods(String gname, double gprice, int gnum) {
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gnum;
	}
	
	/**
	 * 展示所有商品 构造函数
	 * @param gid, gname, gprice, gnum
	 */
	public Goods(int gid, String gname, double gprice, int gnum) {
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gnum;
	}
	
	/**
	 * 根据编号更改商品信息 构造函数
	 * @param gid, gnum
	 */
	public Goods(int gid, int gnum) {
		this.gid = gid;
		this.gnum = gnum;
	}
	
	/**
	 * 根据编号更改商品信息 构造函数
	 * @param gid, gprice
	 */
	public Goods(int gid, double gprice) {
		this.gid = gid;
		this.gprice = gprice;
	}
	
	/**
	 * 根据编号更改商品信息 构造函数
	 * @param gid, gname
	 */
	public Goods(int gid, String gname) {
		this.gid = gid;
		this.gname = gname;
	}
	
	/*
	 * public setter&getter
	 * */
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
}
