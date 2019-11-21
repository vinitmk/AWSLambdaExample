package com.sjsuext.cloudproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	public static Connection getRemoteConnection() {

		Connection con = null;
		Properties properties = new Utilities().getProperties();
		String dbName, userName, password, hostname, port, jdbcUrl = null;

		if (properties.getProperty("RDS_HOSTNAME") != null) {
			try {
				Class.forName("org.postgresql.Driver");
				dbName = properties.getProperty("RDS_DB_NAME");
				userName = properties.getProperty("RDS_DB_USERNAME");
				password = properties.getProperty("RDS_DB_PASSWORD");
				hostname = properties.getProperty("RDS_HOSTNAME");
				port = properties.getProperty("RDS_PORT");
				jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName
						+ "&password=" + password;
				System.out.println("jdbc " + jdbcUrl);
				con = DriverManager.getConnection(jdbcUrl);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
