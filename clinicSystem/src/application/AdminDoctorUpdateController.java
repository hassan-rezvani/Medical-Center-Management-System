package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import user.DoctorInfo;
import user.DoctorUser;
import util.DBUtil;

public class AdminDoctorUpdateController implements Initializable {

    @FXML
    private ChoiceBox<String> departmentChoiceBox;

    @FXML
    private TextField yearField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField dayField;

    @FXML
    private Label dateMessageLabel;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

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
    private ImageView adminPortalBackIcon;

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
    
    @FXML
    private Button updateButton;
    
    @FXML 
    private CheckBox updateToggle;
    
    TextFieldProperty tfProperty = new TextFieldProperty();
    
    DoctorInfo doctorInfo;
    
	// DoctorUser Singleton Object
	DoctorUser sysDoctor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		departmentChoiceBox.getItems().addAll("A", "B", "C");
		genderChoiceBox.getItems().addAll("Female", "Male", "Other", "Not Defined");

		tfProperty.setMaxLimit(doctorIDField, 6);
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
		
		// TODO Auto-generated method stub
		doctorInfo = DoctorInfo.getInstance();
		sysDoctor = DoctorUser.getInstance();
		
		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();
		
		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.
		ResultSet answers = null; // A cursor.
		
		queryText = "SELECT * " + "FROM doctor " + "WHERE doctor_id = ?";
		
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
			querySt.setString(1, doctorInfo.getDoctorID());
			answers = querySt.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL failed in execute");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// Any answer?
		try {
			
			doctorIDField.setText(answers.getString(1));
			sysDoctor.setId(doctorIDField.getText());
			
			firstNameField.setText(answers.getString(2));
			sysDoctor.setFirstName(firstNameField.getText());
			
			lastNameField.setText(answers.getString(3));
			sysDoctor.setLastName(lastNameField.getText());
			
			String[] dateOfBirth = answers.getString(4).split("/");
			dayField.setText(dateOfBirth[0]);
			monthField.setText(dateOfBirth[1]);
			yearField.setText(dateOfBirth[2]);
			sysDoctor.setDateOfBirth(dayField.getText() + "/" + monthField.getText() + "/" + yearField.getText());
			
			addressTextArea.setText(answers.getString(5));
			sysDoctor.setAddress(addressTextArea.getText());
			
			phoneNumberField.setText(answers.getString(6));
			sysDoctor.setPhoneNumber(phoneNumberField.getText());
			
			genderChoiceBox.setValue(answers.getString(7));
			sysDoctor.setGender(genderChoiceBox.getValue());
			
			departmentChoiceBox.setValue(answers.getString(8));
			sysDoctor.setDepartment(departmentChoiceBox.getValue());
			
			userNameField.setText(answers.getString(9));
			sysDoctor.setUserName(userNameField.getText());
			
			passwordField.setText("**********");
			sysDoctor.setPassword(answers.getString(10));
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
	}
    
	public void updateButtonAction () {
		DoctorUser sysDoctor = DoctorUser.getInstance();
		String dateOfBirth = dayField.getText() + "/" + monthField.getText() + "/" + yearField.getText();
		sysDoctor.setId(doctorIDField.getText());
		sysDoctor.updateDoctor(firstNameField.getText(), lastNameField.getText(), dateOfBirth, addressTextArea.getText(), phoneNumberField.getText(), genderChoiceBox.getValue(), departmentChoiceBox.getValue());
	}

	public void updateToggleAction () {
		DoctorUser sysDoctor = DoctorUser.getInstance();
		if (updateToggle.isSelected()) {
			passwordField.setText(sysDoctor.getPassword());
			userNameField.setText(sysDoctor.getUserName());
			firstNameField.setText(sysDoctor.getFirstName());
			lastNameField.setText(sysDoctor.getLastName());
			if (sysDoctor.getDateOfBirth() != null) {
				if (!sysDoctor.getDateOfBirth().isEmpty()) {
					dayField.setText(sysDoctor.getDateOfBirth().substring(0, 2));
					monthField.setText(sysDoctor.getDateOfBirth().substring(3, 5));
					yearField.setText(sysDoctor.getDateOfBirth().substring(6, 10));
				}
			}
			addressTextArea.setText(sysDoctor.getAddress());
			phoneNumberField.setText(sysDoctor.getPhoneNumber());
			genderChoiceBox.setValue(sysDoctor.getGender());
			departmentChoiceBox.setValue(sysDoctor.getDepartment());
			
			firstNameField.setDisable(false);
			firstNameField.setEditable(true);
			
			lastNameField.setDisable(false);
			lastNameField.setEditable(true);
			
			dayField.setDisable(false);
			dayField.setEditable(true);
			
			monthField.setDisable(false);
			monthField.setEditable(true);
			
			yearField.setDisable(false);
			yearField.setEditable(true);
			
			addressTextArea.setDisable(false);
			addressTextArea.setEditable(true);
			
			phoneNumberField.setDisable(false);
			phoneNumberField.setEditable(true);
			
			userNameField.setDisable(false);
			userNameField.setEditable(true);
			
			passwordField.setDisable(false);
			passwordField.setEditable(true);
			
			genderChoiceBox.setDisable(false);
			departmentChoiceBox.setDisable(true);
			updateButton.setDisable(false);
		} else {
			firstNameField.setDisable(true);
			firstNameField.setEditable(false);
			
			lastNameField.setDisable(true);
			lastNameField.setEditable(false);
			
			dayField.setDisable(true);
			dayField.setEditable(false);
			
			monthField.setDisable(true);
			monthField.setEditable(false);
			
			yearField.setDisable(true);
			yearField.setEditable(false);
			
			addressTextArea.setDisable(true);
			addressTextArea.setEditable(false);
			
			phoneNumberField.setDisable(true);
			phoneNumberField.setEditable(false);
			
			userNameField.setDisable(true);
			userNameField.setEditable(false);
			
			passwordField.setDisable(true);
			passwordField.setEditable(false);
			
			genderChoiceBox.setDisable(true);
			departmentChoiceBox.setDisable(true);
			updateButton.setDisable(true);
			
			firstNameField.setText(sysDoctor.getFirstName());
			lastNameField.setText(sysDoctor.getLastName());
			if (sysDoctor.getDateOfBirth() != null) {
				if (!sysDoctor.getDateOfBirth().isEmpty()) {
					dayField.setText(sysDoctor.getDateOfBirth().substring(0, 2));
					monthField.setText(sysDoctor.getDateOfBirth().substring(3, 5));
					yearField.setText(sysDoctor.getDateOfBirth().substring(6, 10));
				}
			}
			addressTextArea.setText(sysDoctor.getAddress());
			phoneNumberField.setText(sysDoctor.getPhoneNumber());
			genderChoiceBox.setValue(sysDoctor.getGender());
			departmentChoiceBox.setValue(sysDoctor.getDepartment());
			passwordField.setText("**********");
		}
	}

	public void adminPortalBackIconClicked() {
		Stage doctorPortalStage = (Stage) updateButton.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminDoctorViewUI.fxml"));
			Scene scene = new Scene(root);
			doctorPortalStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
