import java.util.Random;

public class Endorses extends Entry{
	
	public String getTable(){
		return "Endorses";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int EID = rand.nextInt(User.numUsers);
		int UID = 0;
		while((UID = rand.nextInt(User.numUsers)) == EID){}
		String skill = Skill.getSkill();
		out = "(" + EID + "," + UID + ",'" + skill+ "')";
		return out;
	}
}
