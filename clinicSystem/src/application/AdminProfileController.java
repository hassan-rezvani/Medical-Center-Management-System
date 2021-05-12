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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import user.AdminUser;

public class AdminProfileController implements Initializable {
	@FXML
	private ImageView adminProfileBackIcon;
	@FXML
	private CheckBox updateToggle;
	@FXML
	private TextField adminIDField;
	@FXML
	private TextField adminUsernameField;
	@FXML
	private TextField adminPasswordField;
	@FXML
	private Button updateButton;

	// AdminUser Singleton Object
	AdminUser sysAdmin;
	
	TextFieldProperty tfProperty = new TextFieldProperty();

	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
		tfProperty.setMaxLimit(adminIDField, 6);
		
		AdminUser sysAdmin = AdminUser.getInstance();
		adminIDField.setText(sysAdmin.getId());
		adminUsernameField.setText(sysAdmin.getUserName());
		adminPasswordField.setText("**********");
		
	}
	
	public void adminProfileBackIconClicked() {
		Stage adminProfileStage = (Stage) adminProfileBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminPortalUI.fxml"));
			Scene scene = new Scene(root);
			adminProfileStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateButtonAction() {
		AdminUser sysAdmin = AdminUser.getInstance();
		sysAdmin.updateAdmin(adminUsernameField.getText(), adminPasswordField.getText());
	}

	public void updateToggleAction() {
		AdminUser sysAdmin = AdminUser.getInstance();
		if (updateToggle.isSelected()) {
			adminPasswordField.setText(sysAdmin.getPassword());
			adminUsernameField.setText(sysAdmin.getUserName());
			adminUsernameField.setDisable(false);
			adminPasswordField.setDisable(false);
			updateButton.setDisable(false);
		} else {
			adminUsernameField.setText(sysAdmin.getUserName());
			adminPasswordField.setText("**********");
			adminUsernameField.setDisable(true);
			adminPasswordField.setDisable(true);
			updateButton.setDisable(true);
		}
	}
}
