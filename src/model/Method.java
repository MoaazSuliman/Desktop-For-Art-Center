package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.PayDatabase;
import database.StudentDatabase;

public class Method {
	private  PayDatabase payDatabase = new PayDatabase();

	public String getDate() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(new Date());
	}

	public  void resizeForMoney(ArrayList<Student> students) throws SQLException {
		for (int i = 0; i < students.size() - 1; i++) {
			for (int j = i + 1; j < students.size(); j++) {
				if (payDatabase.getTotalMoneyForStudent(students.get(i).getId()) >= payDatabase
						.getTotalMoneyForStudent(students.get(j).getId()))
					deepCopy(i, j, students);
			}
		}
	}

	private  void deepCopy(int i, int j, ArrayList<Student> students) {
		Student temp = new Student();
		temp.setId(students.get(i).getId());
		temp.setFullName(students.get(i).getFullName());
		temp.setPhone(students.get(i).getPhone());
		temp.setEmail(students.get(i).getEmail());
		temp.setLevel(students.get(i).getLevel());
		temp.setDate(students.get(i).getDate());
		temp.setDateOfBirth(students.get(i).getDateOfBirth());
		temp.setGender(students.get(i).getGender());
		temp.setAddress(students.get(i).getAddress());

		students.get(i).setId(students.get(j).getId());
		students.get(i).setFullName(students.get(j).getFullName());
		students.get(i).setPhone(students.get(j).getPhone());
		students.get(i).setEmail(students.get(j).getEmail());
		students.get(i).setLevel(students.get(j).getLevel());
		students.get(i).setDate(students.get(j).getDate());
		students.get(i).setDateOfBirth(students.get(j).getDateOfBirth());
		students.get(i).setGender(students.get(j).getGender());
		students.get(i).setAddress(students.get(j).getAddress());

		students.get(j).setId(temp.getId());
		students.get(j).setFullName(temp.getFullName());
		students.get(j).setPhone(temp.getPhone());
		students.get(j).setEmail(temp.getEmail());
		students.get(j).setLevel(temp.getLevel());
		students.get(j).setDate(temp.getDate());
		students.get(j).setDateOfBirth(temp.getDateOfBirth());
		students.get(j).setGender(temp.getGender());
		students.get(j).setAddress(temp.getAddress());

	}

	public static void main(String[] args) {
//		try {
//			ArrayList<Student> students = new StudentDatabase().getAllStudentsThatAreAvialable();
//			for (int i = 0; i < students.size(); i++) {
//				System.out.println(students.get(i));
//				System.out
//						.println("his total money is " + payDatabase.getTotalMoneyForStudent(students.get(i).getId()));
//			}
//			resizeForMoney(students);
//			System.out.println("---------------------------------- after sorting");
//			for (int i = 0; i < students.size(); i++) {
//				System.out.println(students.get(i));
//				System.out
//						.println("his total money is " + payDatabase.getTotalMoneyForStudent(students.get(i).getId()));
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}
