package packcheck; 

import java.util.ArrayList;
import java.util.Arrays;

import packnetwork.*; 

public class NetworkCheck {
	
	public static void main(String[] args) {		
		Network network = Network.getInstance();
		
		// Clear network
		network.clearNetwork();

		// Create 3 people
		Person p1 = new Person("Ed63", "Eddard", "Stark", "17-4-1959", "male", "Winterfell", "Winterfell",new ArrayList<String>(Arrays.asList("Sheffield")), new ArrayList<String>(Arrays.asList("Winterfell")), new ArrayList<String>(Arrays.asList("The Empire Strikes Back", "Independence day")), "G61267");
		Person p2 = new Person("Rob83");
		Person p3 = new Person("Arya89", "", "", "", "", "", "", null, null, null, "");
		
		// Add people to the network
		network.addPerson(p1);
		network.addPerson(p2);
		network.addPerson(p3);
		
		// Add relationships
		network.addRelationship(p1, p2);
		network.addRelationship(p2, p3);
		
		// Print network
		System.out.println(network);
		
		// Count people inside the network
		System.out.println("Number of people inside the network: " + network.getNumberOfPeople());
	}
}