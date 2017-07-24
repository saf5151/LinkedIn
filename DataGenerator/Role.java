import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Role {
	public static String role1[];
	public static String role2[];
	public static String role3[];
	public static String title(){
		String out = "";
		Random rand = new Random();
		out += role1[rand.nextInt(DataData.ROLE1)] + " ";
		out += role2[rand.nextInt(DataData.ROLE2)] + " ";
		out += role3[rand.nextInt(DataData.ROLE3)];
		return out;
	}
	
	public static void setup(){
		role1 = new String[DataData.ROLE1];
		role2 = new String[DataData.ROLE2];
		role3 = new String[DataData.ROLE3];

        String line = null;
		
		try {
			String fileName = "Role1";
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            int i = 0;
            while((line = bufferedReader.readLine()) != null){
                role1[i] = line;
                i++;
            }   
            
            bufferedReader.close();
            
            fileName = "Role2";
            fileReader = new FileReader(fileName);

            bufferedReader = new BufferedReader(fileReader);
            
            i = 0;
            while((line = bufferedReader.readLine()) != null){
                role2[i] = line;
                i++;
            }
            
            bufferedReader.close();
            
            fileName = "Role3";
            fileReader = new FileReader(fileName);

            bufferedReader = new BufferedReader(fileReader);
            
            i = 0;
            while((line = bufferedReader.readLine()) != null){
                role3[i] = line;
                i++;
            }
            
            bufferedReader.close();
            
        }catch(Exception e){
            System.out.println("Error");                
        }
	}
}
