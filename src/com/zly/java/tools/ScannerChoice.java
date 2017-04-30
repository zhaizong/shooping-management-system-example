package com.zly.java.tools;

import java.util.Scanner;

import com.zly.java.page.GoodsPage;

/**
 * 1.各种完成操作后的，选择下一步
 * 2.界面选择操作
 * @author 赵林洋
 * @version 1.0
 * */
public class ScannerChoice {

	private static Scanner scanner;

	/**
	 * @return string 获取键盘输入
	 * */
	public static String scannerInfoString() {
		scanner = new Scanner(System.in);
		System.out.print("请输入: ");
		return scanner.next();
	}
	
	/**
	 * @return double 键盘获取商品价格，小数点后两位
	 * */
	public static double scannerInfo() {
		double num = 0.00;
		do {
			scanner = new Scanner(System.in);
			System.out.println("保留小数点后两位，请输入：");
			String info = scanner.next();
			String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})"; // 保留小数点后2为小数
			if (info.matches(regex)) {
				num = Double.parseDouble(info);
			} else {
				System.err.println("！输入有误！");
				continue;
			}
			break;
		} while (true);
		return num;
	}
	
	/**
	 * @return int 键盘获取商品数量
	 */
	public static int scannerNum() {
		int num = 0;
		String regex = "([1-9])|([1-9][0-9]+)"; // 商品数量
		do {
			scanner = new Scanner(System.in);
			System.out.println("请输入：");
			String nums = scanner.next();
			if (nums.matches(regex)) {
				num = Integer.parseInt(nums);
			} else {
				System.err.println("!输入有误！");
				continue;
			}
			break;
		} while (true);
		return num;
	}
	
	/**
	 * 获取用户-更改完商品-下一步
	 * 获取用户-删除完商品-下一步
	 * 获取用户-添加完商品-下一步
	 * @param 调用者
	 */
	public static void changedInfoNext(String oper) {
		do {
			System.out.println("是否继续进行-当前操作:(Y/N)");
			String choice = ScannerChoice.scannerInfoString();
//			在JAVA中: Equals比较的是值,==比较的是地址
			if ("y".equals(choice) || "Y".equals(choice)) {
//				下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同)
				if ("upateGoodsPage".equals(oper)) {
					
				} else if ("deleteGoodsPage".equals(oper)) {
					
				} else if ("addGoodsPage".equals(oper)) {
					GoodsPage.addGoodsPage();
				}
			} else if ("n".equals(choice) || "N".equals(choice)) {
				
			}
			System.out.println("\n输入有误！请重新输入.");
		} while (true);
	}
	
	/**
	 * 获取用户-添加-完售货员-下一步
	 * 获取用户-删除-完售货员-下一步
	 * 获取用户-更改-完售货员-下一步
	 * 获取用户-查询-完售货员-下一步
	 * @param 调用者
	 */
	public static void choiceSalesManNext(String oper) {
		do {
			System.out.println("是否继续进行-(Y/N)");
			String choice = scannerInfoString();
			if ("y".equals(choice) || "Y".equals(choice)) {
				// 下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
				if ("updateSalesMan".equals(oper)) {
					
				}
			}
		} while (true);
	}
}
















