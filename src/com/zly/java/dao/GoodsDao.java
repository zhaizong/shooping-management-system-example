package com.zly.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zly.java.db.DbClose;
import com.zly.java.db.DbOpen;
import com.zly.java.entity.Goods;
import com.zly.java.tools.ScannerChoice;

/**
 * 数据库goods表操作
 * @author 赵林洋
 */
public final class GoodsDao {
	
	Connection connection = null;
	PreparedStatement pStatement = null;
	ResultSet rSet = null;
	
	/**
	 * 1.添加商品到数据库goods表
	 * @param goods 商品对象
	 * @return boolean
	 */
	public boolean addGoods(Goods goods) {
		boolean b = false;
		connection = DbOpen.getconn();
		String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, goods.getGname());
			pStatement.setDouble(2, goods.getGprice());
			pStatement.setInt(3, goods.getGnum());
			
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
	
	/**
	 * 2.更改商品信息到数据库goods表
	 * @param key   选择要更改商品信息
	 * @param goods 商品对象
	 * @return boolean
	 */
	public boolean updateGoods(int key, Goods goods) {
		boolean bool = false;
		connection = DbOpen.getconn();
		switch (key) {
		case 1: // key == 1, 更改商品名称
			String sqlName = "UPDATE GOODS SET GNAME=? WHERE GID=?";
			try {
				pStatement = connection.prepareStatement(sqlName);
				pStatement.setString(1, goods.getGname());
				pStatement.setInt(2, goods.getGid());
				int rs = pStatement.executeUpdate();
				if (rs > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DbClose.addClose(pStatement, connection);
			}
			break;
		case 2: // key == 2, 更改商品价格
			String sqlPrice = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
			try {
				pStatement = connection.prepareStatement(sqlPrice);
				pStatement.setDouble(1, goods.getGprice());
				pStatement.setInt(2, goods.getGid());
				
				int rSet = pStatement.executeUpdate();
				if (rSet > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.addClose(pStatement, connection);
			}
			break;
		case 3: // key == 3, 更改商品数量
			String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";
			try {
				pStatement = connection.prepareStatement(sqlNum);
				pStatement.setInt(1, goods.getGnum());
				pStatement.setInt(2, goods.getGid());
				
				int rSet = pStatement.executeUpdate();
				if (rSet > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.addClose(pStatement, connection);
			}
		default:
			break;
		}
		return bool;
	}
	
	/**
	 * 3.从数据库goods表中-刪除商品
	 * @param gid 商品编号
	 * @return boolean
	 */
	public boolean deleteGoods(int gid) {
		boolean bool = false;
		connection = DbOpen.getconn();
		String sql = "DELETE FROM GOODS WHERE GID=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, gid);
			int rSet = pStatement.executeUpdate();
			if (rSet > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.addClose(pStatement, connection);
		}
		return bool;
	}
	
	/**
	 *4.查询商品信息
	 * @param key 查询方式
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> queryGoods(int key) {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		connection = DbOpen.getconn();
		
		switch (key) {
		case 1: // key == 1 商品 数量 升序查询
			String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
			try {
				pStatement = connection.prepareStatement(sqlGnum);
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
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pStatement, rSet, connection);
			}
			break;
		case 2: // key == 2 商品 价格 升序查询
			String sqlGprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
			try {
				pStatement = connection.prepareStatement(sqlGprice);
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
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pStatement, rSet, connection);
			}
		case 3: // key == 3 商品 关键字 查询
			String nameGet = ScannerChoice.scannerInfoString();
			String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE '%'||?||'%'";
			try {
				pStatement = connection.prepareStatement(sqlGname);
				pStatement.setString(1, nameGet);
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
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pStatement, rSet, connection);
			}
		default:
			break;
		}
		return goodsList;
	}
	
	/**
	 *5.显示所有商品信息
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> displayGoods() {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		connection = DbOpen.getconn();
		String sql = "SELECT * FROM GOODS";
		
		try {
			pStatement = connection.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				int gid = rSet.getInt("gid");
				String gname = rSet.getString(2);
				double gprice = rSet.getDouble("gprice"); // 双引号+主键名，也可用数字表示
				int gnum = rSet.getInt(4);
				
				Goods goods = new Goods(gid, gname, gprice, gnum); // 使用构造函数创建Goods对象
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pStatement, rSet, connection);
		}
		return goodsList;
	}
}











