package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.AdminUser;
import util.DBUtil;

public class AdminLoginController {

	@FXML
	private TextField adminUsername;
	@FXML
	private TextField adminPassword;
	@FXML
	private Button adminLoginButton;
	@FXML
	private Label adminLoginMessage;
	
	// AdminUser Singleton Object
	AdminUser sysAdmin;

	public void adminLoginButtonOnAction(ActionEvent event) {
		
		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.
		ResultSet answers = null; // A cursor.
		boolean exist = false;

		queryText = "SELECT * " + "FROM admin " + "WHERE user_name = ? AND password = ?";

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
			querySt.setString(1, adminUsername.getText());
			querySt.setString(2, adminPassword.getText());
			answers = querySt.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL failed in execute");
			System.out.println(e.toString());
			System.exit(0);
		}

		// Any answer?
		try {
			if (answers.next()) {
				exist = true;
				
				//Initialize the sysAdmin
				AdminUser sysAdmin = AdminUser.getInstance();
				sysAdmin.setId(answers.getString(1));
				sysAdmin.setUserName(answers.getString(2));
				sysAdmin.setPassword(answers.getString(3));
			} else {
				exist = false;
			}
		} catch (SQLException e) {
			System.out.println("SQL failed in cursor.");
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

		Stage adminLoginStage = (Stage) adminLoginButton.getScene().getWindow();

		try {
			if (!adminUsername.getText().isEmpty() && !adminPassword.getText().isEmpty()) {
				adminLoginMessage.setText("Trying to login ...");

				if (exist) {
					//
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("AdminPortalUI.fxml"));
					Parent root = loader.load();
					
					Scene adminLoginScene = new Scene(root);
					
//					AdminPortalController controller = loader.getController();
//					controller.initialUserData(sysAdmin);

					adminLoginStage.setScene(adminLoginScene);
				} else {
					adminLoginMessage.setText("Invalid Username and Password Combination. Please try again!");
				}
			} else {
				adminLoginMessage.setText("Username or Password missing!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}