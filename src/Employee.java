import java.sql.Connection; // FIXME might not be necessary
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee extends User
{
    private int id;
    private String name;
    private String almaMater;
    private String major;
    private double gpa;
    private String email;
    private int age;
    private String city;
    private String state;
    private boolean searching;

    /**
     * @param theirID: the id of the user you wish to make a connection with
     * @return: true on success, false otherwise
     */
    public boolean makeConnection( int theirID )
    {
        // TODO
    }

    /**
     * @param companyID
     * @return: true on success, false otherwise
     */
    public boolean followCompany( int companyID )
    {
        // TODO
    }

    /**
     * @param companyID
     * @param description
     * @param recommended
     * @return: true on success, false otherwise
     */
    public boolean reviewCompany( int companyID, String description, boolean recommended )
    {
        // TODO
    }

    /**
     * @param theirID
     * @param skillName
     * @return: true on success, false otherwise
     */
    public boolean endorseSkill( int theirID, String skillName )
    {
        // TODO
    }

    /**
     * @param name
     * @param description
     * @return: true on success, false otherwise
     */
    public boolean addSkill( String name, String description )
    {
        // TODO
    }

    /**
     * @param name
     * @return: true on success, false otherwise
     */
    public boolean deleteSkill( String name )
    {
        // TODO
    }

    /**
     * @param role
     * @param companyID
     * @param description
     * @return: true on success, false otherwise
     */
    public boolean addPastJob( String role, int companyID, String description )
    {
        // TODO
    }

    /**
     * @param role
     * @param companyID
     * @return: true on success, false otherwise
     */
    public boolean deletePastJob( String role, int companyID )
    {
        // TODO
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
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isSearching() {
        return searching;
    }
}