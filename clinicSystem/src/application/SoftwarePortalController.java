package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SoftwarePortalController {
	@FXML
	private Button adminIcon;
	@FXML
	private Button doctorIcon;
	@FXML
	private Button secretaryIcon;


	public void adminOnMouseClicked(ActionEvent event) {
		Stage mainStage = (Stage) adminIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminLoginUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doctorOnMouseClicked(ActionEvent event) {
		Stage mainStage = (Stage) adminIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("DoctorLoginUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void secretaryOnMouseClicked(ActionEvent event) {
		Stage mainStage = (Stage) secretaryIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SecretaryLoginUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
