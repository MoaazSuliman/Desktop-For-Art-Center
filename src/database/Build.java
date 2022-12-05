package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Build {

	private static Connection connection;
	private static PreparedStatement s;
	private static String sql = "";

	public static void main(String[] args) {

		try {
			connection = new DatabaseConnection().createConnection();
			System.out.println("build main ");
			s = connection.prepareStatement(sql);
			s.execute();
			System.out.println("table added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
