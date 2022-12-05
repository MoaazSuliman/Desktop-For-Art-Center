package model;

public class Attendance {

	private int id;
	private int student_id;
	private String date;
	private int hours;

	public Attendance() {
	}

	public Attendance(int id, int student_id, String date, int hours) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.date = date;
		this.hours = hours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

}
