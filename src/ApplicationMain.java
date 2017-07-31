/**
 * filename: ApplicationMain.java
 * authors: Swan Ronson
 * date: 7/30/17
 *
 * The ApplicationMain class serves as the entry point into the program.  The database has been
 * been pre-built, so the SQL commands are not listed there. To see a full listing of all commands
 * used to create the tables, views, indices, users, and roles, reference the db_schema.txt file.
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import DataGenerator.*;


public class ApplicationMain extends User
{
	/**
	 * @param args: unused command-line args
	 *
     * Enters the user into a login loop where they can either log in to our database as an existing
     * user, or create a new account.
	 * A user can be either an Employee, or a Company.  Once that is determined, the user is connected
	 * to the database with their corresponding database user.
	 * Once the user is logged in, they are redirected to the main program loop (found inside the User class)
	 */
	public static void main ( String[] args ) {
		String caseChoice, user_name, password, email, isEmployee;
		Boolean isEmpB, tmp;
		isEmpB = null;
		System.out.println("\t\t\t\t\t Welcome to Swan Ronson Networking Platform");
		Scanner user_input = new Scanner(System.in);
		Scanner user_names = new Scanner(System.in);
		Scanner passwords = new Scanner(System.in);
		Scanner IsEmployee = new Scanner(System.in);
		Employee employee = new Employee();
		Employer employer = new Employer();

		String dbpath = "jdbc:h2:~/Dropbox/CSCI 320/LinkedIn/db/linkedin";

		try
		{
			// Connect to the database
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection( dbpath, "admin", "admin");
			Statement stmt = con.createStatement();
			DataGenerator.Main.setup(stmt);


			for (; ; ) {
				System.out.println("1- Login\n2- Register\n3- Help\n>>>");
				//Scanner user_input = new Scanner(System.in);
				//caseChoice = user_input.next();
				switch (Integer.parseInt(user_input.next())) {
					case 1:
						System.out.println("Email: ");
						user_name = user_names.next();
						System.out.println("Password: ");
						password = passwords.next();
						ResultSet rs = stmt.executeQuery( "SELECT * FROM User WHERE email='" + user_name + "' " +
								" AND password='" + password + "'");

						// FIXME test for no data available
						rs.next();
						// check login info
						if (user_name.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD")))
						{
							// enter user loop for the company
							if (rs.getString("TYPE").equals("Company")) {

								ResultSet personalInfo = stmt.executeQuery("SELECT companyID, Name, Email, Hiring " +
										"FROM Company WHERE Email='" + user_name + "'");
								// FIXME test for no data available
								personalInfo.next();

								employer.setCompanyID(Integer.valueOf(personalInfo.getInt("companyID")));
								employer.setName(personalInfo.getString("name"));
								employer.setEmail(user_name);
								employer.setHiring(personalInfo.getBoolean("hiring"));

								// close the current connection and reconnect with the correct user
								con.close();
								con = DriverManager.getConnection(dbpath, "Company", "Company");
								employer.setConnection(con);
								employer.userLoop(employer.getName(), String.valueOf(employer.getCompanyID()), true, con );

							}
							// type is employee
							else {
								ResultSet personalInfo = stmt.executeQuery("SELECT * FROM Employee WHERE Email='" + user_name + "'");
								// FIXME test for no data available
								personalInfo.next();

								// store some user information locally
								employee.setId(Integer.valueOf(personalInfo.getInt("UserID")));
								employee.setName(personalInfo.getString("name"));
								employee.setEmail(user_name);
								employee.setSearching(personalInfo.getBoolean("searching"));

								// close the current connection and reconnect with the correct user
								con.close();
								con = DriverManager.getConnection(dbpath, "Employee", "Employee");
								employee.setConnection(con);
								employee.userLoop(employee.getName(), String.valueOf(employee.getId()), false, con );
							}

						} else {
							// FIXME will error before getting here
						}


						break;
					case 2:
						System.out.println("Email: ");
						user_name = user_names.next();
						System.out.println("Password: ");
						password = passwords.next();
						System.out.println("Are you an Employee (y/n)");
						isEmployee = IsEmployee.next();
						if (isEmployee.equals("y") || isEmployee.equals("Y")) {
							/***
							 * Need to check if the email exist
							 */
							stmt.executeUpdate("insert into USER values('" + user_name + "', '"+ password +"', 'Employee')");
							con.close();
							con = DriverManager.getConnection(dbpath, "admin", "admin");//fix me to login with employee user
							employee.setConnection(con);
							employee.employeeRegisteration(user_name, password);
						} else {
							stmt.executeUpdate("insert into USER values('" + user_name + "', '"+ password +"', 'Company')");
							con.close();
							con = DriverManager.getConnection(dbpath, "admin", "admin");//fix me to login with Company user
							employer.setConnection(con);
							employer.employerRegisteration(user_name, password);
						}
						/*********************************
						 * Move to Employee and Employer Registration
						 */
						break;
					case 3:
						//tmp = userLoop("Aziz", "12321", false, con);
						System.out.println("Help");
						break;
				}
			}
			//user_input.close();
			//user_names.close();
			//passwords.close();
		} catch ( Exception e ) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void setInput(File input){
	}
}
