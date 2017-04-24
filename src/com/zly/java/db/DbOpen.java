package com.zly.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接oracle数据库
 * @author 赵林洋
 */
public final class DbOpen {
	
	public static Connection getconn() {
		Connection connection = null;
		
		String user = "sensei";
		String password = "zly";
		// orcl为oracle数据库实例名字
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		return connection;
	}
}
