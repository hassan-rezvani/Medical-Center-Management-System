package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtil;

public class DoctorUser implements User {
	
	private static DoctorUser INSTANCE;
	private String id = null;
	private String firstName = null;
	private String lastName = null;
	private String dateOfBirth = null;
	private String address = null;
	private String phoneNumber = null;
	private String gender = null;
	private String department = null;
	private String userName = null;
	private String password = null;
	
	public synchronized static DoctorUser getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DoctorUser();
		}

		return INSTANCE;
	}
	
	private DoctorUser() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void updateDoctor(String newFirstName, String newLastName, String newDateOfBirth, String newAddress, String newPhoneNumber, String newGender, String newDepartment) {
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.dateOfBirth = newDateOfBirth;
		this.address = newAddress;
		this.phoneNumber = newPhoneNumber;
		this.gender = newGender;
		this.department = newDepartment;

		// Connecting to the Database
		DBUtil db = new DBUtil();
		Connection conn = db.connect();

		// SQL query
		String queryText = ""; // The SQL text.
		PreparedStatement querySt = null; // The query handle.

		queryText = "UPDATE doctor " + "SET first_name = ? ,last_name = ?, date_of_birth = ?, address = ?, phone_number = ?, gender = ?, department = ? " + "WHERE doctor_id = ?";

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
			querySt.setString(1, firstName);
			querySt.setString(2, lastName);
			querySt.setString(3, dateOfBirth);
			querySt.setString(4, address);
			querySt.setString(5, phoneNumber);
			querySt.setString(6, gender);
			querySt.setString(7, department);
			querySt.setString(8, id);
			querySt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL failed in execute");
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

}
