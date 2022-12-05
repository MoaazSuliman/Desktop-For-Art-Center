package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Student;

public class StudentDatabase extends DatabaseConnection {

	ArrayList<Student> students = new ArrayList<Student>();

	public void insertStudent(Student student) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO student (fullName ,phone, email, level, date , dateOfBirth , gender , address ,avilable ) VALUES (?,?,?,?,?,?,?,?,?)";
		s = connection.prepareStatement(sql);
		s.setString(1, student.getFullName());
		s.setString(2, student.getPhone());
		s.setString(3, student.getEmail());
		s.setString(4, student.getLevel());
		s.setString(5, student.getDate());
		s.setString(6, student.getDateOfBirth());
		s.setString(7, student.getGender());
		s.setString(8, student.getAddress());
		s.setString(9, student.getAvilable());
		s.execute();
		connection.close();
		s.close();
	}

	public ArrayList<Student> getAllStudents() throws SQLException {
		connection = createConnection();
		sql = "SELECT * FROM student";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		while (result.next()) {
			students.add(new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getString(6), result.getString(7), result.getString(8),
					result.getString(9), result.getString(10)));
		}
		connection.close();
		s.close();
		result.close();
		return students;
	}

	public ArrayList<Student> getAllStudents(String condition) throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		connection = createConnection();
		condition = condition + "%";
		sql = "SELECT * FROM student WHERE fullName like '" + condition + "'";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		while (result.next()) {
			students.add(new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getString(6), result.getString(7), result.getString(8),
					result.getString(9), result.getString(10)));
		}
		connection.close();
		s.close();
		result.close();
		return students;
	}

	public ArrayList<Student> getAllStudentsThereAreAvilableWithCondition(String condition) throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		connection = createConnection();
		condition = condition + "%";
		sql = "SELECT * FROM student WHERE fullName like '" + condition + "' AND avilable=?";
		s = connection.prepareStatement(sql);
		s.setString(1, "متاح");
		result = s.executeQuery();
		while (result.next()) {
			students.add(new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getString(6), result.getString(7), result.getString(8),
					result.getString(9), result.getString(10)));
		}
		connection.close();
		s.close();
		result.close();
		return students;
	}

	public void deleteStudent(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE FROM student WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		connection.close();
		s.close();
	}

	public void updateStudent(Student student) throws SQLException {
		connection = createConnection();
		sql = "UPDATE student SET fullName=? , phone =? , email=? , level=? ,  dateOfBirth=? ,gender=? ,address=? , avilable=? WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setString(1, student.getFullName());
		s.setString(2, student.getPhone());
		s.setString(3, student.getEmail());
		s.setString(4, student.getLevel());
		s.setString(5, student.getDateOfBirth());
		s.setString(6, student.getGender());
		s.setString(7, student.getAddress());
		s.setString(8, student.getAvilable());
		s.setInt(9, student.getId());
		s.execute();
		connection.close();
		s.close();
	}

	public void getStudentById() {

	}

	public ArrayList<Student> getAllStudentsThatAreAvialable() throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		connection = createConnection();
		sql = "SELECT * FROM student WHERE avilable='متاح'";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		while (result.next()) {
			students.add(new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getString(6), result.getString(7), result.getString(8),
					result.getString(9), result.getString(10)));
		}
		connection.close();
		s.close();
		return students;
	}
}
