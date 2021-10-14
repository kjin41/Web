package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class DBUtil {
// 전부 context.xml로 보냄. : connecion pull
//	private final String driverName = "com.mysql.cj.jdbc.Driver";
//	private final String url = "jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC";
//	private final String user = "ssafy";
//	private final String pass = "ssafy";

	private static DBUtil instance = new DBUtil();

	private DBUtil() {
//		try {
//			Class.forName(driverName);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	public static DBUtil getInstance() {
		return instance;
	}

//	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(url, user, pass);
//	}

	public Connection getConnection() throws SQLException {
		try {
			Context ictx=new InitialContext();
			Context ctx=(Context) ictx.lookup("java:comp/env");
			DataSource ds=(DataSource) ctx.lookup("jdbc/ssafy");
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(AutoCloseable... closeables) {
		for (AutoCloseable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
