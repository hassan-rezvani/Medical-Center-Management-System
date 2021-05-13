package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import user.DoctorInfo;
import util.DBUtil;

public class AdminDoctorViewController implements Initializable{
	
    @FXML
    private TableView<DoctorInfo> doctorsTable;

    @FXML
    private Button deleteDoctorButton;

    @FXML
    private Button addDoctorButton;

    @FXML
    private TableColumn<DoctorInfo, String> doctorLastNCol;

    @FXML
    private Button openDoctorButton;

    @FXML
    private TableColumn<DoctorInfo, String> doctorFirstNCol;

    @FXML
    private TableColumn<DoctorInfo, String> doctorIDCol;
    
    ObservableList<DoctorInfo> doctorsList;
    
    //Doctor Singelton Object
    DoctorInfo doctorInfo;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		doctorIDCol.setCellValueFactory(new PropertyValueFactory<DoctorInfo, String>("doctorID"));
		doctorFirstNCol.setCellValueFactory(new PropertyValueFactory<DoctorInfo, String>("firstName"));
		doctorLastNCol.setCellValueFactory(new PropertyValueFactory<DoctorInfo, String>("lastName"));
		
		doctorsList = FXCollections.observableArrayList();
		
		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.
		ResultSet answers = null; // A cursor.

		queryText = "SELECT * " + "FROM doctor";

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
			answers = querySt.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL failed in execute");
			System.out.println(e.toString());
			System.exit(0);
		}

		// Any answer?
		try {
			while (answers.next()) {
				doctorsList.add(new DoctorInfo(answers.getString("doctor_id"), answers.getString("first_name"), answers.getString("last_name"), answers.getString("date_of_birth"), 
						answers.getString("address"), answers.getString("phone_number"), answers.getString("gender"), answers.getString("department"), answers.getString("user_name"), answers.getString("password")));
			}
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
		
		doctorsTable.setItems(doctorsList);
	}	
	
	public void adminPortalBackIconClicked() {
		Stage adminPortalStage = (Stage) addDoctorButton.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminPortalUI.fxml"));
			Scene scene = new Scene(root);
			adminPortalStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addDoctorOnAction () {		
		Stage mainStage = (Stage) addDoctorButton.getScene().getWindow();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminDoctorRegistrationUI.fxml"));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void openDoctorButtonOnAction (ActionEvent event) {		
		Stage mainStage = (Stage) addDoctorButton.getScene().getWindow();

		try {
			doctorInfo = DoctorInfo.getInstance();
			
			if (doctorsTable.getSelectionModel().getSelectedItem() == null) {	
				return;
			}
			
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
				querySt.setString(1, doctorsTable.getSelectionModel().getSelectedItem().getDoctorID());
				answers = querySt.executeQuery();
			} catch (SQLException e) {
				System.out.println("SQL failed in execute");
				System.out.println(e.toString());
				System.exit(0);
			}
			
			// Any answer?
			try {
				doctorInfo.setDoctorID(answers.getString(1));
				doctorInfo.setFirstName(answers.getString(2));
				doctorInfo.setLastName(answers.getString(3));
				
				if (answers.getString(4) == null) {
					doctorInfo.setDateOfBirth("");
				}
				else {
					doctorInfo.setDateOfBirth(answers.getString(4));
				}
				
				if (answers.getString(5) == null) {
					doctorInfo.setAddress("");
				}
				else {
					doctorInfo.setAddress(answers.getString(5));
				}
				
				if (answers.getString(6) == null) {
					doctorInfo.setPhoneNumber("");
				}
				else {
					doctorInfo.setPhoneNumber(answers.getString(6));
				}
				
				if (answers.getString(7) == null) {
					doctorInfo.setGender("");
				}
				else {
					doctorInfo.setGender(answers.getString(7));
				}
				
				if (answers.getString(8) == null) {
					doctorInfo.setDepartment("");
				}
				else {
					doctorInfo.setDepartment(answers.getString(8));
				}
				
				doctorInfo.setUserName(answers.getString(9));
				doctorInfo.setPassword(answers.getString(10));
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
			
			Parent root = FXMLLoader.load(getClass().getResource("AdminDoctorUpdateUI.fxml"));			
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
