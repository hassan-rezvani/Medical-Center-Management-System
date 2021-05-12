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
import user.DoctorUser;

public class DoctorProfileController implements Initializable{
	@FXML
	private ImageView doctorProfileBackIcon;
	@FXML
	private CheckBox updateToggle;
	@FXML
	private TextField doctorIDField;
	@FXML
	private TextField doctorUsernameField;
	@FXML
	private TextField doctorPasswordField;
	@FXML
	private TextField doctorFirstNameField;
	@FXML
	private TextField doctorLastNameField;
	@FXML
	private TextField doctorDayOfBirthField;
	@FXML
	private TextField doctorMonthOfBirthField;
	@FXML
	private TextField doctorYearOfBirthField;
	@FXML
	private TextField doctorAddressField;
	@FXML
	private TextField doctorPhoneNumberField;
	@FXML
	private ChoiceBox<String> doctorGenderBox = new ChoiceBox<String>();
	@FXML
	private ChoiceBox<String> doctorDepartmentBox = new ChoiceBox<String>();
	@FXML
	private Button updateButton;
	
	
	// DoctorUser Singleton Object
	DoctorUser sysDoctor;

	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
		
		DoctorUser sysDoctor = DoctorUser.getInstance();
		doctorIDField.setText(sysDoctor.getId());
		doctorUsernameField.setText(sysDoctor.getUserName());
		doctorPasswordField.setText("**********");
		doctorFirstNameField.setText(sysDoctor.getFirstName());
		doctorLastNameField.setText(sysDoctor.getLastName());
		if (sysDoctor.getDateOfBirth() != null) {
			if (!sysDoctor.getDateOfBirth().isEmpty()) {
				doctorDayOfBirthField.setText(sysDoctor.getDateOfBirth().substring(0, 2));
				doctorMonthOfBirthField.setText(sysDoctor.getDateOfBirth().substring(3, 5));
				doctorYearOfBirthField.setText(sysDoctor.getDateOfBirth().substring(6, 10));
			}
		}
		doctorAddressField.setText(sysDoctor.getAddress());
		doctorPhoneNumberField.setText(sysDoctor.getPhoneNumber());
		doctorGenderBox.setValue(sysDoctor.getGender());
		doctorDepartmentBox.setValue(sysDoctor.getDepartment());
		doctorGenderBox.getItems().addAll("Female", "Male", "Other", "Not Defined");
		doctorDepartmentBox.getItems().addAll("A", "B", "C");
	}

	public void doctorProfileBackIconClicked() {
		Stage doctorProfileStage = (Stage) doctorProfileBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("DoctorPortalUI.fxml"));
			Scene scene = new Scene(root);
			doctorProfileStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateButtonAction() {
		DoctorUser sysDoctor = DoctorUser.getInstance();
		String Adress = doctorDayOfBirthField.getText() + "/" + doctorMonthOfBirthField.getText() + "/" + doctorYearOfBirthField.getText();
		sysDoctor.updateDoctor(doctorFirstNameField.getText(), doctorLastNameField.getText(), Adress, doctorAddressField.getText(), doctorPhoneNumberField.getText(), doctorGenderBox.getValue(), doctorDepartmentBox.getValue());
	}

	public void updateToggleAction() {
		DoctorUser sysDoctor = DoctorUser.getInstance();
		if (updateToggle.isSelected()) {
			doctorPasswordField.setText(sysDoctor.getPassword());
			doctorUsernameField.setText(sysDoctor.getUserName());
			doctorFirstNameField.setText(sysDoctor.getFirstName());
			doctorLastNameField.setText(sysDoctor.getLastName());
			if (sysDoctor.getDateOfBirth() != null) {
				if (!sysDoctor.getDateOfBirth().isEmpty()) {
					doctorDayOfBirthField.setText(sysDoctor.getDateOfBirth().substring(0, 2));
					doctorMonthOfBirthField.setText(sysDoctor.getDateOfBirth().substring(3, 5));
					doctorYearOfBirthField.setText(sysDoctor.getDateOfBirth().substring(6, 10));
				}
			}
			doctorAddressField.setText(sysDoctor.getAddress());
			doctorPhoneNumberField.setText(sysDoctor.getPhoneNumber());
			doctorGenderBox.setValue(sysDoctor.getGender());
			doctorDepartmentBox.setValue(sysDoctor.getDepartment());
			
			doctorFirstNameField.setDisable(false);
			doctorLastNameField.setDisable(false);
			doctorDayOfBirthField.setDisable(false);
			doctorMonthOfBirthField.setDisable(false);
			doctorYearOfBirthField.setDisable(false);
			doctorAddressField.setDisable(false);
			doctorPhoneNumberField.setDisable(false);
			doctorGenderBox.setDisable(false);
			doctorDepartmentBox.setDisable(false);
			updateButton.setDisable(false);
		} else {
			doctorFirstNameField.setDisable(true);
			doctorLastNameField.setDisable(true);
			doctorDayOfBirthField.setDisable(true);
			doctorMonthOfBirthField.setDisable(true);
			doctorYearOfBirthField.setDisable(true);
			doctorAddressField.setDisable(true);
			doctorPhoneNumberField.setDisable(true);
			doctorGenderBox.setDisable(true);
			doctorDepartmentBox.setDisable(true);
			updateButton.setDisable(true);
			
			doctorFirstNameField.setText(sysDoctor.getFirstName());
			doctorLastNameField.setText(sysDoctor.getLastName());
			if (sysDoctor.getDateOfBirth() != null) {
				if (!sysDoctor.getDateOfBirth().isEmpty()) {
					doctorDayOfBirthField.setText(sysDoctor.getDateOfBirth().substring(0, 2));
					doctorMonthOfBirthField.setText(sysDoctor.getDateOfBirth().substring(3, 5));
					doctorYearOfBirthField.setText(sysDoctor.getDateOfBirth().substring(6, 10));
				}
			}
			doctorAddressField.setText(sysDoctor.getAddress());
			doctorPhoneNumberField.setText(sysDoctor.getPhoneNumber());
			doctorGenderBox.setValue(sysDoctor.getGender());
			doctorDepartmentBox.setValue(sysDoctor.getDepartment());
			doctorPasswordField.setText("**********");
		}
	}
}
