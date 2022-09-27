package testConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class TestSQLite {

	private static final String DB_NAME = "testJava.db";
	private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
	private static final String TABLE_CONTACTS = "contacts";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_PHONE = "phone";
	private static final String COLUMN_EMAIL = "email";

	public static void main(String[] args) {
		try {

			Connection conn = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("Connected to SQlite!");
			Statement statement = conn.createStatement();

			statement.execute(MessageFormat.format("DROP TABLE IF EXISTS {0}", TABLE_CONTACTS));

			statement.execute(MessageFormat.format("CREATE TABLE IF NOT EXISTS {0} ({1} TEXT, {2} INTEGER, {3} TEXT)",
					TABLE_CONTACTS, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL));

			statement.execute(MessageFormat.format(
					"INSERT INTO {0} ({1}, {2}, {3}) VALUES(''Giovanni'', 3398698317, ''ing.giovanni.frison@gmail.com'')",
					TABLE_CONTACTS, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL));

			insertContact(statement, "Marco", 339869347, "ing.marco.frison@gmail.com");
			insertContact(statement, "Anna", 339869341, "ing.anna.frison@gmail.com");
			insertContact(statement, "Michele", 339869341, "ing.michele.frison@gmail.com");

			ResultSet resultSet = statement.executeQuery(MessageFormat.format("SELECT * FROM {0}", TABLE_CONTACTS));
			while (resultSet.next()) {
				System.out.println(MessageFormat.format("{0}, {1}, {2}", resultSet.getString(COLUMN_NAME),
						resultSet.getString(COLUMN_PHONE), resultSet.getString(COLUMN_EMAIL)));
			}

			// good practice, close the resources manually (we could also insert the
			// connection
			// in a try with resources to let java handle closing the connection
			statement.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("Can't connect: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
		statement.execute(MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}) VALUES(''{4}'', ''{5}'', ''{6}'')",
				TABLE_CONTACTS, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL, name, phone, email));
	}
}
