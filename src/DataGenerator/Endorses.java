package DataGenerator;

import java.util.Random;

public class Endorses extends Entry{
	
	public String getTable(){
		return "Endorses";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int EID = Skill.lastID;
		int UID = 0;
		while((UID = rand.nextInt(Employee.numUsers)) == EID){}
		String skill = Skill.lastName;
		out = "(" + EID + "," + UID + ",'" + skill+ "')";
		return out;
	}
}
