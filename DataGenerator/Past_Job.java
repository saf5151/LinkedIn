import java.util.Random;

public class Past_Job extends Entry{
	
	public String getTable(){
		return "Past_Job";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int UID = rand.nextInt(User.numUsers);
		int CID = rand.nextInt(Company.numComps);
		String role = Role.title();
		out = "(" + UID + "," + CID + ",'" + role + "')";
		return out;
	}
}
