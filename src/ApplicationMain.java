//package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ApplicationMain extends User
{
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
		//User user = new User();

		String dbpath = "jdbc:h2:~/Dropbox/CSCI 320/LinkedIn/db/linkedin";

		try
		{
			// Connect to the database
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection( dbpath, "admin", "admin");
			Statement stmt = con.createStatement();

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
								employer.userLoop(employer.getName(), String.valueOf(employer.getCompanyID()), true);

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
								employee.userLoop(employee.getName(), String.valueOf(employee.getId()), false);
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
							//System.out.println("True");
							employee.employeeRegisteration(user_name, password);
						} else {
							//System.out.println("False");
							employer.employerRegisteration(user_name, password);
						}

						/*********************************
						 * Move to Employee and Employer Registration
						 */
						break;
					case 3:
						tmp = userLoop("Aziz", "12321", false);
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
        /*Connection con = null;

        try
        {
            // Connect to the database
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection( "jdbc:h2:file:C:/Users/Aziz/OneDrive/Documents/workspace/Linkedin/db/linkedin", "admin", "admin" );
            Statement stmt = con.createStatement();

            // Below are a few example queries that will be used in functions in the fully-implemented release
            // Get a list of all companies that are currently hiring and print out their information
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Company WHERE hiring='true' ORDER BY name ");
            while ( rs.next() )
            {
                int companyID = rs.getInt( "companyID" );
                String name = rs.getString( "name" );
                String desc = rs.getString( "description" );
                System.out.println( "ID: " + companyID + ", Name: " + name + ", Description: " + desc );
            }

            // a user updates their profile to indicate they are no longer searching for a job
            int userID = 3; // this will be pulled from the database when the user logs in
            stmt.executeUpdate( "UPDATE User SET searching='false' WHERE ID='" + userID + "'" );

            // User A (id=1) wants to see user B's(id=2) skills, and potentially endorse one
            // query for user B's skills:
            int A_ID = 1;
            int B_ID = 2;
            rs = stmt.executeQuery( "SELECT name, description FROM Skill WHERE userID='2'" );
            while( rs.next() )
            {
                String name = rs.getString( "name" );
                String desciption = rs.getString( "description" );
                System.out.println( "Skill: " + name + ", Description: " + desciption );
            }
            // User A sees B's skill and wants to endorse user B's skill in Java:
            stmt.executeUpdate( "INSERT INTO Endorses VALUES ('" + A_ID + "', '" + B_ID + "', 'Java'"  );

            // A user wants a listing of all of Google's California offices
            rs = stmt.executeQuery( "SELECT C.companyID, Name, city, state " +
                                        "FROM Company AS C NATURAL JOIN Office " +
                                        "WHERE State='CA' AND Name='Google' ;" );
            while( rs.next() )
            {
                String name = rs.getString( "Name" );
                String city = rs.getString( "City" );
                String state = rs.getString( "State" );
                System.out.println( "Name: " + name + ", City: " + city + ", State: " + state );
            }

            // Again, the example queries above will be separated into functions for the final implementation
            // For more examples, including some complex ones, see section 3.1 of our phase 2 write-up

            // close the connect to the database
            if ( con != null ) con.close();

        } catch ( Exception e ) {
            System.out.println("Error: " + e.getMessage());
        }*/
}
