package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBContext {
	private static final String serverName = "DESKTOP-K0CF3TH\\SQLEXPRESS";
	private static final String dbName = "HaLo'sShop";
	private static final String portNumber = "1433";
	private static final String instance = "";// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private static final String userID = "sa";
	private static final String password = "thuyhao1506";
	private static HikariDataSource dataSource;

	static {

		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(userID);
		dataSource.setPassword(password);
		dataSource.setMaximumPoolSize(3000);
		dataSource.setMinimumIdle(100);

	}

	private DBContext() {
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getConnection());
	}

}