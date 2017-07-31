package DataGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Place {
	public static String state[];
	public static String city[];
	public static String getState(){
		return state[new Random().nextInt(DataData.STATES)];
	}
	public static String getCity(){
		return city[new Random().nextInt(DataData.CITIES)];
	}
	public static void setup(){
		state = new String[DataData.STATES];
		city = new String[DataData.CITIES];

        String line = null;
		
		try {
			String fileName = DataData.PATH + "\\DataGenerator\\Cities.txt";
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            int i = 0;
            while((line = bufferedReader.readLine()) != null){
                city[i] = line;
                i++;
            }   
            
            bufferedReader.close();
            
            fileName = DataData.PATH + "\\DataGenerator\\States";
            fileReader = new FileReader(fileName);

            bufferedReader = new BufferedReader(fileReader);
            
            i = 0;
            while((line = bufferedReader.readLine()) != null){
                state[i] = line;
                i++;
            }
            
            bufferedReader.close();
           
            
        }catch(Exception e){
            System.out.println("Error");                
        }
	}
	public static boolean isState(String in){
		for(int i = 0; i < 50; i++){
			if(in.equals(state[i])){
				return true;
			}
		}
		return false;
	}
}
