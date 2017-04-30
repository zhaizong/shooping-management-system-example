package com.zly.java.entity;

/**
 * GoodsSales 购物结算实体类
 * @author 赵林洋
 * @version 1.0
 */
public final class GoodsSales {

	// goods id
	private int gId;
	// sales id
	private int sId;
	// sales number
	private int sNum;
	
	// goods name 
	private String gName;
	// goods price
	private double gPrice;
	// goods number
	private int gNum;
	// single goods sales total
	private int allSalesNum;
	
	/**
	 * 购物结算
	 * @param gId,sId,sNum
	 */
	public GoodsSales(int gId, int sId, int sNum) {
		this.gId = gId;
		this.sId = sId;
		this.sNum = sNum;
	}
	
	/**
	 * 展现商品列表
	 * @param gName,gPrice,gNum,allSalesNum
	 */
	public GoodsSales(String gName, double gPrice, int gNum, int allSalesNum) {
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
		this.allSalesNum = allSalesNum;
	}
	
	// setter getter
	public int getGId() {
		return gId;
	}
	public void setGId(int gId) {
		this.gId = gId;
	}
	
	public int getSId() {
		return sId;
	}
	public void setSId(int sId) {
		this.sId = sId;
	}
	
	public int getSNum() {
		return sNum;
	}
	public void setSNum(int sNum) {
		this.sNum = sNum;
	}
	
	public String getGName() {
		return gName;
	}
	public void setGName(String gName) {
		this.gName = gName;
	}
	
	public double getGPrice() {
		return gPrice;
	}
	public void setGPrice(double gPrice) {
		this.gPrice = gPrice;
	}
	
	public int getGNum() {
		return gNum;
	}
	public void setGNum(int gNum) {
		this.gNum = gNum;
	}
	
	public int getAllSalesNum() {
		return allSalesNum;
	}
	public void setAllSalesNum(int allSalesNum) {
		this.allSalesNum = allSalesNum;
	}
}























