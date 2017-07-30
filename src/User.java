import java.sql.Connection; // FIXME might not be necessary
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.lang.reflect.Method;

public abstract class User
{
    private String email;
    private static boolean out,check;
    private static String user_inputStr,method;
    private static String[] user_input;
    public Connection con;

    public static boolean userLoop (String name, String id, boolean isEmp, Connection con) {
    	out = true;
    	Scanner User_input = new Scanner(System.in);
    	Employee employee = new Employee();
    	Employer employer = new Employer();
		// FIXME scoping is messed up for the connection


    	//Class cls = User.class;
    	while (out == true) {
    		try {
	    		System.out.println("Welcome " + name + ",");
	    		System.out.println("Command (or help for a list of commands) >>>");
	    		user_inputStr= User_input.nextLine();
	    		user_input = user_inputStr.split(" ");
	    		//System.out.println(user_input[0]);
	    		if ((user_input[0].toUpperCase()).equals("HELP")) {
	    			System.out.println("-SearchEmployee: Search for an Employee.\n\tSyntax: SearchEmployee -name <Employee Name/null> -follow <true/false/null>\n");
	    			System.out.println("-ViewEmployee: View an employees profile.\n\tSyntax: viewEmployee -id <Employee ID>\n");
	    			System.out.println("-searchCompany: search for a company.\n\tSyntax: searchCompany -name <Company Name/null> -location <City/State/City,State/null> -following <true/false/null>\n");
	    			System.out.println("-viewCompany: view a company profile.\n\tSyntax: viewCompany -id <Company ID>\n");
	    			System.out.println("-searchJob: search for a job listing.\n\tSyntax: searchJob -id <Company ID/null> -role <Job Role> -following <true/false/null>\n");
	    			System.out.println("-editProfileAttribute: edit an attribute in the user profile.\n\tSyntax: editProfileAttribute -name <Attribute Name> -new <New Attribute Value>\n");
	    			System.out.println("-addPhone: add a new phone number to a user profile.\n\tSyntax: addPhone -newNumber <Phone Number> -type <type>\n");
	    			System.out.println("-addDelete: delete a phone number from a user profile.\n\tSyntax: deletePhone -number <Phone Number>\n");
	    			System.out.println("-viewReview: view the company reviews.\n\tSyntax: viewReviews -id <Company ID>\n");
	    			if (isEmp) {
	    				System.out.println("-makeConnection: make a connection with another employee.\n\tSyntax: makeConnection -id <Company ID>\n");
	    				System.out.println("-followCompany: follow a company to view their profile.\n\tSyntax: followCompany -id <Company ID>\n");
	    				System.out.println("-reviewCompany: write a review about a company.\n\tSyntax: reviewCompany -id <Company ID> -description <review description> -recommended <true/false/null>\n");
	    				System.out.println("-endorseSkill: endorse an employee's skill.\n\tSyntax: endorseSkill -id <Employee ID> -skillName <Skill Name>\n");
	    				System.out.println("-addSkill: add a skill to user profile.\n\tSyntax: addSkill -name <Skill Name> -description <Description>\n");
	    				System.out.println("-deleteSkill: delete a skill to user profile.\n\tSyntax: deleteSkill -name <Skill Name>\n");
	    				System.out.println("-addPastJob: add a past job to profile.\n\tSyntax: addPastJob -role <Job Role> -id <Company id> -description <Job description>\n");
	    				System.out.println("-deletePastJob: delete a past job from profile.\n\tSyntax: deletePastJob -role <Job Role> -id <Company id>\n");
	    			}else {
	    				System.out.println("-addAssociate: add another company as an associate.\n\tSyntax: addAssociate -id <Company ID>\n");
	    				System.out.println("-postJob: post a new job listing.\n\tSyntax: postJob -role <Job Role> -salary <Job Salary/null> -description <Job description>\n");
	    				System.out.println("-deleteJob: delete a job listing .\n\tSyntax: deleteJob -role <Job Role>\n");
	    				System.out.println("-addOffice: add a company office location.\n\tSyntax: addOffice -location <CitySstate>\n");
	    				System.out.println("-deleteOffice: delete an office location.\n\tSyntax: deleteOffice -location <City,State>\n");
	    			}
	    		//searchEmployee call
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHEMPLOYEE")) {
	    			if ((user_input[4].toUpperCase()).equals("TRUE")) {
	    				check = true;
	    			}else {
	    				check = false;
	    			}
	    			searchEmployee(user_input[2], check, con );
	    		// viewEmployee call
	    		}else if((user_input[0].toUpperCase()).equals("VIEWEMPLOYEE")) {
	    			viewEmployee(user_input[2], con);
	    		// SearchCompany
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHCOMPANY")) {
	    			if ((user_input[6].toUpperCase()).equals("TRUE")) {
	    				check = true;
	    			}else {
	    				check = false;
	    			}
	    			searchCompany(user_input[2], user_input[4], check, con);
	    		}else if((user_input[0].toUpperCase()).equals("VIEWCOMPANY")) {
	    			viewCompany(user_input[2], con );
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHJOB")) {
	    			searchJob(user_input[2], user_input[4], Integer.parseInt(user_input[6]), con);
	    		}else if((user_input[0].toUpperCase()).equals("EDITPROFILEATTRIBUTE")) {
	    			editProfileAttribute(user_input[2], user_input[4], isEmp, Integer.parseInt(id),  con);
	    		}else if((user_input[0].toUpperCase()).equals("ADDPHONE")) {
	    			addPhone(user_input[2], user_input[4], Integer.parseInt(id), con);
	    		}else if((user_input[0].toUpperCase()).equals("DELETEPHONE")) {
	    			deletePhone(user_input[2], con);
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHREVIEWS")) {
	    			searchReviews(user_input[2], con);
	    		// Calling User specific methods
	    		}else {
	    			if (isEmp) {
	    				check = employee.employeeMethodCall(user_input);
	    			} else {
	    				check = employer.employerMethodCall(user_input);
	    			}
	    			if (!check) {
	    				System.out.println("Command not found!");
	    			}
	    		}
    		} catch (Exception e) {
    			System.out.println("Syntax Error, please use the help command to see proper use.");
    		}
    	}
    	return true;
    }

    /**
     * @param name: name of the employee you are searching for
     * @param following: true/false tag that will filter results if true*
     * @return: a result set containing all names similar the given name
     */
    public static void searchEmployee( String name, boolean following, Connection con )
    {
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs;
			if ( following )
				rs = stmt.executeQuery("SELECT * FROM Employee WHERE Name LIKE %name%"); // FIXME following filter
			else
				rs = stmt.executeQuery( "SELECT * FROM Employee WHERE NAME LIKE %name%" );

			System.out.println( "Users: " );
			while( rs.next() )
			{
				int userID = rs.getInt( "userID" );
				String resultName = rs.getString( "name" );
				String almaMater = rs.getString( "alma_mater" );
				String major = rs.getString( "major" );
				double gpa = rs.getDouble( "gpa" );

				System.out.println( "\t-Name: " + resultName );
				System.out.println( "\t\tID: " + Integer.toString(userID) );
				System.out.println( "\t\tAlma Mater: " + almaMater );
				System.out.println( "\t\tMajor: " + major );
				System.out.println( "\t\tGPA: " + Double.toString( gpa ) );
			}

		} catch ( Exception e ) {
			System.out.println("Error: failed search for employees.");
		}

		return;
    }

    /**
     * @param id: the userID of the employee that you want to see
     */
    public static void viewEmployee( String id, Connection con )
    {
		try
		{
			Statement stmt = con.createStatement();
			ResultSet profile = stmt.executeQuery("SELECT * FROM Employee WHERE userID='" + id + "'" );
			ResultSet pastJobs = stmt.executeQuery( "SELECT PAST_JOB.COMPANYID, PAST_JOB.DESCRIPTION AS DESC, ROLE, NAME\n" +
					"FROM PAST_JOB JOIN COMPANY WHERE companyID='" + id + "'");

			System.out.println( "User Profile Information:" );
			while( profile.next() )
			{
				int userID = profile.getInt( "userID" );
				String name = profile.getString( "name" );
				String almaMater = profile.getString( "alma_mater" );
				String major = profile.getString( "major" );
				double gpa = profile.getDouble( "gpa" );

				System.out.println( "\tID: " + Integer.toString(userID) );
				System.out.println( "\tName: " + name );
				System.out.println( "\tAlma Mater: " + almaMater );
				System.out.println( "\tMajor: " + major );
				System.out.println( "\tGPA: " + Double.toString( gpa ) );
			}

			System.out.println( "\nPast Jobs: " );
			while( pastJobs.next() )
			{
				String name = pastJobs.getString( "name" );
				String role = pastJobs.getString( "role" );
				String desc = pastJobs.getString( "PAST_JOB.DESCRIPTION" );

				System.out.println( "\t-" + name );
				System.out.println( "\t\tRole: " + role );
				System.out.println( "\t\tDesciption: " + desc );
			}

		} catch ( Exception e ) {
			System.out.println("Error: couldn't find user.");
		}

		return;
    }

    /**
     * @param name
     * @param location
     * @param following
     * @return
     */
    public static void searchCompany( String name, String location, Connection con )
    { //test me
    	try
		{
			Statement stmt = con.createStatement();
			ResultSet rs;
			if ( location.equals( "null" ) )
				rs = stmt.executeQuery( "SELECT * FROM Employee_View WHERE NAME LIKE %'"+ name +"'%" );
			else
				rs = stmt.executeQuery( "SELECT * FROM Employee_View WHERE NAME LIKE %'"+ name +"'% and LOCATION LIKE %'"+location+"'%" );

			System.out.println( "Companies: " );
			while( rs.next() )
			{
				int companyID = rs.getInt( "COMPANYID" );
				String resultName = rs.getString( "NAME" );
				String description = rs.getString( "Description" );

				System.out.println( "\t-Name: " + resultName );
				System.out.println( "\t\tID: " + Integer.toString(companyID) );
				System.out.println( "\t\tDescription: " + description );
			}

		} catch ( Exception e ) {
			System.out.println("Error: failed search for employee.");
		}

		return;
    }

    /**
     * @param id
     * @return
     */
    public static void viewCompany( String id, Connection con )
    {
		try
		{
			Statement stmt = con.createStatement();
			ResultSet profile = stmt.executeQuery("SELECT COMPANY.COMPANYID, COMPANY.NAME, DESCRIPTION, HIRING, (" +
					               "  SELECT AVG(CASE WHEN REVIEW.RECOMMEND='TRUE' AND REVIEW.COMPANYID='" + id + "' THEN 1.0 ELSE 0 END ) AS RATING" +
					               "  FROM REVIEW" +
					               "  WHERE REVIEW.COMPANYID='" + id + "'  ) AS RATING" +
					               "FROM COMPANY" +
					               "WHERE COMPANYID='" + id + "'" );

			ResultSet offices = stmt.executeQuery( "SELECT City, State FROM Office WHERE companyID='" + id + "'" );
			ResultSet associates = stmt.executeQuery( "SELECT CID2 FROM ASSOCIATES WHERE CID1='" + id + "'" );
			ResultSet openJobs = stmt.executeQuery( "SELECT * FROM Job WHERE companyID='" + id + "'");

			System.out.println( "Company Profile Information:" );
			while( profile.next() )
			{
				int compID = profile.getInt( "companyID" );
				String name = profile.getString( "name" );
				String description = profile.getString( "description" );
				boolean hiring = profile.getBoolean( "hiring" );
				int rating = 100 * (int)profile.getDouble( "rating" );

				System.out.println( "\tID: " + Integer.toString(compID) );
				System.out.println( "\tName: " + name );
				System.out.println( "\tDescription: " + description );
				System.out.println( "\t" + Integer.toString( rating )  + "% of all reviews were positive." );
				if ( hiring )
					System.out.println( "\t" + name + " is currently hiring." );
				else
					System.out.println( "\t" + name + " is not currently hiring."  );
			}

			System.out.println( "\nCompany Offices" );
			while( offices.next() )
			{
				String city = offices.getString( "city" );
				String state = offices.getString( "state" );

				System.out.println( "\t-City: " + city + ", State: " + state );
			}


			System.out.println( "\nOpen Job Listings:" );
			while( openJobs.next() )
			{
				String role = openJobs.getString( "role" );
				int salary = openJobs.getInt( "salary" );
				String description = openJobs.getString( "desciption" );

				System.out.println( "\t-Role: " + role + ", Salary: " + Integer.toString(salary) + ", Description: " + description );
			}

		} catch ( Exception e ) {
			System.out.println("Error: couldn't find company.");

		}

		return;
    }


    /**
     * @param companyID
     * @param role
     * @param minimumSalary
     * @return
     */
    public static void searchJob( String companyID, String role, int minimumSalary, Connection con )
    {
    	//test me
    	try
		{
			Statement stmt = con.createStatement();
			ResultSet rs,name;
			rs = stmt.executeQuery( "SELECT * FROM Employee WHERE CompanyID LIKE %'"+ companyID +"'% and ROLE LIKE %'"+ role + "'% and SALARY>'"+ minimumSalary + "'" );
			name = stmt.executeQuery( "SELECT * FROM Employee WHERE COMPANYID = '"+companyID+"'" );
			System.out.println( "Job listing: " );
			while( rs.next() && name.next() )
			{
				int salary = rs.getInt( "SALARY" );
				String resultName = name.getString( "NAME" );
				String roles = rs.getString( "ROLE" );
				String description = rs.getString( "Description" );

				System.out.println( "\t-Name: " + resultName );
				System.out.println( "\t\tID: " + companyID);
				System.out.println( "\t\trole: " + roles );
				System.out.println( "\t\tDescription: " + description );
			}

		} catch ( Exception e ) {
			System.out.println("Error: failed search for job listing.");
		}

		return;
    }

    /**
     * @param attributeName
     * @param newVal
     * @return
     */
    public static void editProfileAttribute( String attributeName, String newVal, boolean isEmp, int id, Connection con )
    {// test me
    	try
		{
    		if (isEmp) {
    			Statement stmt = con.createStatement();
    			stmt.executeUpdate("update Employee Set '"+ attributeName +"' = '"+ newVal +"' where USERID= '" + id + "'");
    			System.out.println( attributeName + " was updated with the new value" );	
    		}else {
    			Statement stmt = con.createStatement();
    			stmt.executeUpdate("update Company Set '"+ attributeName +"' = '" + newVal + "' where COMPANYID = '"+ id + "'");
    			System.out.println( attributeName + " was updated with the new value" );	
    			
    		}
		} catch ( Exception e ) {
			System.out.println("Error: failed to edit attribute.");
		}	

    }

    /**
     * @param newNumber
     * @param type
     * @return
     */
    public static void addPhone(String newNumber, String type, int id, Connection con )
    { //test me
    	try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into Phone values('"+id+"', '"+newNumber+"', '"+type+"')");
			System.out.println( "Phone number added to profile" );
		} catch ( Exception e ) {
			System.out.println("Error: failed to add phone number.");
		}	
    }



	/**
     * @param number

     * @return
     */
    public static void deletePhone( String number, Connection con )
    {
    	try
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("delete from Phone where NUMBER = '"+ number + "'");
			System.out.println("Phone number deleted from profile");
		} catch ( Exception e ) {
			System.out.println("Error: failed to delete phone number.");
		}
    }

    /**
     * @param companyID
     * @return
     */
    public static void searchReviews( String companyID , Connection con)
    { //test me
    	try
		{
			Statement stmt = con.createStatement();
			ResultSet rs, cName, eName
			rs = stmt.executeQuery( "SELECT * FROM Review WHERE CompanyID LIKE %'"+companyID+"'%");
			cName = stmt.executeQuery( "SELECT * FROM COMPANY WHERE COMPANYID = '"+companyID+"'" );
			System.out.println( "Reviews: " );
			while( rs.next())
			{
				int id = rs.getInt( "USERID" );
				String companyName = cName.getString( "NAME" );
				eName = stmt.executeQuery( "SELECT * FROM Employee WHERE COMPANYID = '" + id + "'" );
				String employeeName = eName.getString( "NAME" );
				String description = rs.getString( "Description" );
				Boolean recomm = rs.getBoolean("RECOMMEND");
				
				System.out.println( "\t-Company Name: " + companyName);
				System.out.println( "\t-Employee Name: " + employeeName);
				System.out.println( "\t\tID: " + companyID);
				System.out.println( "\t\tDescription: " + description );
				System.out.println( "\t\tRecommended: " + recomm );
			}

		} catch ( Exception e ) {
			System.out.println("Error: failed to find reviews");
		}

		return;
    }

    /**
     * @return
     */
    public String getEmail()
    {
        return this.email;
    }

	/**
	 *
	 * @param con
	 */
	public void setConnection(Connection con) {
		this.con = con;
	}
}