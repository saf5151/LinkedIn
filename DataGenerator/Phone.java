import java.util.Random;

public class Phone extends Entry{
	private String[] types = {"Home","Work","Cell"};
	
	public String getTable(){
		return "Phone";
	}
	public String value(){
		String out;
		
		Random rand = new Random();
		int ID = rand.nextInt(User.numUsers);
		int number = rand.nextInt(8999999) + 1000000;
		String type = types[rand.nextInt(3)];
		out = "(" + ID + "," + number + ",'" + type + "')";
		return out;
	}
	
}
