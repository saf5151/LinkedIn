package DataGenerator;

import java.util.Random;
import java.io.*;

public class Skill extends Entry implements Reader{
	public static int lastID = 0;
	public static String lastName = "";
	
	private static String names[];
	public String getTable(){
		return "Skill";
	}
	
	public void setup() {
		
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
		lastName = name;
		String description = Jackson.saySomething();
		int userID = new Random().nextInt(Employee.numUsers);
		lastID = userID;
		
		out = "('" + name + "','" + description + "'," + userID + ")";
		return out;
	}
}
