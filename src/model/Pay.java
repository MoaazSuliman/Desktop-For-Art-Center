package model;

public class Pay {

	private int id;
	private int student_id;
	private int money;
	private String date;

	public Pay() {
	}

	public Pay(int id, int student_id, int money, String date) {
		this.id = id;
		this.student_id = student_id;
		this.money = money;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent() {
		return student_id;
	}

	public void setStudent(int student_id) {
		this.student_id = student_id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
