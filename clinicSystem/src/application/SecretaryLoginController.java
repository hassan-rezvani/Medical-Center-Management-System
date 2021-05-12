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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.SecretaryUser;
import util.DBUtil;

public class SecretaryLoginController {
	@FXML
	private TextField secretaryUsername;
	@FXML
	private TextField secretaryPassword;
	@FXML
	private Button secretaryLoginButton;
	@FXML
	private Label secretaryLoginMessage;
	
	// SecretaryUser Singleton Object
		SecretaryUser sysSecretary;
	
public void secretaryLoginButtonOnAction(ActionEvent event) {
		
		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.
		ResultSet answers = null; // A cursor.
		boolean exist = false;

		queryText = "SELECT * " + "FROM secretary " + "WHERE user_name = ? AND password = ?";

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
			querySt.setString(1, secretaryUsername.getText());
			querySt.setString(2, secretaryPassword.getText());
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
				
				//Initialize the sysSecretary
				SecretaryUser sysSecretary = SecretaryUser.getInstance();
				sysSecretary.setId(answers.getString(1));
				sysSecretary.setFirstName(answers.getString(2));
				sysSecretary.setLastName(answers.getString(3));
				sysSecretary.setDateOfBirth(answers.getString(4));
				sysSecretary.setAddress(answers.getString(5));
				sysSecretary.setPhoneNumber(answers.getString(6));
				sysSecretary.setGender(answers.getString(7));
				sysSecretary.setUserName(answers.getString(8));
				sysSecretary.setPassword(answers.getString(9));
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

		Stage secretaryLoginStage = (Stage) secretaryLoginButton.getScene().getWindow();

		try {
			if (!secretaryUsername.getText().isEmpty() && !secretaryPassword.getText().isEmpty()) {
				secretaryLoginMessage.setText("Trying to login ...");

				if (exist) {
					//
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("SecretaryPortalUI.fxml"));
					Parent root = loader.load();
					
					Scene secretaryLoginScene = new Scene(root);


					secretaryLoginStage.setScene(secretaryLoginScene);
				} else {
					secretaryLoginMessage.setText("Invalid Username and Password Combination. Please try again!");
				}
			} else {
				secretaryLoginMessage.setText("Username or Password missing!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
