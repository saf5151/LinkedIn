import java.sql.Connection; // FIXME might not be necessary
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class User
{
    private String email;

    /**
     * @param name: name of the employee you are searching for
     * @param following: true/false tag that will filter results if true*
     * @return: a result set containing all names similar the given name
     */
    public ResultSet searchEmployee( String name, boolean following )
    {
        // TODO
    }

    /**
     * @param id: the userID of the employee that you want to see
     */
    public ResultSet viewEmployee( int id )
    {
        // TODO
    }

    /**
     * @param name
     * @param location
     * @param following
     * @return
     */
    public ResultSet searchCompany( String name, String location, boolean following )
    {
        // TODO
    }

    /**
     * @param id
     * @return
     */
    public ResultSet viewCompany( int id )
    {
        // TODO
    }

    /**
     * @param companyID
     * @param role
     * @param minimumSalary
     * @return
     */
    public ResultSet searchJob( int companyID, String role, int minimumSalary )
    {
        // TODO
    }

    /**
     * @param attributeName
     * @param newVal
     * @return
     */
    public ResultSet editProfileAttribute( String attributeName, String newVal )
    {
        // TODO
    }

    /**
     * @param newNumber
     * @param type
     * @return
     */
    public ResultSet addPhone( int newNumber, String type )
    {
        // TODO
    }

    /**
     * @param number
     * @return
     */
    public ResultSet deletePhone( int number )
    {
        // TODO
    }

    /**
     * @param companyID
     * @return
     */
    public ResultSet searchReviews( int companyID )
    {
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