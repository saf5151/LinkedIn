package DataGenerator;

import java.util.Random;

public class Endorses extends Entry{
	
	public String getTable(){
		return "Endorses";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int UID = Skill.lastID;
		int EID = 0;
		while((EID = rand.nextInt(Employee.numUsers)) == UID){}
		String skill = Skill.lastName;
		out = "(" + EID + "," + UID + ",'" + skill+ "')";
		return out;
	}
}
