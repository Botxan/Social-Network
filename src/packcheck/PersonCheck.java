package packcheck;

import java.util.ArrayList;
import java.util.Arrays;

import packnetwork.Person;

public class PersonCheck {
	public static void main(String[] args) {	
		
		// Create 3 people
		Person p1 = new Person("Ed63", "Eddard", "Stark", "17-4-1959", "male", "Winterfell", "Winterfell",new ArrayList<String>(Arrays.asList("Sheffield")), new ArrayList<String>(Arrays.asList("Winterfell")), new ArrayList<String>(Arrays.asList("The Empire Strikes Back", "Independence day")), "G61267");
		Person p2 = new Person("Rob83");
		Person p3 = new Person("Arya89", "", "", "", "", "", "", null, null, null, "");
		
		// Print people
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		// getters and setters
		System.out.println("\n** Testing getters and setters with " + p1.getID() + "... **");
		p1.setFirstName("new " + p1.getFirstName());
		p1.setLastName("new " + p1.getLastName());
		p1.setBirthdate("new " + p1.getBirthdate());
		p1.setGender("new " + p1.getGender());
		p1.setBirthplace("new " + p1.getBirthplace());
		p1.setHome("new " + p1.getHome());
		p1.removeStudiedat(0);
		p1.addStudiedat("new studiedat");
		p1.removeWorkedat(0);
		p1.addWorkedat("new workedat");
		p1.removeMovie(0);
		p1.addMovie("new movie");
		p1.setGroupcode("new " + p1.getGroupcode());
		
		// Print changed person
		System.out.println("\nPerson " + p1.getID() + " after changes: ");
		System.out.println(p1);
		
		// Check equals override
		System.out.println(new Person(p1.getID()).equals(p1));
	}
}
