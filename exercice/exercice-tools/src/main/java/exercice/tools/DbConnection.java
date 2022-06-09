package exercice.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties props = new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties"));

		Class.forName(props.getProperty("driver"));

		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String pwd = props.getProperty("pwd");

		return DriverManager.getConnection(url, user, pwd);
	}
}
