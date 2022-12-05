package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public class UserDatabase extends DatabaseConnection {
	private ArrayList<User> users = new ArrayList<>();
	private boolean flag;

	// check if user in database
	public boolean checkUser(String username, String password) throws SQLException {
		connection = createConnection();
		sql = "select username, password from user WHERE username =? AND password=?";
		flag = false;
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		s.setString(2, password);
		result = s.executeQuery();
		if (result.next())
			flag = true;
		connection.close();
		s.close();
		result.close();
		return flag;

	}

	// get question by unique username
	public String getQuestion(String username) throws SQLException {
		connection = createConnection();
		String question = "";
		sql = "select question from user WHERE username=?";
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		result = s.executeQuery();
		if (result.next())
			question = result.getString("question");
		connection.close();
		s.close();
		result.close();
		return question;
	}

	// get answer for username
	public String getAnswer(String username) throws SQLException {
		connection = createConnection();
		sql = "select answer from user WHERE username=?";
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		String answer = "";
		result = s.executeQuery();
		if (result.next())
			answer = result.getString("answer");
		connection.close();
		s.close();
		result.close();
		return answer;
	}

	// send new password
	public void updatePassword(String username, String password) throws SQLException {
		connection = createConnection();
		sql = "UPDATE   user SET password = ? WHERE username=?";
		s = connection.prepareStatement(sql);
		s.setString(1, password);
		s.setString(2, username);
		s.execute();
		connection.close();
		s.close();
	}

	// update User
	public void updateUser(int id, String username, String password, String phone, String question, String answer)
			throws SQLException {
		connection = createConnection();
		sql = "UPDATE user SET username=?,password=?,phone=?,question=?,answer=? WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		s.setString(2, password);
		s.setString(3, phone);
		s.setString(4, question);
		s.setString(5, answer);
		s.setInt(6, id);
		s.execute();
		connection.close();
		s.close();
	}

	// delete user
	public void delete(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE from user WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		connection.close();
		s.close();
	}

	// restart this table

}
