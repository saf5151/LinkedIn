package DataGenerator;

import java.util.Random;

public class Employs extends Entry{

	public String getTable(){
		return "Employs";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int CID = rand.nextInt(Company.numComps);
		int UID = rand.nextInt(Employee.numUsers);
		out = "(" + CID + "," + UID + ")";
		return out;
	}
}
