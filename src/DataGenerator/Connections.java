package DataGenerator;

import java.util.Random;

public class Connections extends Entry{
	
	public String getTable(){
		return "Connect";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int IDA = rand.nextInt(Employee.numUsers);
		int IDB = 0;
		while((IDB = rand.nextInt(Employee.numUsers)) == IDA){}
		out = "(" + IDA + "," + IDB + ")";
		return out;
	}
}
