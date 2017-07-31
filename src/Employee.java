/**
 * filename: Employee.java
 * author: Swan Ronson
 * date: 7/30/17
 *
 * The Employee class specifies all of the functionality that is exclusive to Employees
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Employee extends User {
	/** These fields correspond to the values of a row in the db, kept locally to avoid extra querying */
	private int id, age;
	private String email;
	private String almaMater;
	private String name;
	private String major;
	private String gpaStr;
	private double gpa;
	private String [] phone;
	private String dob;
	private String[] location;
	private String city;
	private String state;
	private String searchStr;
	private boolean search;
	
	/**
     * @param email: email of the user registering
     *	   password: login password
     * @return: true for completed registration, false for failed registration
     */
	public boolean employeeRegisteration(String email, String password) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		java.util.Date dobDate;
		SimpleDateFormat dt1 = new SimpleDateFormat("YYYY-MM-DD");

		try {
			Statement stmt = this.con.createStatement();
			System.out.println("Full Name: ");
			name = ApplicationMain.inst.next();
			System.out.println("Alma Mater: ");
			almaMater = ApplicationMain.inst.next();
			System.out.println("Major: ");
			major = ApplicationMain.inst.next();
			System.out.println("GPA: ");
			gpaStr = ApplicationMain.inst.next();
			gpa = Double.parseDouble(gpaStr);
			System.out.println("Phone Number (seperate by comma if multiple): ");
			phone = (ApplicationMain.inst.next()).split(",");
			System.out.println("Date of Birth (format: YYYY-MM-DD): ");
			dob = ApplicationMain.inst.next();
			System.out.println("Location (format: city,state): ");
			location = (ApplicationMain.inst.next()).split(",");
			city = location[0];
			state = location[1];
			//System.out.println(city);
			System.out.println("Would you like to be discoverable by employers(y/n)? ");
			searchStr = ApplicationMain.inst.next();
			if (searchStr.equals("y") || searchStr.equals("Y")) {
				search = true;
			}else {
				search = false;
			}
			
			stmt.executeUpdate("insert into Employee (name, alma_mater, major, gpa, email, dob, city, state, searching) Values('" + name + "', '"+ almaMater +"', '"+ major +"', '"+ gpa +"', '"+ email +"', Date '"+ dob +"', '"+ city +"', '"+ state +"', '"+ search +"')");
			return true;
		}catch ( Exception e ){
			System.out.println("User was Registered, but the profile information were not saved. Error Message: " + e.getMessage());
			return false;
		}

	}
	/**
     * @param user_input: an Array containing the method to be called and parameters
     * @return: true on success, false otherwise
     */
    public boolean employeeMethodCall( String[] user_input )
    {
    	String method = user_input[0].toUpperCase();
    	boolean found = true;
    	boolean check; 
    	if (method.equals("MAKECONNECTION")) {
    		makeConnection(user_input[2]);
    	}else if(method.equals("FOLLOWCOMPANY")) {
    		followCompany(user_input[2]);
    	}else if (method.equals("REVIEWCOMPANY")) {
    		if(user_input[6].toUpperCase().equals("TRUE")) {
    			check = true;
    		}else {
    			check = false;
    		}
    		reviewCompany(user_input[2], user_input[4], check);
    	}else if (method.equals("ENDORSESKILL")) {
    		endorseSkill( user_input[2], user_input[4]);
    	}else if (method.equals("ADDSKILL")) {
    		addSkill( user_input[2], user_input[4]);
    	}else if (method.equals("DELETESKILL")) {
    		deleteSkill( user_input[2]);
    	}else if (method.equals("ADDPASTJOB")) {
    		addPastJob( user_input[2],user_input[4],user_input[6]);
    	}else if (method.equals("DELETEPASTJOB")) {
    		deletePastJob( user_input[2], user_input[4]);
    	}else {
    		found = false;
    	}
        // TODO
    	return found;
    }
	/**
     * @param theirID: the id of the user you wish to make a connection with
     * @return: true on success, false otherwise
     */
    public boolean makeConnection( String theirID )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Connect VALUES ( '" +
					Integer.toString( this.id ) + "', '" + theirID +"')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: couldn't make connection.");
			return false;
		}
    }

    /**
     * @param companyID
     * @return: true on success, false otherwise
     */
    public boolean followCompany( String companyID )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Follows VALUES ( '" +
					Integer.toString( this.id ) + "', '" + companyID +"')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: failed to follow company.");
			return false;
		}
    }

    /**
     * @param companyID
     * @param description
     * @param recommended
     * @return: true on success, false otherwise
     */
    public boolean reviewCompany( String companyID, String description, boolean recommended )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Review VALUES ( '" +
					Integer.toString( this.id ) + "', '" + companyID +"', '" + description + "', '" + recommended + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: could not review company.");
			return false;
		}
    }

    /**
     * @param theirID
     * @param skillName
     * @return: true on success, false otherwise
     */
    public boolean endorseSkill( String theirID, String skillName )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Endorses VALUES ( '" +
					Integer.toString( this.id ) + "', '" + theirID +"', '" + skillName + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: failed to endorse skill.");
			return false;
		}
    }

    /**
     * @param name
     * @param description
     * @return: true on success, false otherwise
     */
    public boolean addSkill( String name, String description )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Skill VALUES ( '" +
					name + "', '" + description +"', '"  + Integer.toString( this.id ) + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: company does not exist.");
			return false;
		}
    }

    /**
     * @param name
     * @return: true on success, false otherwise
     */
    public boolean deleteSkill( String name )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("DELETE FROM Skill WHERE name='" + name + "' AND userID='" + Integer.toString( this.id ) + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: company does not exist.");
			return false;
		}
    }

    /**
     * @param role
     * @param companyID
     * @param description
     * @return: true on success, false otherwise
     */
    public boolean addPastJob( String role, String companyID, String description )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("INSERT INTO Past_Job VALUES ( '" + Integer.toString( this.id ) + "', '" +
					companyID + "', '" + role + "', '" + description + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: company does not exist.");
			return false;
		}
    }

    /**
     * @param role
     * @param companyID
     * @return: true on success, false otherwise
     */
    public boolean deletePastJob( String role, String companyID )
    {
		try
		{
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate("DELETE FROM Past_Job WHERE companyID='" + companyID + "' AND userID='"
					+ Integer.toString( this.id ) + "' AND role='" + role + "')" );
			return true;

		} catch ( Exception e ) {
			System.out.println("Error: company does not exist.");
			return false;
		}
    }

    /** GETTERS */

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAlmaMater()
    {
        return this.almaMater;
    }

    public String getMajor()
    {
        return this.major;
    }

    public double getGpa()
    {
        return this.gpa;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
    	/* 
    	 * get dob from db
    	 */
    	return age;
    }

    public Connection getCon()
	{
		return this.con;
	}

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isSearching() {
        return search;
    }

    public void setId( Integer id )
	{
		this.id = id;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public void setSearching( boolean searching )
	{
		this.search = searching;
	}

	public void setName( String name )
	{
		this.name = name;
	}


}

