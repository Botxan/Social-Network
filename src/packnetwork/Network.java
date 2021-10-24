package packnetwork;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import exceptions.*;

/**
 * Represents a social network where people can make relationships
 * @author Oihan
 *
 */
public class Network {
	
	// Hashmap to store relationships (edges) in the network (graph)
	private Map<Person, List<String>> network;
	
	// Singleton instance
	private static Network instance;
	
	private Network() {
		network = new HashMap<>();	
	}
	
	/**
	 * Returns the unique instance of SocialNetwork
	 * @return the unique instance of the social network
	 */
	public static Network getInstance() {
		if (instance == null) instance = new Network();
		return instance;
	}
	
	/**
	 * Resets the Social Network instance
	 */
	public void reset() {
		instance = null;
	}
	
	/**
	 * Adds to the network the person passed by parameter.
	 * @param p the person to be added to the network.
	 * @throws ElementAlreadyExistsException when the person to be added is already in the network.
	 */
	public void addPerson(Person p) throws ElementAlreadyExistsException {
		if (network.containsKey(p))
			throw new ElementAlreadyExistsException("network");
		network.put(p, new LinkedList<String>());
	}
	
	/**
	 * Reads people data from the file passed by parameter and loads it into the network
	 * @param f the file with people data
	 * @throws IOException when the target file has not been found
	 */
	public void loadPeople(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line;

		reader.readLine();
		// Read lines until end of file
		while((line = reader.readLine()) != null) {
			String[] p = line.split(",");
			Person person = new Person(p[0], p[1], p[2], p[3], p[4], p[5], p[6], 
					new ArrayList<>(Arrays.asList(p[7].split(";"))), 
					new ArrayList<>(Arrays.asList(p[8].split(";"))), 
					new ArrayList<>(Arrays.asList(p[9].split(";"))), 
					p[10]);
			// Add person to the network
			addPerson(person);
		}
		
		reader.close();
	}
	
	/**
	 * Adds the relationship between the 2 people passed by parameter.
	 * @param p1 person 1.
	 * @param p2 person 2.
	 * @throws ElementNotFoundException when either of the two people is not inside the network.
	 */
	public void addRelationship(Person p1, Person p2) 
			throws ElementNotFoundException, InvalidRelationshipException {
		if (!network.containsKey(p1) || !network.containsKey(p2)) 
			throw new ElementNotFoundException("network");
		if (network.get(p1).contains(p2.getID()) || network.get(p2).contains(p1.getID())) 
			throw new InvalidRelationshipException("relation already exists");
		if (p1.equals(p2))
			throw new InvalidRelationshipException("same person");
			
		network.get(p1).add(p2.getID());
		network.get(p2).add(p1.getID());
	}
	
	/**
	 * Loads relationships from external file.
	 * @param f the target file
	 * @throws IOException if the target file can not be found
	 */
	public void loadRelationships(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line;

		reader.readLine();
		// Read lines until end of file
		while((line = reader.readLine()) != null) {
			String[] p = line.split(",");
			String id1 = p[0];
			String id2 = p[1];

			// Add the relationship to the network
			addRelationship(new Person(id1), new Person(id2));
		}
		
		reader.close();
	}
	
	/**
	 * Gets the number of people inside the network.
	 * @return the number of people inside the network.
	 */
	public int getNumberOfPeople() {
		return network.keySet().size();
	}
	
	/**
	 * Gets the total number of relationships inside the network.
	 * @return the total number of relationships inside the network.
	 */
	public int getNumberOfRelationships() {
		int count = 0;
		for (Person p: network.keySet()) count += network.get(p).size();		
		return count/2;
	}
	
	/**
	 * Checks whether or not a person is already inside the network.
	 * @param p the target person
	 * @return true iff the person is inside the network.
	 */
	public boolean hasPerson(Person p) {
		return network.containsKey(p);
	}
	
	/**
	 * Checks whether or not a relationship exists between 2 people.
	 * @param p1 person 1.
	 * @param p2 person 2.
	 * @return true iff a relationship exist.
	 * @throws ElementNotFoundException when either of the two people is not inside the network.
	 */
	public boolean hasRelationship(Person p1, Person p2) throws ElementNotFoundException {
		if (!network.containsKey(p1) || !network.containsKey(p2))
			throw new ElementNotFoundException("Person");
		
		return network.get(p1).contains(p2.getID());
	}
	
	/**
	 * Deletes the network.
	 */
	public void clearNetwork() {
		network.clear();
	}
	
	/**
	 * Prints in the standard input all the people inside the network.
	 */
	public void printPeople() {
		for (Person p: network.keySet()) {
			System.out.println(p.toString());
		}
	}
	
	/**
	 * Prints out to the file passed by parameter all the people inside the network.
	 * @param f output file
	 * @throws IOException when the target file can not be found
	 */
	public void printPeople(File f) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write("idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode\n");
		for (Person p: network.keySet()) {
			writer.write(p.toString() + "\n");
		}
		
		writer.close();
	}
	
	/**
	 * Prints all the users as well as their relationships.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Person p: network.keySet()) {
			str.append(p.getID() + ": ");
			for (String id: network.get(p)) {
				str.append(id + " ");
			}
			str.append("\n");
		}
		
		return str.toString();
	}
}