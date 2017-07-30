package DataGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Jackson {
	public static BufferedReader quotes;
	public static String saySomething(){
		try {
			String line = null;
			if((line = quotes.readLine()) == null){
				setup();
				return quotes.readLine();
			}else{
				return line;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public static void setup(){
		String fileName = "C:\\Users\\Scott\\Dropbox\\CSCI 320\\LinkedIn\\src\\DataGenerator\\Jackson.txt";
        
		try {
            FileReader fileReader = 
                new FileReader(fileName);

            quotes = 
                new BufferedReader(fileReader);       
        }catch(Exception e) {
            System.out.println("Error");                
        }
	}
}
