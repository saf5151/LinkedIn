package DataGenerator;

import java.util.Random;

public class Job extends Entry{
	public String getTable(){
		return "Job";
	}
	public String value(){
		String out;
		
		Random rand = new Random();
		int ID = rand.nextInt(Company.numComps);
		String role = Role.title();
		int salary = rand.nextInt(105000) + 15000;
		String description = Jackson.saySomething();
		
		out = "(" + ID + ",'" + role + "'," + salary + "," + description + "')";
		return out;
	}
	
}
