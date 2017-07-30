package DataGenerator;

import java.util.Random;

public class Past_Job extends Entry{
	
	public String getTable(){
		return "Past_Job";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int UID = rand.nextInt(Employee.numUsers);
		int CID = rand.nextInt(Company.numComps);
		String role = Role.title();
		String description = Jackson.saySomething();
		
		out = "(" + UID + "," + CID + ",'" + role + "','" + description + "')";
		return out;
	}
}
