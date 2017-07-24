import java.util.Random;
	
public class Prefers extends Entry{
		
		public String getTable(){
			return "Prefers";
		}
		
		public String value(){
			String out;
			
			Random rand = new Random();
			int ID = rand.nextInt(User.numUsers);
			String city = Place.getCity();
			String state = Place.getState();
			out = "(" + ID + ",'" + city + "','" + state + "')";
			return out;
		}

}
