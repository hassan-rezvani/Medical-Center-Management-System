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
import user.DoctorUser;

public class DoctorPortalController implements Initializable {
	@FXML
	private Button profileButton;
	@FXML
	private Button calenderButton;
	@FXML
	private ImageView doctorPortalBackIcon;
	@FXML
	private Label welcomeMessage;

	// DoctorUser Singleton Object
	DoctorUser sysDoctor;

	@Override
	public void initialize(URL ursl, ResourceBundle rb) {
		// TO-DO (Welcome Message, Show Date, etc ...)
		DoctorUser sysDoctor = DoctorUser.getInstance();
		welcomeMessage.setText("Welcome " + sysDoctor.getUserName());
	}

	public void profileButtonAction(ActionEvent event) {
		Stage mainStage = (Stage) profileButton.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("DoctorProfileUI.fxml"));
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

	public void doctorPortalBackIconMouseClicked() {
		Stage doctorPortalStage = (Stage) doctorPortalBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("DoctorLoginUI.fxml"));
			Scene scene = new Scene(root);
			doctorPortalStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
