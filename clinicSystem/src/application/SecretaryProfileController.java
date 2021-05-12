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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import user.SecretaryUser;

public class SecretaryProfileController implements Initializable{
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
	private TextField secretaryAddressField;
	@FXML
	private TextField secretaryPhoneNumberField;
	@FXML
	private ChoiceBox<String> secretaryGenderBox = new ChoiceBox<String>();
	@FXML
	private Button updateButton;
	
	

	// SecretaryUser Singleton Object
	SecretaryUser sysSecretary;


	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
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
		secretaryAddressField.setText(sysSecretary.getAddress());
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
		SecretaryUser sysSecretary = SecretaryUser.getInstance();
		String Adress = secretaryDayOfBirthField.getText() + "/" + secretaryMonthOfBirthField.getText() + "/" + secretaryYearOfBirthField.getText();
		sysSecretary.updateSecretary(secretaryFirstNameField.getText(), secretaryLastNameField.getText(), Adress, secretaryAddressField.getText(), secretaryPhoneNumberField.getText(), secretaryGenderBox.getValue());
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
			secretaryAddressField.setText(sysSecretary.getAddress());
			secretaryPhoneNumberField.setText(sysSecretary.getPhoneNumber());
			secretaryGenderBox.setValue(sysSecretary.getGender());
			
			secretaryFirstNameField.setDisable(false);
			secretaryLastNameField.setDisable(false);
			secretaryDayOfBirthField.setDisable(false);
			secretaryMonthOfBirthField.setDisable(false);
			secretaryYearOfBirthField.setDisable(false);
			secretaryAddressField.setDisable(false);
			secretaryPhoneNumberField.setDisable(false);
			secretaryGenderBox.setDisable(false);
			updateButton.setDisable(false);
		} else {
			secretaryFirstNameField.setDisable(true);
			secretaryLastNameField.setDisable(true);
			secretaryDayOfBirthField.setDisable(true);
			secretaryMonthOfBirthField.setDisable(true);
			secretaryYearOfBirthField.setDisable(true);
			secretaryAddressField.setDisable(true);
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
			secretaryAddressField.setText(sysSecretary.getAddress());
			secretaryPhoneNumberField.setText(sysSecretary.getPhoneNumber());
			secretaryGenderBox.setValue(sysSecretary.getGender());
			secretaryPasswordField.setText("**********");
		}
	}
}
