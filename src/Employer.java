/**
 * filename: Employer.java
 * author: Swan Ronson
 * date: 7/30/17
 *
 * The Employer class contains all of the functionality that is exclusive to a Company
 */
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Employer extends User {
    /** These fields correspond to the values of a row in the db, kept locally to avoid extra querying */
    private int companyID;
    private String name;
    private String description;
    private String email;
    private String hiringStr;
    private boolean hiring;


    /**
     * @param email: email of the user registering
     *               password: login password
     * @return: true for completed registration, false for failed registration
     */
    public boolean employerRegisteration(String email, String password) {
        Scanner Name = new Scanner(System.in);
        Scanner Description = new Scanner(System.in);
        Scanner Hiring = new Scanner(System.in);
        this.email = email;
        //System.out.println("Got it from" + email);
		/* 
		 * Do we need data validation???
		 */
        try {
        	//System.out.println("Before");
			Statement stmt = this.con.createStatement();
	        System.out.println("Company Name: ");
	        name = Name.next();
	        System.out.println("Description: ");
	        description = Description.next();
	        System.out.println("Currently Hiring (y/n): ");
	        hiringStr = Hiring.next();
	        if (hiringStr.equals("y") || hiringStr.equals("Y")) {
	            hiring = true;
	        } else {
	            hiring = false;
	        }
	        stmt.executeUpdate("insert into Company (name, description, email, hiring) Values('" + name + "', '"+ description +"', '"+ email +"', '"+ hiring +"')");
	        return true;
        }catch (Exception e) {
			System.out.println("User was Registered, but the profile information were not saved. Error Message: " + e.getMessage());
			return false;
        }
		/*
		 * Update db with employee information
		 */
		
		/*
		Name.close();
		Description.close();
		Hiring.close();
		*/
    }

    /**
     * @param user_input: an Array containing the method to be called and parameters
     * @return: true on success, false otherwise
     */
    public boolean employerMethodCall(String[] user_input) {
        String method = user_input[0].toUpperCase();
        boolean found = true;
        if (method.equals("ADDASSOCIATE")) {
            addAssociate(user_input[2]);
        } else if (method.equals("POSTJOB")) {
            postJob(user_input[2], Integer.parseInt(user_input[4]), user_input[6]);
        } else if (method.equals("DELETEJOB")) {
            deleteJob(user_input[2]);
        } else if (method.equals("ADDOFFICE")) {
            addOffice(user_input[2], user_input[4]);
        } else if (method.equals("POSTJOB")) {
            deleteOffice(user_input[2], user_input[4]);
        } else {
            found = false;
        }
        // TODO
        return found;
    }

    /**
     * @param theirCompanyID: the id of the company that you you want to add as an associate
     * @return: true on success, false otherwise
     */
    public boolean addAssociate(String theirCompanyID) {
        try
        {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate("INSERT INTO Associates VALUES ( '" +
                    Integer.toString( this.companyID ) + "', '" + theirCompanyID +"')" );
            return true;

        } catch ( Exception e ) {
            System.out.println("Error: company does not exist.");
            return false;
        }
    }

    /**
     * @param role: the role of your new job, ex: Software Developer
     * @param salary: the amount you will pay anually for this job
     * @param description: short description of the job
     * @return: true on success, false otherwise
     */
    public boolean postJob(String role, int salary, String description) {
        try
        {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate("INSERT INTO Job VALUES ( '" + Integer.toString( this.companyID ) +
                    "', '" + role +"', '" + Integer.toString( salary ) + "', '" + description + "')" );
            return true;

        } catch ( Exception e ) {
            System.out.println("Error: could not post job.");
            return false;
        }
    }

    /**
     * @param role: role of your new job
     * @return: true on success, false otherwise
     */
    public boolean deleteJob(String role) {
        try
        {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate("DELETE FROM Job WHERE companyID='" + Integer.toString( this.companyID ) + "' " +
                    "AND role='" + role + "'" );
            return true;

        } catch ( Exception e ) {
            System.out.println("Error: no job with that role was found for your company.");
            return false;
        }
    }

    /**
     * @param city: city the office is in
     * @param state: abbreviation for the State that the office is in
     * @return: true on success, false otherwise
     */
    public boolean addOffice(String city, String state) {
        try
        {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate("INSERT INTO Office VALUES ( '" +
                    Integer.toString( this.companyID ) + "', '" + city +"', '" + state + "')" );
            return true;

        } catch ( Exception e ) {
            System.out.println("Error: could not add company.");
            return false;
        }
    }

    /**
     * @param city: city of the office you are deleting
     * @param state: abbreviation for the state the office is i
     * @return: true on success, false otherwise
     */
    public boolean deleteOffice(String city, String state) {
        try
        {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate("DELETE FROM Office WHERE companyID='" + Integer.toString( this.companyID)
                    + "' AND city='" + city + "' AND state='" + state + "'" );
            return true;

        } catch ( Exception e ) {
            System.out.println("Error: no office was found at that location.");
            return false;
        }
    }


    /** GETTERS */

    public int getCompanyID() {
        return this.companyID;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isHiring() {
        return this.hiring;
    }

    public Connection getCon() {
        return this.con;
    }

    /** SETTERS */

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHiring(boolean hiring)
    {
        this.hiring =hiring;
    }
}
