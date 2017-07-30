package DataGenerator;

import java.util.Random;

public class Review extends Entry{
	public String getTable(){
		return "Review";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int CID = rand.nextInt(Company.numComps);
		int UID = rand.nextInt(Employee.numUsers);
		String description = Jackson.saySomething();
		boolean rec = new Random().nextBoolean();
		String r = "";
		if(rec){
			r = "TRUE";
		}else{
			r = "FALSE";
		}
		out = "(" + UID + "," + CID + ",'" + description + "'," + r + ")";
		return out;
	}
}