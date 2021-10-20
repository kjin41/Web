package com.ssafy.ws.util;

public class DBUtil {
	
	private static DBUtil instance = new DBUtil();
	private DBUtil() {}
	
	public static DBUtil getInstance() {
		return instance;
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
