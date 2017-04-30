package com.zly.java.page;

import java.util.ArrayList;
import java.util.Scanner;

import com.zly.java.dao.GoodsDao;
import com.zly.java.dao.GoodsSalesDao;
import com.zly.java.dao.SalesManDao;
import com.zly.java.entity.Goods;
import com.zly.java.entity.GoodsSales;
import com.zly.java.entity.SalesMan;
import com.zly.java.tools.Arith;
import com.zly.java.tools.QueryPrint;
import com.zly.java.tools.ScannerChoice;

/**
 * 超市购物管理系统主界面             
 * @author 赵林洋 
 * @version 1.0
 */
public class MainPage {
	
	/**
	 * 入口函数
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainPage.mainPage();
	}
	
	/*
	 * 主界面
	 * */
	public static void mainPage() {
		System.out.println("***********************\n");
		System.out.println("\t 1.商品维护\n");
		System.out.println("\t 2.前台收银\n");
		System.out.println("\t 3.商品管理\n");
		System.out.println("***********************\n");
		System.out.println("\n请输入选项，或者按0退出。");
		do {
			String choice = ScannerChoice.scannerInfoString();
			String regex = "[0-3]"; // 正则表达式
			if (choice.matches(regex)) { // 判断是否与regex匹配
				int info = Integer.parseInt(choice);
				switch (info) {
				case 0:
					System.out.println("----------------");
					System.out.println("您已经退出系统");
					System.exit(1);
					break;
				case 1: // 商品维护
					maintenancePage();
					break;
				case 2:
					checkstandLogPage();
					break;
				case 3:
					break;
				default:
					break;
				}
			}
			System.err.println("输入有误!");
			System.out.println("重新选择或按0退出");
		} while (true);
	}
	
	/*
	 * 1.商品维护界面
	 * */
	public static void maintenancePage() {
		System.out.println("***********************\n");
		System.out.println("\t 1.添加商品\n");
		System.out.println("\t 2.更改商品\n");
		System.out.println("\t 3.删除商品\n");
		System.out.println("\t 4.查询商品\n");
		System.out.println("\t 5.显示所有商品\n");
		System.out.println("***********************\n");
		System.out.println("\n请输入选项，或者按0返回上一级菜单.");
		do {
			String choice = ScannerChoice.scannerInfoString();
			String regex = "[0-5]";
			if (choice.matches(regex)) { // 判断是否与regex匹配
				int info = Integer.parseInt(choice);
				switch (info) {
				case 0:
					mainPage();
					break;
				case 1:
					GoodsPage.addGoodsPage();
					break;
				case 2:
					GoodsPage.updateGoodsPage();
					break;
				case 3:
					GoodsPage.deleteGoodsPage();
					break;
				case 4:
					GoodsPage.queryGoodsPage();
					break;
				case 5:
					GoodsPage.displayGoodsPage();
					break;
				default:
					break;
				}
			}
			System.err.println("输入有误!");
			System.out.println("重新选择或按0返回上一级菜单");
		} while (true);
	}
	
	/*
	 * 2.前台收银登陆界面
	 * */
	public static void checkstandLogPage() {
		System.out.println("*******欢迎使用超市购物管理系统*******");
		System.out.println("\t 1.登陆系统\n");
		System.out.println("\t 2.退出\n");
		System.out.println("----------------------------------");
		System.out.println("请输入选项，或按0返回上一级菜单。");
		do {
			String choice = ScannerChoice.scannerInfoString();
			String regex = "[0-2]";
			if (choice.matches(regex)) {
				int info = Integer.parseInt(choice);
				switch (info) {
				case 0:
					mainPage();
					break;
				case 1:
					int loginTimes = 3; // 3次登陆机会
					while (loginTimes != 0) {
						loginTimes--;
						System.out.println("---用户名---");
						String sName = ScannerChoice.scannerInfoString();
						System.out.println("---密码---");
						String sPassword = ScannerChoice.scannerInfoString();
						// 以用户名从数据库中获取用户密码
						ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName);
						
						if (salesManInfo == null || salesManInfo.size() == 0) {
							System.err.println("\t!!用户名输入有误!!\n");
							System.out.println("\n剩余登陆次数: " + loginTimes);
						} else {
							// 只返回了一组数值，只遍历1次即可
							SalesMan salesMan = salesManInfo.get(0);
							// 验证密码
							if (sPassword.equals(salesMan.getSPassword())) {
								System.out.println("\t---账户登陆成功---");
								// 参数为营业员编号 sId
								shoppingSettlementPage(salesMan.getSId());
							} else {
								System.out.println("\t!!密码错误!!\n");
								System.out.println("\n剩余登陆次数: " + loginTimes);
							}
						}
					}
					// loginTimes == 0
					System.out.println("-----------------------");
					System.out.println("\t!!您已被强制退出系统!!");
					System.exit(1);
					break;
				case 2:
					System.out.println("-----------------------");
					System.out.println("\t!!您已退出系统!!");
					System.exit(0);
					break;
				default:
					break;
				}
			}
			System.err.println("输入有误!");
			System.out.println("重新选择或按0返回上一级菜单");
		} while (true);
	}
	
	/*
	 * 3.商品管理界面
	 * */
	public static void commodityManagementPage() {
		System.out.println("***********************\n");
		System.out.println("\t 1.售货员管理\n");
		System.out.println("\t 2.列出当日卖出列表\n");
		System.out.println("***********************\n");
		System.out.println("请输入选项，或按0返回上一级菜单。");
		do {
			String choice = ScannerChoice.scannerInfoString();
			String regex = "[0-2]";
			if (choice.matches(regex)) {
				int info = Integer.parseInt(choice);
				switch (info) {
				case 0:
					mainPage();
					break;
				case 1:
					salesManManagementPage();
					break;
				case 2:
					GoodsSalesPage.dailySaleGoodsPage();
					break;
				default:
					break;
				}
			}
			System.err.println("输入有误!");
			System.out.println("重新选择或按0返回上一级菜单");
		} while (true);
	}
	
	/*
	 * 购物结算界面
	 * */
	public static void shoppingSettlementPage(int salesmanSid) {
		System.out.println("\n\t*******购物结算*******\n");
		do {
			System.out.println("按 s 开始购物结算，按 0 返回账户登陆界面。");
			String choNext = ScannerChoice.scannerInfoString();
			if ("0".equals(choNext)) {
				checkstandLogPage();
			} else if ("s".equals(choNext) || "S".equals(choNext)) {
				System.out.println("\n--请输入商品关键字");
				// 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
				int gid = QueryPrint.querySettlement();
				switch (gid) {
				case -3:
					// 无此商品，重新循环
					break;
				case -1:
					System.err.println("\t--抱歉，该商品已售空");
				default:
					System.out.println("--按商品编号选择商品--");
					// 传参gid，调用精确查询商品
					int shoppingGId = ScannerChoice.scannerNum();
					ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGId, null);
					if (goodsList == null || goodsList.size() <= 0) {
						System.err.println("\t 未查询到此商品\n");
					} else {
						Goods goods = goodsList.get(0);
						int gNum = goods.getGnum();
						double gPrice = goods.getGprice();
						
						System.out.println("--请输入购买数量--");
						do {
							// 获取用户要购买的数量
							int choiceGoodsNum = ScannerChoice.scannerNum();
							if (choiceGoodsNum > gNum) {
								System.err.println("\t!!仓库储备不足!!");
								System.out.println("--请重新输入购买数量--");
							} else {
								// 利用BigDecimal(大小数)作乘法运算
								double allPrice = Arith.mul(choiceGoodsNum, gPrice);
								System.out.println("\t\t\t购物车结算\n");
								System.out.println("\t\t商品名称\t商品单价\t购买数量\t总价\n");
								System.out.println("\t\t" + goods.getGname() + "\t" + goods.getGprice() + "$\t" + choiceGoodsNum + "\t" + allPrice + "$\n");
								do {
									System.out.println("确认购买: Y/N");
									String choShopping = ScannerChoice.scannerInfoString();
									if ("y".equals(choShopping) || "Y".equals(choShopping)) {
										System.out.print("\n总价: " + allPrice + "$");
										System.out.println("\n实际缴付金额");
										do {
											double amount = ScannerChoice.scannerInfo();
											// 用户交钱与购买物品总价间的差额
											double balance = Arith.sub(amount, allPrice);
											if (balance < 0) {
												System.out.println("\t!!缴付金额不足!!");
												System.out.println("\n请重新输入缴付金额($");
											} else {
												// 数据库购物结算操作
												// 1.更改goods表数量
												// 2.增加sales表数量
												// 原商品数量gNum, 结算员工id salesManSid
												// sales表操作
												GoodsSales goodsSales = new GoodsSales(goods.getGid(), salesmanSid, choiceGoodsNum);
												boolean isInsert = new GoodsSalesDao().shoppingSettlement(goodsSales);
												/* goods表操作 */
												int goodsNewNum = gNum - choiceGoodsNum; // 现goods表中该商品数量(剩余的)
												Goods newGoods = new Goods(goods.getGid(), goodsNewNum);
												boolean isUpdate = new GoodsDao().updateGoods(3, newGoods);
												if (isUpdate && isInsert) {
													System.out.println("找零: " + balance);
													System.out.println("\n谢谢光临，欢迎下次惠顾");
												} else {
													// 出现这个错误一定是数据库操作有问题
													System.err.println("支付失败!");
												}
												// 最后跳转到到购物结算页面
												shoppingSettlementPage(salesmanSid);
												/* goods表操作 */
											}
										} while (true);
									} else if ("n".equals(choShopping) || "N".equals(choShopping)) {
										shoppingSettlementPage(salesmanSid);
									}
									System.err.println("\t 请确认购物意向!");
								} while (true);
							}
						} while (true);
					}
					break;
				}
			} else {
				System.err.println("\t 请输入规定字符! \n");
			}
		} while (true);
	}
	
	/*
	 * 售货员管理界面
	 * */
	public static void salesManManagementPage() {
		System.out.println("***************************\n");
		System.out.println("\t 1.添加售货员\n");
		System.out.println("\t 2.删除售货员\n");
		System.out.println("\t 3.更改售货员\n");
		System.out.println("\t 4.查询售货员\n");
		System.out.println("\t 5.显示所有售货员\n");
		System.out.println("***************************");
		
		System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
		do {
			String choice = ScannerChoice.scannerInfoString();
			String regex = "[0-5]";
			if (choice.matches(regex)) {
				int info = Integer.parseInt(choice);
				switch (info) {
				case 0:
					commodityManagementPage();
					break;
				case 1:
					SalesManPage.addSalesManPage();
					break;
				case 2:
					SalesManPage.deleteSalesManPage();
					break;
				case 3:
					SalesManPage.updateSalesManPage();
					break;
				case 4:
					SalesManPage.querySalesManPage();
					break;
				case 5:
					SalesManPage.displaySalesManPage();
					break;
				default:
					break;
				}
			}
			System.err.println("输入有误!");
			System.out.println("重新选择或按0返回上一级菜单");
		} while (true);
	}

}
