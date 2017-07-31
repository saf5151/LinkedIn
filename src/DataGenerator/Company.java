package DataGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Company extends Entry implements Reader{
	private static String names[];
	public static int numComps = 0;
	public String getTable(){
		return "Company";
	}
	
	public void setup() {
		
		String fileName = DataData.PATH + "\\DataGenerator\\CompanyNames.txt";

        String line = null;
        
        names = new String[DataData.COMPS];
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
        }
	}
	
	public static String getCompanyName(){
		return names[numComps-1];
	}
	public String value(){
		String out;
		
		int ID = numComps;
		numComps++;
		String name = getCompanyName();
		String description = Jackson.saySomething();
		int hiring = new Random().nextInt(2);
		String hiringS = "";
		if(hiring == 1){
			hiringS = "TRUE";
		}else{
			hiringS = "FALSE";
		}
		String email = User.getEmail();
		
		out = "(" + ID + ",'" + name + "','" + description + "','" + email + "'," + hiringS + ")";
		
		return out;
	}
}
