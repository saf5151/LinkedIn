package DataGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Employee extends Entry implements Reader{
	public static int numUsers = 0;
	public static String first[];
	public static String last[];
	public static String college[];
	public static String majors[];
	public static String getFirst(){
		return first[new Random().nextInt(DataData.NAMES)];
	}
	public static String getLast(){
		return last[new Random().nextInt(DataData.NAMES)];
	}
	public static String getCollege(){
		return college[new Random().nextInt(DataData.COLLEGES)];
	}
	public static String getMajor(){
		return majors[new Random().nextInt(DataData.MAJORS)];
	}
	
	public void setup(){
		first = new String[DataData.NAMES];
		last = new String[DataData.NAMES];
		college = new String[DataData.COLLEGES];
		majors = new String[DataData.MAJORS];

        String line = null;
		
		try {
			String fileName = "Names";
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            int i = 0;
			StringTokenizer defaultTokenizer;
            while((line = bufferedReader.readLine()) != null){
				defaultTokenizer = new StringTokenizer(line);
                first[i] = defaultTokenizer.nextToken();
				last[i] = defaultTokenizer.nextToken();
                i++;
            }   
            
            bufferedReader.close();
			
			fileName = "Colleges";
            fileReader = 
                new FileReader(fileName);

            bufferedReader = 
                new BufferedReader(fileReader);
            
            i = 0;
            while((line = bufferedReader.readLine()) != null){
                college[i] = line;
                i++;
            }
            
            bufferedReader.close();
            
			fileName = "Majors";
            fileReader = 
                new FileReader(fileName);

            bufferedReader = 
                new BufferedReader(fileReader);
            
            i = 0;
            while((line = bufferedReader.readLine()) != null){
                majors[i] = line;
                i++;
            }
            
            bufferedReader.close();
            
        }catch(Exception e){
            System.out.println("Error");                
        }
	}

	public String value() {
		String out;
		Random rand = new Random();
		
		numUsers++;
		int ID = numUsers;
		String name = getFirst() + " " + getLast();
		int gpa1 = rand.nextInt(3) + 1;
		int gpa2 = rand.nextInt(10);
		String city = Place.getCity();
		String state = Place.getState();
		String email = "";
		int num = numUsers;
		int space = 1;
		char n = 'a';
		while(num > 0){
			while(num % (space * 26) != 0){
				num -= space;
				n++;
			}
			email += n;
			space *= 26;
			n = 'a';
		}
		email += "@gmail.com";
		email = User.getEmail();
		int s = rand.nextInt(2);
		String alma = getCollege();
		String major = getMajor();
		int month = rand.nextInt(9) + 1;
		int day = rand.nextInt(18) + 10;
		int year = rand.nextInt(50) + 1950;
		String DOB = "DATE '" + year + "-0" + month + "-" + day + "'";
		String searching = "";
		if(s == 0){
			searching = "TRUE";
		}else{
			searching = "FALSE";
		}
		
		out = "(" + ID + ",'" + name + "','" + alma + "','" + major + "'," + gpa1 + "." + gpa2 + ",'" + email + "'," + DOB + ",'" + city + "','" + state + "'," + searching + ")";
		return out;
	}

	public String getTable() {
		return "Employee";
	}
}
