package com.zly.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zly.java.db.DbClose;
import com.zly.java.db.DbOpen;
import com.zly.java.entity.GoodsSales;

/**
 * 数据库GoodsSale表操作
 * @author 赵林洋
 * @version 1.0
 */
public final class GoodsSalesDao {

	Connection connection = null;
	PreparedStatement pStatement = null;
	ResultSet rSet = null;
	
	/**
	 * 1.当天卖出的商品
	 * @return ArrayList<GoodsSales> 商品信息,包括 allSum (单种商品的销售总和)
	 */
	public ArrayList<GoodsSales> dailyGoodsSales() {
		ArrayList<GoodsSales> goodsSalesList = new ArrayList<GoodsSales>();
		connection = DbOpen.getconn();
		
		// 售卖时间=当前时间 trunc(sdate) =trunc(sysdate) 单位：天
		// sql语句解释见files/sql/java_sql.sql
		String sql = "select gname, gprice, gnum, allSalesNum from goods, (select gid as salesid, sum(snum) as allSalesNum from goodssales where trunc(sdate) =trunc(sysdate) group by gid) where gid = salesid";
		try {
			pStatement = connection.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				String gName = rSet.getString(1);
				double gPrice = rSet.getDouble(2);
				int gNum = rSet.getInt(3);
				int allSalesNum = rSet.getInt("allSalesNum");
				
				GoodsSales goodsSales = new GoodsSales(gName, gPrice, gNum, allSalesNum);
				goodsSalesList.add(goodsSales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pStatement, rSet, connection);
		}
		return goodsSalesList;
	}
	
	/**
	 *2.购物结算-向sales表中插入商品数据！
	 *@param goodsSales 售卖商品对象
	 *@return boolean
	 */
	public boolean shoppingSettlement(GoodsSales gSales) {
		boolean b = false;
		connection = DbOpen.getconn();
		String sql = "SELECT INTO GOODSSALES(GID,SID,SNUM) VALUES(?,?,?)";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, gSales.getGId());
			pStatement.setInt(2, gSales.getSId());
			pStatement.setInt(3, gSales.getSNum());
			
			int rs = pStatement.executeUpdate();
			if (rs > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.addClose(pStatement, connection);
		}
		return b;
	}
}
