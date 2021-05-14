package application;

import java.net.URL;
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
import user.DoctorUser;
import user.SecretaryUser;

public class SecretaryProfileController implements Initializable {
	@FXML
	private ImageView secretaryProfileBackIcon;
	@FXML
	private CheckBox updateToggle;
	@FXML
	private TextField secretaryIDField;
	@FXML
	private TextField secretaryUsernameField;
	@FXML
	private TextField secretaryPasswordField;
	@FXML
	private TextField secretaryFirstNameField;
	@FXML
	private TextField secretaryLastNameField;
	@FXML
	private TextField secretaryDayOfBirthField;
	@FXML
	private TextField secretaryMonthOfBirthField;
	@FXML
	private TextField secretaryYearOfBirthField;
	@FXML
	private TextArea secretaryAddressTextArea;
	@FXML
	private TextField secretaryPhoneNumberField;

	@FXML
	private ChoiceBox<String> secretaryGenderBox = new ChoiceBox<String>();
	@FXML
	private Button updateButton;

	@FXML
	private Label idMessageLabel;

	@FXML
	private Label passwordMessageLabel;

	@FXML
	private Label fNameMessageLabel;

	@FXML
	private Label lNameMessageLabel;

	@FXML
	private Label dateMessageLabel;

	@FXML
	private Label userNameMessageLabel;

	@FXML
	private Label addressMessageLabel;

	@FXML
	private Label genderMessageLabel;

	@FXML
	private Label phoneNumberMessageLabel;

	// SecretaryUser Singleton Object
	SecretaryUser sysSecretary;

	TextFieldProperty tfProperty = new TextFieldProperty();

	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
		secretaryGenderBox.getItems().addAll("---", "Female", "Male", "Other", "Not Defined");

		tfProperty.setMaxLimit(secretaryIDField, 5);
		tfProperty.setMaxLimit(secretaryDayOfBirthField, 2);
		tfProperty.setMaxLimit(secretaryMonthOfBirthField, 2);
		tfProperty.setMaxLimit(secretaryYearOfBirthField, 4);
		tfProperty.setMaxLimit(secretaryPhoneNumberField, 12);

		idMessageLabel.setText("");
		fNameMessageLabel.setText("");
		lNameMessageLabel.setText("");
		dateMessageLabel.setText("");
		genderMessageLabel.setText("");
		userNameMessageLabel.setText("");
		passwordMessageLabel.setText("");
		addressMessageLabel.setText("");
		phoneNumberMessageLabel.setText("");

		// Set the color of Message Label to red
		idMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		fNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		lNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		dateMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		genderMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		userNameMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		passwordMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		addressMessageLabel.setTextFill(Color.web("#ff0000", 0.8));
		phoneNumberMessageLabel.setTextFill(Color.web("#ff0000", 0.8));

		SecretaryUser sysSecretary = SecretaryUser.getInstance();
		secretaryIDField.setText(sysSecretary.getId());
		secretaryUsernameField.setText(sysSecretary.getUserName());
		secretaryPasswordField.setText("**********");
		secretaryFirstNameField.setText(sysSecretary.getFirstName());
		secretaryLastNameField.setText(sysSecretary.getLastName());
		if (sysSecretary.getDateOfBirth() != null) {
			if (!sysSecretary.getDateOfBirth().isEmpty()) {
				secretaryDayOfBirthField.setText(sysSecretary.getDateOfBirth().substring(0, 2));
				secretaryMonthOfBirthField.setText(sysSecretary.getDateOfBirth().substring(3, 5));
				secretaryYearOfBirthField.setText(sysSecretary.getDateOfBirth().substring(6, 10));
			}
		}
		secretaryAddressTextArea.setText(sysSecretary.getAddress());
		secretaryPhoneNumberField.setText(sysSecretary.getPhoneNumber());
		secretaryGenderBox.setValue(sysSecretary.getGender());
		secretaryGenderBox.getItems().addAll("Female", "Male", "Other", "Not Defined");
	}

	public void secretaryProfileBackIconClicked() {
		Stage secretaryProfileStage = (Stage) secretaryProfileBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SecretaryPortalUI.fxml"));
			Scene scene = new Scene(root);
			secretaryProfileStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateButtonAction() {
		boolean cond = true;

		if (!tfProperty.checkNameField(secretaryFirstNameField, fNameMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkNameField(secretaryLastNameField, lNameMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkDateField(secretaryDayOfBirthField, secretaryMonthOfBirthField, secretaryYearOfBirthField,
				dateMessageLabel)) {
			cond &= false;
		}

		if (secretaryGenderBox.getValue() == null) {
			genderMessageLabel.setText("Select a Gender");
			cond &= false;
		} else {
			genderMessageLabel.setText("");
		}

		if (!tfProperty.checkAddressTextArea(secretaryAddressTextArea, addressMessageLabel)) {
			cond &= false;
		}

		if (!tfProperty.checkPhoneNumberField(secretaryPhoneNumberField, phoneNumberMessageLabel)) {
			cond &= false;
		}

		if (cond) {
			SecretaryUser sysSecretary = SecretaryUser.getInstance();
			String datOfBirth = null;
			if (!secretaryDayOfBirthField.getText().isEmpty() && !secretaryMonthOfBirthField.getText().isEmpty()
					&& !secretaryYearOfBirthField.getText().isEmpty()) {
				datOfBirth = secretaryDayOfBirthField.getText() + "/" + secretaryMonthOfBirthField.getText() + "/"
						+ secretaryYearOfBirthField.getText();
			}
			sysSecretary.updateSecretary(secretaryFirstNameField.getText(), secretaryLastNameField.getText(),
					datOfBirth.isEmpty() ? null : datOfBirth,
					secretaryAddressTextArea.getText().isEmpty() ? null : secretaryAddressTextArea.getText(),
					secretaryPhoneNumberField.getText().isEmpty() ? null : secretaryPhoneNumberField.getText(),
					secretaryGenderBox.getValue().isEmpty() || secretaryGenderBox.getValue().equals("---") ? null : secretaryGenderBox.getValue());
		}
	}

	public void updateToggleAction() {
		SecretaryUser sysSecretary = SecretaryUser.getInstance();
		if (updateToggle.isSelected()) {
			secretaryPasswordField.setText(sysSecretary.getPassword());
			secretaryUsernameField.setText(sysSecretary.getUserName());
			secretaryFirstNameField.setText(sysSecretary.getFirstName());
			secretaryLastNameField.setText(sysSecretary.getLastName());
			if (sysSecretary.getDateOfBirth() != null) {
				if (!sysSecretary.getDateOfBirth().isEmpty()) {
					secretaryDayOfBirthField.setText(sysSecretary.getDateOfBirth().substring(0, 2));
					secretaryMonthOfBirthField.setText(sysSecretary.getDateOfBirth().substring(3, 5));
					secretaryYearOfBirthField.setText(sysSecretary.getDateOfBirth().substring(6, 10));
				}
			}
			secretaryAddressTextArea.setText(sysSecretary.getAddress());
			secretaryPhoneNumberField.setText(sysSecretary.getPhoneNumber());
			secretaryGenderBox.setValue(sysSecretary.getGender());

			secretaryFirstNameField.setDisable(false);
			secretaryLastNameField.setDisable(false);
			secretaryDayOfBirthField.setDisable(false);
			secretaryMonthOfBirthField.setDisable(false);
			secretaryYearOfBirthField.setDisable(false);
			secretaryAddressTextArea.setDisable(false);
			secretaryPhoneNumberField.setDisable(false);
			secretaryGenderBox.setDisable(false);
			updateButton.setDisable(false);
		} else {
			secretaryFirstNameField.setDisable(true);
			secretaryLastNameField.setDisable(true);
			secretaryDayOfBirthField.setDisable(true);
			secretaryMonthOfBirthField.setDisable(true);
			secretaryYearOfBirthField.setDisable(true);
			secretaryAddressTextArea.setDisable(true);
			secretaryPhoneNumberField.setDisable(true);
			secretaryGenderBox.setDisable(true);
			updateButton.setDisable(true);
			secretaryFirstNameField.setText(sysSecretary.getFirstName());
			secretaryLastNameField.setText(sysSecretary.getLastName());
			if (sysSecretary.getDateOfBirth() != null) {
				if (!sysSecretary.getDateOfBirth().isEmpty()) {
					secretaryDayOfBirthField.setText(sysSecretary.getDateOfBirth().substring(0, 2));
					secretaryMonthOfBirthField.setText(sysSecretary.getDateOfBirth().substring(3, 5));
					secretaryYearOfBirthField.setText(sysSecretary.getDateOfBirth().substring(6, 10));
				}
			}
			secretaryAddressTextArea.setText(sysSecretary.getAddress());
			secretaryPhoneNumberField.setText(sysSecretary.getPhoneNumber());
			secretaryGenderBox.setValue(sysSecretary.getGender());
			secretaryPasswordField.setText("**********");
		}
	}
}
