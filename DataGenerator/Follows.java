import java.util.Random;

public class Follows {
	
	public String getTable(){
		return "Follows";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int UID = rand.nextInt(User.numUsers);
		int CID = rand.nextInt(Company.numComps);
		out = "(" + UID + "," + CID + ")";
		return out;
	}
}
