package database;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Attendance;
import model.Method;

public class AttendanceDatabase extends DatabaseConnection {

	public void addHoursForStudent(int student_id) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO attendance (student_id , date , hours) VALUES (?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, student_id);
		s.setString(2, new Method().getDate());
		s.setInt(3, 2);
		s.execute();
		connection.close();
		s.close();
	}

	public ArrayList<Attendance> getAllAttendanceForStudent(int student_id) {
		try {
			ArrayList<Attendance> attendances = new ArrayList<>();
			connection = createConnection();
			sql = "SELECT * FROM attendance WHERE student_id=?";
			s = connection.prepareStatement(sql);
			s.setInt(1, student_id);
			result = s.executeQuery();
			while (result.next()) {
				attendances.add(
						new Attendance(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4)));
			}
			connection.close();
			s.close();
			result.close();
			return attendances;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}

	}

	public int getTotalHoursForStudent(int student_id) throws SQLException {
		int totalHours = 0;
		connection = createConnection();
		sql = "SELECT hours FROM attendance WHERE student_id=?";
		s = connection.prepareStatement(sql);
		s.setInt(1, student_id);
		result = s.executeQuery();
		while (result.next())
			totalHours += 2;
		connection.close();
		s.close();
		result.close();
		return totalHours;
	}

}
