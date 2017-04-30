package com.zly.java.page;

import java.util.ArrayList;

import com.zly.java.dao.GoodsSalesDao;
import com.zly.java.entity.GoodsSales;
import com.zly.java.tools.ScannerChoice;

/**
 * 当日卖出商品列表界面
 * @author 赵林洋
 * @version 1.0
 */
public final class GoodsSalesPage {

	public static void dailySaleGoodsPage() {
		System.out.println("\t正在列出当日售出商品列表");
		// 当日售出商品数组
		ArrayList<GoodsSales> goodsSalesList = new GoodsSalesDao().dailyGoodsSales();
		if (goodsSalesList.size() <= 0) {
			System.err.println("\t!! 今日无商品售出!");
			MainPage.commodityManagementPage();
		} else {
			System.out.println("\t\t\t\t今日售出商品列表\n");
			System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");
			for (int index = 0; index < goodsSalesList.size(); index++) {
				// 获取售出商品
				GoodsSales goodsSales = goodsSalesList.get(index);
				System.out.println("\t" + goodsSales.getGName() + "\t\t" + goodsSales.getGPrice() + "$\t\t" + goodsSales.getGNum() + "\t\t" + goodsSales.getAllSalesNum());
				int gNum = goodsSales.getGNum();
				if (gNum == 0) {
					System.out.println("\t\t该商品已售空");
				} else if (gNum < 10) {
					System.out.println("\t\t该商品已不足10件");
				} else {
					System.out.println("\t\t-");
				}
				System.out.println("\t");
			}
			do {
				System.out.println("\n\n输入0返回上一级菜单");
				String choice = ScannerChoice.scannerInfoString();
				if ("0".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				MainPage.commodityManagementPage();
			} while (true);
		}
	}
}

















