package DataGenerator;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Main {
	
	public static void setup(Statement stmt){
		System.out.println("Data generator started");
		Entry[] things = new Entry[10];
		int[] amount = new int[10];
		things[0] = new Phone();
		amount[0] = 1000;
		things[1] = new Past_Job();
		amount[1] = 400;
		things[2] = new Follows();
		amount[2] = 1000;
		things[3] = new Office();
		amount[3] = 200;
		things[4] = new Comp_Phone();
		amount[4] = 300;
		things[5] = new Associates();
		amount[5] = 300;
		things[6] = new Job();
		amount[6] = 100;
		things[7] = new Employs();
		amount[7] = 300;
		things[8] = new Review();
		amount[8] = 500;
		things[9] = new Connections();
		amount[9] = 1000;
		
		User user = new User();
		Employee employee = new Employee();
		employee.setup();
		
		Company company = new Company();
		company.setup();
		
		Skill skill = new Skill();
		skill.setup();
		
		Endorses endorses = new Endorses();
		
		Jackson.setup();
		Role.setup();
		Place.setup();
		
		user.setType(1);
		for(int i = 0; i < 100; i++){
			add(user,stmt);
			add(company,stmt);
		}
		System.out.println("Finished Company");
		
		user.setType(0);
		for(int i = 0; i < 500; i++){
			add(user,stmt);
			add(employee,stmt);
		}
		
		for(int j = 0; j < 10; j++){
			for(int i = amount[j]; i > 0; i--){
				add(things[j],stmt);
			}
		}
		
		Random rand = new Random();
		for(int i = 0; i < 2000; i++) {
			add(skill, stmt);
			if (rand.nextBoolean()) {
				if (rand.nextBoolean()) {
					add(endorses, stmt);
				}
			}
		}
	}
	public static void add(Entry in, Statement stmt){
		String query = "insert into " + in.getTable() + " values " + in.value();
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("sql error");
		}
	}
}
