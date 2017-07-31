package DataGenerator;

import java.util.Random;

public class Phone extends Entry{
	private String[] types = {"home","work","cell"};
	
	public String getTable(){
		return "Phone";
	}
	public String value(){
		String out;
		
		Random rand = new Random();
		int ID = rand.nextInt(Employee.numUsers);
		int number = rand.nextInt(1999999999) + 1000000000;
		String type = types[rand.nextInt(3)];
		out = "(" + ID + "," + number + ",'" + type + "')";
		return out;
	}
	
}
