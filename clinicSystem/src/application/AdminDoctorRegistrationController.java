package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import util.DBUtil;

public class AdminDoctorRegistrationController implements Initializable {

	@FXML
	private ChoiceBox<String> departmentChoiceBox = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> genderChoiceBox = new ChoiceBox<String>();

	@FXML
	private TextField yearField;

	@FXML
	private TextField phoneNumberField;

	@FXML
	private TextField dayField;

	@FXML
	private Label dateMessageLabel;

	@FXML
	private Label departMessageLabel;

	@FXML
	private Label lNameMessageLabel;

	@FXML
	private Button addButton;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextArea addressTextArea;

	@FXML
	private TextField firstNameField;

	@FXML
	private Label fNameMessageLabel;

	@FXML
	private Label passwordMessageLabel;

	@FXML
	private Label genderMessageLabel;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField monthField;

	@FXML
	private Label phoneNumberMessageLabel;

	@FXML
	private Label addressMessageLabel;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField doctorIDField;

	@FXML
	private Label userNameMessageLabel;

	@FXML
	private Label idMessageLabel;

	TextFieldProperty tfProperty = new TextFieldProperty();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		departmentChoiceBox.getItems().addAll("A", "B", "C");
		genderChoiceBox.getItems().addAll("Female", "Male", "Other", "Not Defined");

		tfProperty.setMaxLimit(doctorIDField, 5);
		tfProperty.setMaxLimit(dayField, 2);
		tfProperty.setMaxLimit(monthField, 2);
		tfProperty.setMaxLimit(yearField, 4);
		tfProperty.setMaxLimit(phoneNumberField, 12);

		idMessageLabel.setText("");
		departMessageLabel.setText("");
		fNameMessageLabel.setText("");
		lNameMessageLabel.setText("");
		dateMessageLabel.setText("");
		genderMessageLabel.setText("");
		userNameMessageLabel.setText("");
		passwordMessageLabel.setText("");
		addressMessageLabel.setText("");
		phoneNumberMessageLabel.setText("");

		idMessageLabel.setTextFill(Color.web("#ff0000", 0.8)); // Set the color of Message Label to red
		departMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		fNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		lNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		dateMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		genderMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		userNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		passwordMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		addressMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		phoneNumberMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
	}

	public void addButtonOnAction() {
		boolean cond = true;

		if (!tfProperty.checkIDField(doctorIDField, idMessageLabel)) {
			cond &= false;
		}

		if (departmentChoiceBox.getValue() == null) {
			departMessageLabel.setText("Select a Department");
			cond &= false;
		} else {
			departMessageLabel.setText("");
		}

		if (!tfProperty.checkNameField(firstNameField, fNameMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkNameField(lastNameField, lNameMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkDateField(dayField, monthField, yearField, dateMessageLabel)) {
			cond &= false;
		}

		if (genderChoiceBox.getValue() == null) {
			genderMessageLabel.setText("Select a Gender");
			cond &= false;
		} else {
			genderMessageLabel.setText("");
		}

		if (!tfProperty.checkCredentialField(userNameField, userNameMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkCredentialField(passwordField, passwordMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkAddressTextArea(addressTextArea, addressMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkPhoneNumberField(phoneNumberField, phoneNumberMessageLabel)) {
			cond &= false;
		}

		if (cond) {
			// Connecting to the Database
			DBUtil db = new DBUtil();
			Connection conn = db.connect();

			// SQL query
			String queryText = ""; // The SQL text.
			PreparedStatement querySt = null; // The query handle.

			queryText = "INSERT INTO doctor(doctor_id, first_name, last_name, date_of_birth, address, phone_number, gender, department, user_name, password) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)";

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
				querySt.setString(1, "D" + doctorIDField.getText());
				querySt.setString(2, firstNameField.getText());
				querySt.setString(3, lastNameField.getText());
				String dateOfBirth = dayField.getText() + "/" + monthField.getText() + "/" + yearField.getText();
				querySt.setString(4, dateOfBirth);
				querySt.setString(5, addressTextArea.getText());
				querySt.setString(6, phoneNumberField.getText());
				querySt.setString(7, genderChoiceBox.getValue());
				querySt.setString(8, departmentChoiceBox.getValue());
				querySt.setString(9, userNameField.getText());
				querySt.setString(10, passwordField.getText());
				querySt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL failed in execute");
				System.out.println(e.toString());
				tfProperty.duplicateDoctorIDError(doctorIDField, idMessageLabel);
				return;
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

}
