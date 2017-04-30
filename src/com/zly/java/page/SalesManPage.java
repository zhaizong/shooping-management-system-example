package com.zly.java.page;

import java.util.ArrayList;

import com.zly.java.dao.SalesManDao;
import com.zly.java.entity.SalesMan;
import com.zly.java.tools.QueryPrint;
import com.zly.java.tools.ScannerChoice;

/**
 * 操作售货员界面
 * 增删改查       
 * @author 赵林洋
 * @version 1.0
 */
public final class SalesManPage extends ScannerChoice {

	/**
	 * 1.添加售货员界面
	 */
	public static void addSalesManPage() {
		System.out.println("\t正在执行添加售货员操作\n");
		
		System.out.println("\n添加售货员-姓名");
		String sName = scannerInfoString();
		System.out.println("\n添加售货员-密码");
		String sPassword = scannerInfoString();
		
		SalesMan salesMan = new SalesMan(sName, sPassword);
		boolean b = new SalesManDao().addSalesMan(salesMan);
		if (b) {
			System.out.println("\n\t!您已成功添加售货员到数据库!");
		} else {
			System.out.println("添加售货员失败");	
		}
	}
	
	/**
	 * 2.删除售货员界面
	 */
	public static void deleteSalesManPage() {
		System.out.println("\t执行删除售货员操作\t");
		System.out.println("请输入想要删除的售货员名字");
		String sName = scannerInfoString();
		// 调用精确查找售货员函数
		ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
		if (salesManList.size() <= 0) {
			System.err.println("\t!!查无此人!!");
			choiceSalesManNext("deleteSalesMan");
		} else {
//			显示将要删除的售货员信息
			System.out.println("\t\t\t删除售货员信息\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			for (int index = 0; index < salesManList.size(); index++) {
				SalesMan salesMan = salesManList.get(index);
				System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassword());
				System.out.println();
			}
			// 确认是否真的删除
			do {
				System.out.println("\n确认删除该售货员：Y/N");
				String choice = scannerInfoString();
				if ("y".equals(choice) || "Y".equals(choice)) {
//					进行刪除-数据库操作
					boolean isDeleteSalesMan = new SalesManDao().deleteSalesMan(sName);
					if (isDeleteSalesMan) {
						System.err.println("\t！！已成功刪除该售货员！！\n");
					} else {
						System.err.println("\t！！刪除该售货员失敗！！");
					}
					choiceSalesManNext("deleteSalesMan");
				} else if ("n".equals(choice) || "N".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				System.err.println("\t!!输入有误,请重新输入!!");
			} while (true);
		}
	}
	
	/**
	 * 3.更改售货员界面
	 */
	public static void updateSalesManPage() {
		System.out.println("\t正在执行更改售货员操作\n");
		System.out.println("请输入想要更改的售货员名字");
		String sName = scannerInfoString();
		
//		调用精确查找售货员函数
		ArrayList<SalesMan> salesMans = new QueryPrint().querySalesMan(sName);
		if (salesMans.size() <= 0) {
			System.err.println("\t!!查无此人!!");
			choiceSalesManNext("updateSalesMan");
		} else {
//			显示将要更改的售货员信息
			System.out.println("\t\t\t售货员信息\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			
			SalesMan salesMan = salesMans.get(0);
			System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassword());
			System.out.println();
			
			// xu选择更改售货员内容
			System.out.println("\n----------请选择要更改的内容\n");
			System.out.println("\t1.更改售货员-姓名");
			System.out.println("\t2.更改售货员-密码");
			do {
				String choice = scannerInfoString();
				String regex = "[0-2]";
				if (choice.matches(regex)) {
					int info = Integer.parseInt(choice);
					switch (info) {
					case 0:
						MainPage.salesManManagementPage();
						break;
					case 1:
						System.out.println("更改售货员-新姓名");
						String newName = scannerInfoString();
						
						SalesMan newSalesManName = new SalesMan(salesMan.getSId(), newName, null);
						boolean isUpdateNameSuccess = new SalesManDao().updateSalesMan(1, newSalesManName);
						if (isUpdateNameSuccess) {
							System.out.println("\n\t成功更新售货员名字到数据库\n");
						} else {
							System.err.println("\n\t售货员名字更新失败");
						}
						choiceSalesManNext("updateSalesMan");
						break;
					case 2:
						System.out.println("更改售货员-新密码");
						String newPassword = scannerInfoString();
						
						SalesMan newSalesManPassword = new SalesMan(salesMan.getSId(), null, newPassword);
						boolean isUpdatePasswordSuccess = new SalesManDao().updateSalesMan(2, newSalesManPassword);
						if (isUpdatePasswordSuccess) {
							System.out.println("\n\t成功更新售货员密码到数据库\n");
						} else {
							System.err.println("\n\t售货员密码更新失败");
						}
						choiceSalesManNext("updateSalesMan");
						break;
					default:
						break;
					}
				}
				System.out.println("\t输入有误");
				System.out.println("\n继续选择或按0返回上一级菜单");
			} while (true);
		}
	}
	
	/**
	 * 4.查询售货员界面
	 */
	public static void querySalesManPage() {
		System.out.println("\t\t正在执行查询售货员操作\n");
		System.out.println("请输入要查询售货员的关键字");
		String name = scannerInfoString();
		
		ArrayList<SalesMan> salesMans = new SalesManDao().querySalesMan(name);
		if (salesMans.size() <= 0) {
			System.err.println("\t没有人员符合查询条件");
		} else {
			System.out.println("\t\t\t售货员列表\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			for (int index = 0; index < salesMans.size(); index++) {
				SalesMan salesMan = salesMans.get(index);
				System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassword());
				System.out.println();
			}
		}
		choiceSalesManNext("querySalesMan");
	}
	
	/**
	 * 5.显示所有售货员界面
	 */
	public static void displaySalesManPage() {
		ArrayList<SalesMan> salesMans = new SalesManDao().displaySalesMan();
		if (salesMans.size() <= 0) {
			System.err.println("\t售货员列表为空");
			// 返回售货员管理页
			MainPage.salesManManagementPage();
		} else {
			System.out.println("\t\t\t所有售货员列表\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码\t");
			for (int index = 0; index < salesMans.size(); index++) {
				SalesMan salesMan = salesMans.get(index);
				System.out.println("\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassword());
				System.out.println();
			}
			do {
				System.out.println("\n\n输入0返回上一级菜单");
				String choice = scannerInfoString();
				if ("0".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				System.out.println("\n\t输入错误，请重新输入");
			} while (true);
		}
	}
}

















