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
    public static boolean userLoop (String name, String id, boolean isEmp) {
    	out = true;
    	Scanner User_input = new Scanner(System.in);
    	Employee employee = new Employee();
    	Employer employer = new Employer();
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
	    			searchEmployee(user_input[2], check);
	    		// viewEmployee call
	    		}else if((user_input[0].toUpperCase()).equals("VIEWEMPLOYEE")) {
	    			viewEmployee(user_input[2]);
	    		// SearchCompany
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHCOMPANY")) {
	    			if ((user_input[6].toUpperCase()).equals("TRUE")) {
	    				check = true;
	    			}else {
	    				check = false;
	    			}
	    			searchCompany(user_input[2], user_input[4], check);
	    		}else if((user_input[0].toUpperCase()).equals("VIEWCOMPANY")) {
	    			viewCompany(user_input[2]);
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHJOB")) {
	    			searchJob(user_input[2], user_input[4], Integer.parseInt(user_input[6]));
	    		}else if((user_input[0].toUpperCase()).equals("EDITPROFILEATTRIBUTE")) {
	    			editProfileAttribute(user_input[2], user_input[4]);
	    		}else if((user_input[0].toUpperCase()).equals("ADDPHONE")) {
	    			addPhone(user_input[2], user_input[4]);
	    		}else if((user_input[0].toUpperCase()).equals("DELETEPHONE")) {
	    			deletePhone(user_input[2]);
	    		}else if((user_input[0].toUpperCase()).equals("SEARCHREVIEWS")) {
	    			searchReviews(user_input[2]);
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
    public static ResultSet searchEmployee( String name, boolean following )
    {
    	System.out.println("searchEmployee method");
        // TODO
    }

    /**
     * @param id: the userID of the employee that you want to see
     */
    public static ResultSet viewEmployee( String id )
    {
    	System.out.println("viewEmployee method");
        // TODO
    }

    /**
     * @param name
     * @param location
     * @param following
     * @return
     */
    public static ResultSet searchCompany( String name, String location, boolean following )
    {
    	System.out.println("searchCompany method");
        // TODO
    }

    /**
     * @param id
     * @return
     */
    public static ResultSet viewCompany( String id )
    {
    	System.out.println("viewCompany method");
        // TODO
    }

    /**
     * @param companyID
     * @param role
     * @param minimumSalary
     * @return
     */
    public static ResultSet searchJob( String companyID, String role, int minimumSalary )
    {
    	System.out.println("searchJob method");
        // TODO
    }

    /**
     * @param attributeName
     * @param newVal
     * @return
     */
    public static ResultSet editProfileAttribute( String attributeName, String newVal )
    {
        System.out.println("editProfileAttr method");
        // TODO
    }

    /**
     * @param newNumber
     * @param type
     * @return
     */
    public static ResultSet addPhone(String newNumber, String type )
    {
    	System.out.println("addPhone method");
        // TODO
    }

    /**
     * @param number
     * @return
     */
    public static ResultSet deletePhone( String number )
    {
        // TODO
    	System.out.println("deletePhone method");
    }

    /**
     * @param companyID
     * @return
     */
    public static ResultSet searchReviews( String companyID )
    {
    	System.out.println("searchReviews method");
        // TODO
    }

    /**
     * @return
     */
    public String getEmail()
    {
        return this.email;
    }
}