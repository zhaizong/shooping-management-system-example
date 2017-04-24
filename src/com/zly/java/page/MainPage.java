package com.zly.java.page;

import java.util.Scanner;

import com.zly.java.entity.Goods;
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
					break;
				case 2:
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
					break;
				case 2:
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
				
			}
		} while (true);
	}
	
	/*
	 * 售货员管理界面
	 * */
	public static void salesManManagementPage() {
		System.out.println("***************************\n");
		System.out.println("\t 1.添加售货员\n");
		System.out.println("\t 2.更改售货员\n");
		System.out.println("\t 3.删除售货员\n");
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
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
