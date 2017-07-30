package DataGenerator;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class Entry {
	public abstract String value();
	public abstract String getTable();
	public void addValue(Statement stmt){
		try {
			stmt.executeUpdate("insert into "+ getTable() + " values " + value());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
