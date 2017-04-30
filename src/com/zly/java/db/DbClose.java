package com.zly.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 关闭操作数据库时产生的资源流
 * @author 赵林洋
 */
public final class DbClose {

	/**
	 * 关闭 添加功能 资源
	 * @param pstmt,rs,conn
	 */
	public static void addClose(PreparedStatement pStatement, Connection connection) {
		/*
		 * 多个 try-catch 出发点：安全
		 */
		try {
			if (pStatement != null) {
				pStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭资源
	 * @param pstmt,rs,conn
	 */
	public static void queryClose(PreparedStatement pStatement, ResultSet rSet, Connection connection) {
		try {
			if (pStatement != null) {
				pStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rSet != null) {
				rSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}











