package DataGenerator;

import java.util.Random;

public class User extends Entry{
	public static int numUs = 0;
	public static String email = "";
	public int type = 0;

	public String value() {
		String out;
		Random rand = new Random();
		
		numUs++;
		String password = "";
		for(int i = 0; i < 8; i++){
			password += ('a' + rand.nextInt(25));
		}
		
		String searching = "";
		if(type == 0){
			searching = "Employee";
		}else{
			searching = "Company";
		}
		
		int num = numUs + 300;
		String email = "";
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
		out = "('" + email + "','" + password + "','" + searching + "')";
		return out;
	}
	
	public void setType(int s){
		type = s;
	}
	public static String getEmail(){
		return email;
	}

	public String getTable() {
		return "Employee";
	}
}
