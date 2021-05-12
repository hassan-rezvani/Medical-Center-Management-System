package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtil;

//public class Admin implements User {
//
//	private String adminID;
//	
//	//Constructor
//	public Admin(String userID) {
//		this.adminID = userID;
//	}
//	
//	public String getUserID() {
//		return this.adminID;
//	}
//}

public final class AdminUser implements User {

	private static AdminUser INSTANCE;
	private String id = null;
	private String userName = null;
	private String password = null;

	private AdminUser() {
	}

	public synchronized static AdminUser getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AdminUser();
		}

		return INSTANCE;
	}

	// getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void updateAdmin(String newUserName, String newPassword) {
		this.userName = newUserName;
		this.password = newPassword;

		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.

		queryText = "UPDATE admin " + "SET user_name = ? ,password = ? " + "WHERE admin_id = ?";

		// Prepare the query.
		try {
			querySt = conn.prepareStatement(queryText);
		} catch (SQLException e) {
			System.out.println("SQL failed in prepare");
			System.out.println(e.toString());
			System.exit(0);
		}

		// Execute the query
		try {
			querySt.setString(1, userName);
			querySt.setString(2, password);
			querySt.setString(3, id);
			querySt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL failed in execute");
			System.out.println(e.toString());
			System.exit(0);
		}

		// We're done with the handle.
		try {
			querySt.close();
		} catch (SQLException e) {
			System.out.print("SQL failed closing the handle.\n");
			System.out.println(e.toString());
			System.exit(0);
		}
	}

}
