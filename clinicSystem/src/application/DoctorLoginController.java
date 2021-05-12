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
import user.DoctorUser;
import util.DBUtil;

public class DoctorLoginController {
	@FXML
	private TextField doctorUsername;
	@FXML
	private TextField doctorPassword;
	@FXML
	private Button doctorLoginButton;
	@FXML
	private Label doctorLoginMessage;
	
	 // DoctorUser Singleton Object
		DoctorUser sysDoctor;
	
public void doctorLoginButtonOnAction(ActionEvent event) {
		
		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.
		ResultSet answers = null; // A cursor.
		boolean exist = false;

		queryText = "SELECT * " + "FROM doctor " + "WHERE user_name = ? AND password = ?";

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
			querySt.setString(1, doctorUsername.getText());
			querySt.setString(2, doctorPassword.getText());
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
				
				//Initialize the sysDoctor
				DoctorUser sysDoctor = DoctorUser.getInstance();
				sysDoctor.setId(answers.getString(1));
				sysDoctor.setFirstName(answers.getString(2));
				sysDoctor.setLastName(answers.getString(3));
				sysDoctor.setDateOfBirth(answers.getString(4));
				sysDoctor.setAddress(answers.getString(5));
				sysDoctor.setPhoneNumber(answers.getString(6));
				sysDoctor.setGender(answers.getString(7));
				sysDoctor.setDepartment(answers.getString(8));
				sysDoctor.setUserName(answers.getString(9));
				sysDoctor.setPassword(answers.getString(10));
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

		Stage doctorLoginStage = (Stage) doctorLoginButton.getScene().getWindow();

		try {
			if (!doctorUsername.getText().isEmpty() && !doctorPassword.getText().isEmpty()) {
				doctorLoginMessage.setText("Trying to login ...");

				if (exist) {
					//
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("DoctorPortalUI.fxml"));
					Parent root = loader.load();
					
					Scene doctorLoginScene = new Scene(root);


					doctorLoginStage.setScene(doctorLoginScene);
				} else {
					doctorLoginMessage.setText("Invalid Username and Password Combination. Please try again!");
				}
			} else {
				doctorLoginMessage.setText("Username or Password missing!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
