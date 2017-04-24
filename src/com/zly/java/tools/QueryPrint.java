package com.zly.java.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zly.java.db.DbOpen;
import com.zly.java.entity.Goods;

/**
 * 查询&&打印 函数工具(后期优化可能会删)
 *@author 赵林洋
 */
public final class QueryPrint {

	Connection connection = null;
	PreparedStatement pStatement = null;
	ResultSet rSet = null;
	
	/**
	 * 模糊查询函数小工具
	 * @return int 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
	 * 					  
	 */
	public static int querySettlement() {
		int gid = -1;
		return gid;
	}
	
	/**
	 * 模糊查询并陈列查询信息函数小工具
	 * @param oper 调用者
	 * @return 查询到的信息的gid,如果返回值等于-1，则代表查询异常。	  
	 */
	public static int query(String oper) {
		int gid = -1;
		String shopping = ScannerChoice.scannerInfoString(); // 键盘获取商品名字
		ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(-1, shopping);
		if (goodsList == null || goodsList.size() <= 0) {
			System.err.println("\t !! 查无此商品 !!");
			// 调用选择下一步函数
			ScannerChoice.changedInfoNext(oper);
		} else {
			Goods goods = goodsList.get(0);
			System.out.println("\t\t\t\t\t\t商品列表\n\n");
			System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
			System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
			if (goods.getGnum() == 0) {
				System.out.println("\t\t该商品已售空");
			} else if (goods.getGnum() < 10) {
				System.out.println("\t\t该商品已不足10件");
			} else {
				System.out.println("\t\t-");
			}
			gid = goods.getGid(); // 将商品编号返回给调用者
		}
		return gid;
	}
	
	/**
	 * 根据商品 gid or gName查询商品
	 * @param 商品id,商品名称
	 * @return 商品信息
	 */
	public ArrayList<Goods> queryGoodsKey(int gId, String gName) {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		connection = DbOpen.getconn();
		
		String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, gId);
			pStatement.setString(2, gName);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				int gid = rSet.getInt("gid");
				String gname = rSet.getString(2);
				double gprice = rSet.getDouble(3);
				int gnum = rSet.getInt(4);
				
				Goods goods = new Goods(gid, gname, gprice, gnum);
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// TODO: dbclose
		}
		return goodsList;
	}
}

