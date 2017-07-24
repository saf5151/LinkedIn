import java.util.Random;

public class Comp_Phone {
private String[] types = {"Home","Work","Cell"};
	
	public String getTable(){
		return "Comp_Phone";
	}
	public String value(){
		String out;
		
		Random rand = new Random();
		int ID = rand.nextInt(Company.numComps);
		int number = rand.nextInt(8999999) + 1000000;
		String type = types[rand.nextInt(3)];
		out = "(" + ID + "," + number + ",'" + type + "')";
		return out;
	}
}
