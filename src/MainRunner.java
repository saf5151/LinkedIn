
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import DataGenerator.*;

public class MainRunner {
	public static void main(String[] args){
		String dbpath = "jdbc:h2:~/test";
		try {
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection( dbpath, "admin", "admin");
			Statement stmt = con.createStatement();
			Main.setup(stmt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
