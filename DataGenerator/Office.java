import java.util.Random;
	
public class Office extends Entry{
		
		public String getTable(){
			return "Office";
		}
		
		public String value(){
			String out;
			
			Random rand = new Random();
			int ID = rand.nextInt(Company.numComps);
			String city = Place.getCity();
			String state = Place.getState();
			out = "(" + ID + ",'" + city + "','" + state + "')";
			return out;
		}

}