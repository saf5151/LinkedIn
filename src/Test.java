
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import org.h2.tools.Server;
 
public class Test {
 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
    	String dbpath = "jdbc:h2:~/test";
    	Class.forName("org.h2.Driver");
		
    	
    	int ID = 0;
    	
    	String fileName = "Test.EmployeeRegister";
    	File input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	
    	Connection con = DriverManager.getConnection( dbpath, "admin", "admin");
		Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery("select * from employee where email='erb8134@gmail.com'");
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    		return;
    	}
    	ID = rs.getInt("userID");
    	
    	fileName = "Test.EmployeeModify";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	con = DriverManager.getConnection( dbpath, "admin", "admin");
		stmt = con.createStatement();
    	rs = stmt.executeQuery("select * from employee where email='erb8134@gmail.com'");
    	rs.next();
    	String checkS = rs.getString("city");
    	if(!checkS.equals("Rochester")){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.EmployeePhone";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from phone where userID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	if(rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.MakeConnection";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from connect where AID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.Follow";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from follows where userID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.Review";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from review where userID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.AddSkill";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from skill where userID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.DeleteSkill";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from skill where userID=" + ID);
    	if(rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.AddPastJob";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from past_job where userID=" + ID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.DeletePastJob";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from past_job where userID=" + ID);
    	if(rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	
    	int CID = 0;
    	
    	fileName = "Test.CompanyRegister";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from company where email='bear@gmail.com'");
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    		return;
    	}
    	CID = rs.getInt("userID");
    	
    	fileName = "Test.CompanyPhone";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from comp_phone where companyID=" + CID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	if(rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.EmployeeModify";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from company where email='bear@gmail.com'");
    	rs.next();
    	checkS = rs.getString("description");
    	if(!checkS.equals("A company for bears")){
    		System.out.println("Error at " + fileName);
    	}
    	
    	fileName = "Test.AddAssociate";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from associates where companyID=" + CID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
		
		fileName = "Test.PostJob";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from job where companyID=" + CID);
    	if(!rs.next()){
    		System.out.println("Error at " + fileName);
    	}
		
		fileName = "Test.DeleteJob";
    	input = new File(fileName);
    	ApplicationMain.setInput(input);
    	ApplicationMain.main(null);
    	rs = stmt.executeQuery("select * from job where companyID=" + CID);
    	if(rs.next()){
    		System.out.println("Error at " + fileName);
    	}
    	
    }
}