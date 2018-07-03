package unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlDataBase {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/springmvc?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	private static Connection connection;

	public static Connection getConnct() throws SQLException {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;

	}

	public static void getClose(Connection c, java.sql.Statement s, ResultSet r)
			throws SQLException {
		if (c != null) {
			c.close();
		}
		if (s != null) {
			s.close();
		}
		if (r != null) {
			r.close();
		}

	}

}
