package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import user.SecretaryUser;

public class SecretaryPortalController implements Initializable {
	@FXML
	private Button profileButton;
	@FXML
	private Button calenderButton;
	@FXML
	private ImageView secretaryPortalBackIcon;
	@FXML
	private Label welcomeMessage;

	// SecretaryUser Singleton Object
	SecretaryUser sysSecretary;

	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
		SecretaryUser sysSecretary = SecretaryUser.getInstance();
		welcomeMessage.setText("Welcome " + sysSecretary.getUserName());
	}

	public void profileButtonAction(ActionEvent event) {
		Stage mainStage = (Stage) profileButton.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SecretaryProfileUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void calenderButtonAction(ActionEvent event) {
//		Stage mainStage = (Stage) profileButton.getScene().getWindow();
//
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource(""));
//			Scene scene = new Scene(root);
//			mainStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void secretaryPortalBackIconMouseClicked() {
		Stage secretaryPortalStage = (Stage) secretaryPortalBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SecretaryLoginUI.fxml"));
			Scene scene = new Scene(root);
			secretaryPortalStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
