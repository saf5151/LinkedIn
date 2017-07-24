import java.util.Random;

public class Employs {

	public String getTable(){
		return "Employs";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int CID = rand.nextInt(Company.numComps);
		int UID = rand.nextInt(User.numUsers);
		out = "(" + CID + "," + UID + ")";
		return out;
	}
}
