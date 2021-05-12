package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public Connection connect() {
		// SQLite connection string
		File directory = new File("./");
		String path = directory.getAbsolutePath().toString().replace('\\', '/');
		String dbFile = "clinicDatabase.db";
		String url = "jdbc:sqlite:" + path.substring(0, path.length() - 1) + "lib/" + dbFile;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connected!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		db.connect();
	}
}
