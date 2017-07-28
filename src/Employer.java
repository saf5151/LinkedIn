import java.sql.Connection; // FIXME might not be necessary
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Employer extends User
{
    private int companyID;
    private String name;
    private String description;
    private String email;
    private String hiringStr;
    private boolean hiring;
    
	
	/**
     * @param email: email of the user registering
     *	   password: login password
     * @return: true for completed registration, false for failed registration
     */
    public boolean employerRegisteration(String email, String password) {
		Scanner Name = new Scanner(System.in);
		Scanner Description = new Scanner(System.in);
		Scanner Hiring = new Scanner(System.in);
    	this.email= email;
		//System.out.println("Got it from" + email);
		/* 
		 * Do we need data validation???
		 */
		System.out.println("Company Name: ");
		name = Name.next();
		System.out.println("Description: ");
		description = Description.next();
		System.out.println("Currently Hiring (y/n): ");
		hiringStr = Hiring.next();
		if (hiringStr.equals("y") || hiringStr.equals("Y")) {
			hiring = true;
		}else {
			hiring = false;
		}
		return true;
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
    public boolean employerMethodCall( String[] user_input )
    {
    	String method = user_input[0].toUpperCase();
    	boolean found = true;
    	if (method.equals("ADDASSOCIATE")) {
    		addAssociate(user_input[2]);
    	}else if(method.equals("POSTJOB")) {
    		postJob(user_input[2], Integer.parseInt(user_input[4]),user_input[6]);
    	}else if(method.equals("DELETEJOB")) {
    		deleteJob(user_input[2]);
    	}else if(method.equals("ADDOFFICE")) {
    		addOffice(user_input[2], user_input[4]);
    	}else if(method.equals("POSTJOB")) {
    		deleteOffice(user_input[2], user_input[4]);
    	}else {
    		found = false;
    	}
        // TODO
    	return found;
    }
    
    /**
     * @param theirCompanyID: the id of the company that you you want to add as an associate
     * @return: true on success, false otherwise
     */
    public boolean addAssociate( String theirCompanyID )
    {
        // TODO
    	return true;
    }

    /**
     * @param role
     * @param salary
     * @param description
     * @return: true on success, false otherwise
     */
    public boolean postJob( String role, int salary, String description )
    {
        // TODO
    	return true;
    }

    /**
     * @param role
     * @return: true on success, false otherwise
     */
    public boolean deleteJob( String role )
    {
        // TODO
    	return true;
    }

    /**
     * @param city
     * @param state
     * @return: true on success, false otherwise
     */
    public boolean addOffice( String city, String state )
    {
        // TODO
    	return true;
    }

    /**
     * @param city
     * @param state
     * @return: true on success, false otherwise
     */
    public boolean deleteOffice( String city, String state )
    {
        // TODO
    	return true;
    }


    // GETTERS

    public int getCompanyID()
    {
        return this.companyID;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean isHiring()
    {
        return this.hiring;
    }

}
