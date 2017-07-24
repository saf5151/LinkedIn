import java.util.Random;

public class Associates {
	
	public String getTable(){
		return "Associates";
	}
	
	public String value(){
		String out;
		
		Random rand = new Random();
		int ID1 = rand.nextInt(Company.numComps);
		int ID2 = 0;
		while((ID2 = rand.nextInt(Company.numComps)) == ID1){}
		out = "(" + ID1 + "," + ID2 + ")";
		return out;
	}
}
