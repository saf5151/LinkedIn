import java.sql.Statement;
import java.util.Random;
import java.io.*;

public class Skill extends Entry implements Reader{
	private static String names[];
	public String getTable(){
		return "Skill";
	}
	
	public void setup(Statement stmt) {
		
		String fileName = "Skills";

        String line = null;
        
        names = new String[DataData.SKILLS];
		try {
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            int i = 0;
            while((line = bufferedReader.readLine()) != null){
                names[i] = line;
                i++;
            }   

            bufferedReader.close();         
        }catch(Exception e){
            System.out.println("Error");                
        }
	}
	
	public static String getSkill(){
		return names[new Random().nextInt(DataData.SKILLS)];
	}
	public String value(){
		String out;
		
		String name = Skill.getSkill();
		String description = Jackson.saySomething();
		int userID = new Random().nextInt(User.numUsers);
		
		out = "('" + name + "','" + description + "'," + userID + ")";
		return out;
	}
}
