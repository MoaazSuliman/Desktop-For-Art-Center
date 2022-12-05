package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Method;
import model.Pay;

public class PayDatabase extends DatabaseConnection {
	private int totalMoney = 0;

	public void payForStudent(int student_id, int money) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO pay (student_id , money ,date) VALUES(?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, student_id);
		s.setInt(2, money);
		String date = new Method().getDate();
		s.setString(3, date);
		s.execute();
		connection.close();
		s.close();
	}

	public ArrayList<Pay> getAllInformationForStudentPayment(int student_id) throws SQLException {
		totalMoney = 0;
		ArrayList<Pay> pays = new ArrayList<>();
		connection = createConnection();
		sql = "SELECT * FROM pay WHERE student_id=?";
		s = connection.prepareStatement(sql);
		s.setInt(1, student_id);
		result = s.executeQuery();
		while (result.next()) {
			totalMoney += result.getInt(3);
			pays.add(new Pay(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4)));
		}
		connection.close();
		s.close();
		result.close();
		return pays;

	}

	public int getTotalMoneyForStudent(int student_id) throws SQLException {
		getAllInformationForStudentPayment(student_id);
		return totalMoney;
	}

}
