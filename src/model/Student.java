package model;

public class Student {

	private int id;
	private String fullName;
	private String phone;
	private String email;
	private String level;
	private String date;
	private String dateOfBirth;
	private String gender;
	private String address;
	private String avilable;

	public Student() {
	}

	public Student(int id, String fullName, String phone, String email, String level, String date, String dateOfBirth,
			String genedr, String address, String avilable) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.level = level;
		this.date = date;
		this.dateOfBirth = dateOfBirth;
		this.gender = genedr;
		this.address = address;
		this.avilable = avilable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String genedr) {
		this.gender = genedr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvilable() {
		return avilable;
	}

	public void setAvilable(String avilable) {
		this.avilable = avilable;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", email=" + email + ", level="
				+ level + ", date=" + date + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", address="
				+ address + ", avilable=" + avilable + "]";
	}

}
