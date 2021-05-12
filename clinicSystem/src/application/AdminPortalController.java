package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import user.AdminUser;

public class AdminPortalController implements Initializable{
	@FXML
	private ImageView adminPortalBackIcon;
	@FXML
	private Button adminDoctorsButton;
	@FXML
	private Button adminSecretariesButton;
	@FXML
	private Button adminProfileButton;
	@FXML
	private Button adminPatientsButton;
	@FXML
	private Label welcomeMessage;
	
	// AdminUser Singleton Object
	AdminUser sysAdmin;

	@Override	
	public void initialize(URL ursl, ResourceBundle rb) { 
		//TO-DO (Welcome Message, Show Date, etc ...)
		AdminUser sysAdmin = AdminUser.getInstance();
		welcomeMessage.setText("Welcome " + sysAdmin.getUserName());
	}
	
	public void adminPortalBackIconClicked() {
		Stage adminPortalStage = (Stage) adminPortalBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminLoginUI.fxml"));
			Scene scene = new Scene(root);
			adminPortalStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminProfileButtonAction() {
		
		Stage adminPortalStage = (Stage) adminProfileButton.getScene().getWindow();

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AdminProfileUI.fxml"));
			Parent root = loader.load();
			
			Scene adminPortalScene = new Scene(root);
			
//			AdminProfileController controller = loader.getController();
//			controller.initialUserData(sysAdmin);
			
			adminPortalStage.setScene(adminPortalScene);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminDoctorsButtonAction() {
		Stage mainStage = (Stage) adminPortalBackIcon.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminDoctorViewUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminSecretariesButtonAction() {
//		Stage mainStage = (Stage) adminPortalBackIcon.getScene().getWindow();
//
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("AdminLoginUI.fxml"));
//			Scene scene = new Scene(root);
//			mainStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void adminPatientsButtonAction() {
//		Stage mainStage = (Stage) adminPortalBackIcon.getScene().getWindow();
//
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("AdminLoginUI.fxml"));
//			Scene scene = new Scene(root);
//			mainStage.setScene(scene);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
//	public void initialUserData(ClassSingleton adminObj) {
//		sysAdmin = adminObj;
//		if (sysAdmin == null)
//			System.out.println("Error - Portal");
//		
//		//TO-DO
//	}
}
