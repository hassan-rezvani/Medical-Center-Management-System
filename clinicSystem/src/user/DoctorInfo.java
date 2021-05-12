package user;


public class DoctorInfo {
	
	private static DoctorInfo INSTANCE;
	private String doctorID = null;
	private String firstName = null;
	private String lastName = null;
	private String dateOfBirth = null;
	private String address = null;
	private String phoneNumber = null;
	private String gender = null;
	private String department = null;
	private String userName = null;
	private String password = null;
	/**
	 * 
	 * @param doctorID
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param address
	 * @param phoneNumber
	 * @param gender
	 * @param department
	 * @param userName
	 * @param password
	 */
	
	public synchronized static DoctorInfo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DoctorInfo();
		}

		return INSTANCE;
	}
	
	public DoctorInfo() {
	}
	
	public DoctorInfo(String doctorID, String firstName, String lastName, String dateOfBirth, String address,
			String phoneNumber, String gender, String department, String userName, String password) {
		super();
		this.doctorID = doctorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.department = department;
		this.userName = userName;
		this.password = password;
	}
	
	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
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
}
