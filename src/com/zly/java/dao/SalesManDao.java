package com.zly.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zly.java.db.DbClose;
import com.zly.java.db.DbOpen;
import com.zly.java.entity.SalesMan;

/**
 * 数据库SalesMan表操作
 * @author 赵林洋
 * @version 1.0
 */
public final class SalesManDao {

	Connection connection = null;
	PreparedStatement pStatement = null;
	ResultSet rSet = null;
	
	/**
	 * 1.前台收银登陆
	 * @param sName 用户名
	 * @return ArrayList<SalesMan> sPassword,sId
	 */
	public ArrayList<SalesMan> checkstandLog(String sName) {
		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
		connection = DbOpen.getconn();
		String sql = "SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sName);
			
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				String sPassword = rSet.getString("spassword");
				int sId = rSet.getInt("sId");
				SalesMan salesMan = new SalesMan(sId, sPassword);
				salesManInfo.add(salesMan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pStatement, rSet, connection);
		}
		return salesManInfo;
	}
	
	/**
	 * 2.添加售货员
	 * @param sName 用户名
	 * @return boolean
	 */
	public boolean addSalesMan(SalesMan sName) {
		boolean b = false;
		connection = DbOpen.getconn();
		String sql = "INSERT INFO SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sName.getSName());
			pStatement.setString(2, sName.getSPassword());
			
			int rs = pStatement.executeUpdate();
			while (rs > 0) {
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
	 * 3.更改售货员信息
	 * @param key 	要更改项
	 * @param salesMan 售货员对象
	 * @return boolean
	 */
	public boolean updateSalesMan(int key, SalesMan salesMan) {
		boolean b = false;
		connection = DbOpen.getconn();
		switch (key) {
		case 1: // 3.1 更改售货员姓名
			String sqlName = "UPDATE SALESMAN SET SNAME=? WHERE SID=?";
			try {
				pStatement = connection.prepareStatement(sqlName);
				pStatement.setString(1, salesMan.getSName());
				pStatement.setInt(2, salesMan.getSId());
				int rs = pStatement.executeUpdate();
				while (rs > 0) {
					b = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.addClose(pStatement, connection);
			}
			break;
		case 2: // 3.2 更改售货员密码
			String sqlPassword = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";
			try {
				pStatement = connection.prepareStatement(sqlPassword);
				pStatement.setString(1, salesMan.getSPassword());
				pStatement.setInt(2, salesMan.getSId());
				
				int rs = pStatement.executeUpdate();
				while (rs > 0) {
					b = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.addClose(pStatement, connection);
			}
		default:
			break;
		}
		return b;
	}
	
	/**
	 * 4.删除售货员
	 * @param sName 用户名
	 * @return boolean
	 */
	public boolean deleteSalesMan(String sName) {
		boolean b = false;
		connection = DbOpen.getconn();
		String sql = "DELETE FROM SALESMAN WHERE SNAME=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sName);
			int rs = pStatement.executeUpdate();
			while (rs > 0) {
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
	 * 5.模糊查询售货员
	 * @param sName 用户名
	 * @return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> querySalesMan(String sName) {
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		connection = DbOpen.getconn();
		// 从用户处获取的字符串加上 % 符号，来达到模糊查询的目的.字符串的连接还有更优秀的方式，待优化代码！
		sName = "%" + sName + "%";
		System.out.println(sName);
		// 不能直接跟 % .只能用连接字符串的方式
		String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sName);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				int sid = rSet.getInt("sid");
				String sname = rSet.getString(2);
				String spassword = rSet.getString(3);
				
				SalesMan salesMan = new SalesMan(sid, sname, spassword);
				salesManList.add(salesMan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pStatement, rSet, connection);
		}
		return salesManList;
	}
	
	/**
	 * 6.显示所有售货员
	 * @return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> displaySalesMan() {
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		connection = DbOpen.getconn();
		String sql = "SELECT * FROM SALESMAN";
		
		try {
			pStatement = connection.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				int sId = rSet.getInt("sid");
				String sName = rSet.getString(2);
				String sPassword = rSet.getString(3);
				
				SalesMan salesMan = new SalesMan(sId, sName, sPassword);
				salesManList.add(salesMan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pStatement, rSet, connection);
		}
		return salesManList;
	}
}

























