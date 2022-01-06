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
import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import exceptions.*;

/**
 * Represents a social network where people can make relationships
 * @author Oihan and Eneko
 *
 */
public class Network {
	
	// Singleton instance
	private static Network instance;
	
	// Map to map id to Person
	private Map<String, Person> people;
		
	// Map to store relationships (edges) in the network (graph)
	private Map<String, LinkedList<String>> relations;
	
	// Map to relate component id to each member of the component
	private Map<Integer, List<String>> componentIDs;
	
	// DFS Longest path
	private List<String> longestPath;

	
	// Cliques in the network
	private List<List<String>> cliques;
	
	private Network() {
		relations = new HashMap<String, LinkedList<String>>();
		people = new HashMap<String, Person>();
		componentIDs = new HashMap<Integer, List<String>>();
	}
	
	/**
	 * Returns the unique instance of SocialNetwork.
	 * @return the unique instance of the social network
	 */
	public static Network getInstance() {
		if (instance == null) instance = new Network();
		return instance;
	}
	
	/**
	 * Resets the Social Network instance.
	 */
	public void reset() {
		instance = null;
	}
	
	/**
	 * Returns the person with the ID passed by parameter.
	 * @param id the person's id
	 * @return the person whose id is the one passed by parameter
	 * @throws ElementNotFoundException if a person with the given id is not found
	 */
	public Person getPerson(String id) throws ElementNotFoundException {
		if (!people.containsKey(id))
			throw new ElementNotFoundException("network");
		return people.get(id);
	}
	
	/**
	 * Retrieves all the relations of one person
	 * @param id the person
	 * @return all the relations of one person
	 */
	public List<String> getRelations(String id) {
		return relations.get(id);
	}
	
	/**
	 * Adds to the network the person passed by parameter.
	 * @param p the person to be added to the network
	 * @throws ElementAlreadyExistsException when the person to be added is already in the network
	 */
	public void addPerson(Person p) throws ElementAlreadyExistsException {
		if (people.containsKey(p.getID()))
			throw new ElementAlreadyExistsException("network");
		
		// create a new component for the person
		LinkedList<String> members = new LinkedList<String>();
		members.add(p.getID());
		componentIDs.put(componentIDs.size(), members);
		
		// Assign a component to the person
		p.setComponentID(componentIDs.size()-1);
				
		people.put(p.getID(), p);
		relations.put(p.getID(), new LinkedList<String>());
		
	}
	
	/**
	 * Reads people data from the file passed by parameter and loads it into the network.
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
	 * @param id1 1st person ID
	 * @param id2 2nd person ID
	 * @throws ElementNotFoundException when either of the two people is not inside the network
	 */
	public void addRelationship(String id1, String id2)
			throws ElementNotFoundException, InvalidRelationshipException {
		if (!people.containsKey(id1) || !people.containsKey(id2)) 
			throw new ElementNotFoundException("network");
		if (relations.get(id1).contains(id2) || relations.get(id2).contains(id1)) 
			throw new InvalidRelationshipException("relation already exists");
		if (id1.equals(id2))
			throw new InvalidRelationshipException("same person");
			
		relations.get(id1).add(id2);
		relations.get(id2).add(id1);
		
		// Compute the new component
		Person p1 = people.get(id1);
		Person p2 = people.get(id2);
		
		if (p1.getComponentID() != p2.getComponentID()) {			
			int newGroup = Math.min(p1.getComponentID(), p2.getComponentID());	
			updateGroup(Math.max(p1.getComponentID(), p2.getComponentID()), newGroup);
		}		
	}
	
	public void updateGroup(int oldGroupID, int newGroupID) {
		// change the component id property of each person in the old group
		for (String pId: componentIDs.get(oldGroupID)) 
			people.get(pId).setComponentID(newGroupID);
		
		// move the old group to the new one in the map
		componentIDs.get(newGroupID).addAll(componentIDs.get(oldGroupID));
		
		// remove the old group (duplicated) from the map
		componentIDs.remove(oldGroupID);
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
			addRelationship(id1, id2);
		}
		
		reader.close();
	}
	
	/**
	 * Gets the number of people inside the network.
	 * @return the number of people inside the network
	 */
	public int getNumberOfPeople() {
		return people.size();
	}
	
	/**
	 * Gets the total number of relationships inside the network.
	 * @return the total number of relationships inside the network
	 */
	public int getNumberOfRelationships() {
		int count = 0;
		for (String id: relations.keySet()) count += relations.get(id).size();		
		return count/2;
	}
	
	/**
	 * Checks whether or not a person is already inside the network.
	 * @param id the target person's id
	 * @return true iff the person is inside the network
	 */
	public boolean hasPerson(String id) {
		return people.containsKey(id);
	}
	
	/**
	 * Checks whether or not a relationship exists between 2 people.
	 * @param id1 1st person id
	 * @param id2 2nd person id
	 * @return true iff a relationship exist
	 * @throws ElementNotFoundException when either of the two people is not inside the network
	 */
	public boolean hasRelationship(String id1, String id2) throws ElementNotFoundException {
		if (!people.containsKey(id1) || !people.containsKey(id2))
			throw new ElementNotFoundException("Person");
		
		return relations.get(id1).contains(id2);
	}
	
	/**
	 * Deletes the network.
	 */
	public void clearNetwork() {
		relations.clear();
		people.clear();
	}
	
	/**
	 * Returns a list of people that matches a value in a given attribute.
	 * @param personToCompare the person to be compared
	 * @param c the comparator
	 * @return list of people that matches a value in a given attribute
	 */
	public LinkedList<String> findPeople(Person personToCompare, Comparator<Person> c) {
		LinkedList<String> result = new LinkedList<String>();
		
		for (Person p: people.values()) 	
			if (c.compare(p, personToCompare) == 0) result.add(p.getID());
		
		return result;
	}
	
	/**
	 * Given 2 dates, return.s a list of people id's where their birtplace is between
	 * these 2 dates.
	 * @param date1 lowest date
	 * @param date2 highest date
	 * @return a list of people id's where their birthplace is between these 2 dates
	 */
	public LinkedList<String> findSortedPeopleBetweenDates(String date1, String date2) {
		LinkedList<Person> people = findPeopleBetweenDates(date1, date2);
		Collections.sort(people, PersonComparator.byBSN);
		LinkedList<String> result = new LinkedList<String>();
		for (Person p: people)
			result.add(p.getID());
		
		return result;
	}
	
	/**
	 * Auxiliary function to find people between 2 dates (disregarding month and day).
	 * @param date1 lower bound
	 * @param date2 upper bound
	 * @return people who were born between the dates passed by parameter
	 */
	private LinkedList<Person> findPeopleBetweenDates(String date1, String date2) {
		date1 = date1.substring(date1.length()-4);
		date2 = date2.substring(date2.length()-4);
		
		LinkedList<Person> result = new LinkedList<Person>();
		for (Person p: people.values()) {
			String birthdate = p.getBirthdate();
			if (!birthdate.equals("")) {
				String year = birthdate.substring(birthdate.length()-4);
				if (year.compareTo(date1) >= 0 && year.compareTo(date2) <= 0) 
					result.add(p);
			}
		}
		
		return result;
	}
	
	/**
	 * Given a file with people id's returns a list of people which its birthplace
	 * matches the hometown of the people in the file.
	 * @param f the output file
	 * @return a string representation of the people which its birthplace matches
	 * the hometown of the people in the file
	 */
	public String findByResidenceFile(File f) {
		BufferedReader reader;
		String result = "";
		String id;
		Person p;
		try {
			reader = new BufferedReader(new FileReader(f));
			// Find people in network whose birthplace matches the hometown of people retrieved from the file
			while ((id = reader.readLine()) != null) {
				result += "\nPeople that its birthplace matches the hometown of " + id + ":\n";
				p = getPerson(id);
				result += toString(findPeople(p, PersonComparator.byBirthplaceAndHome), new int[] {6, 2, 1});
			}
			reader.close();
		} catch (IOException | ElementNotFoundException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	/**
	 * Returns a list with all friends associated with a give person.
	 * @param p the person
	 * @return a set of ids of people that are friends of the person
	 * passed by parameter
	 */
	public LinkedList<String> findFriends(String p) {
		if (!people.containsKey(p))
			throw new ElementNotFoundException("network");
		
		return new LinkedList<String>(relations.get(p));
	}
	
	/**
	 * Print to stdout people's data
	 * @param peopleIDs people's id to get printed out
	 * @param attributes the attributes that are printed out
	 */
	public void printPeople(LinkedList<String> peopleIDs, int[] attributes) {
		System.out.println(peopleIDs.toString());
	}
	
	/**
	 * Returns a map with the users split in same profiles.
	 * Two users have the same profile if they match the same collection of
	 * favourite movies
	 * @return a map with the users split int same profiles
	 */
	public HashMap<String, LinkedList<String>> groupByMovies() {
		HashMap<String, LinkedList<String>> classes = new HashMap<String, LinkedList<String>>();
		for (Person p: people.values()) {
			String movies = "[" + String.join(" | ", p.getMovies()).toLowerCase() + "]";
			if (classes.containsKey(movies)) 
				classes.get(movies).add(p.getID());
			else 
				classes.put(movies, new LinkedList<String>(Arrays.asList(p.getID())));
		}
		
		return classes;
	}
	
	/**
	 * Finds the shortest path between 2 people
	 * @param source first person
	 * @param target second person
	 * @throws InvalidPersonIdException if any of the id is invalid
	 */
	public void shortestPath(String source, String target) throws InvalidPersonIdException {
		Queue<String> q = new LinkedList<String>();
		Map<String, String> edgeTo = new HashMap<String, String>();
		boolean found = false;
		
		if (!relations.containsKey(source) || !relations.containsKey(target))
			throw new InvalidPersonIdException();
		
		q.add(source);
		edgeTo.put(source, null);
		
		while (!q.isEmpty() && !found) {
			String parent = q.poll();
			if (parent.equals(target)) found = true;
			else 
				// add friends to the queue if not added yet
				for (String adj: relations.get(parent))
					if (!edgeTo.containsKey(adj)) {
						// add friend to the queue
						q.add(adj);
						edgeTo.put(adj, parent);
					}
		}
		if (found) {
			// Reorder the path using a stack
			Stack<String> path = new Stack<String>();
			String current = target;
			while (current != null) {
				path.push(current);
				current = edgeTo.get(current);
			}
			// Print the shortest path
			System.out.println("Shortest chain length: " + path.size());
			while (path.size() > 1) System.out.print(path.pop() + " -> ");
			System.out.println(path.pop());
		} else System.out.println("There is no relationship between " + source + " and " + target + ".");
	}
	
	/**
	 * Returns the longest chain of relationships between 2 people
	 * @param source the person from where start the chain
	 * @param target the last person of the chain
	 * @throws InvalidPersonIdException
	 */
	public void longestPath(String source, String target) throws InvalidPersonIdException {		
		if (!relations.containsKey(source) || !relations.containsKey(target))
			throw new InvalidPersonIdException();
		
		// Check if source and target are in the same connected component
		if (people.get(source).getComponentID() != people.get(source).getComponentID()) {
			System.out.println("There is no relationship between " + source + " and " + target + ".");
		} else {			
			// initialise longest path as empty
			longestPath = new ArrayList<String>();
			// initialise the list to save each recursive try of the longest path
			List<String> path = new ArrayList<String>();
			
			// find the longest path
			longestPathWorker(source, target, path);
	
			// print the path
			while(longestPath.size() > 1) System.out.print(longestPath.remove(0) + " -> ");
			System.out.println(longestPath.remove(0));		
		}
		
		
	}
	
	/**
	 * Worker recursive function for finding the longest chain between 2 people.
	 * @param current the current person of the chain
	 * @param target the last person of the chain
	 * @param path the current try of the longest path
	 */
	public void longestPathWorker(String current, String target, List<String> path) {
		// if already found the longest path, stop recursion
		if (longestPath.size() == componentIDs.get(people.get(current).getComponentID()).size()) return;
		
		path.add(current);
		if (current.equals(target)) {
			if (path.size() > longestPath.size()) longestPath = new ArrayList<String>(path);
		} else {
			for (String adj: relations.get(current)) 
				if (!path.contains(adj)) longestPathWorker(adj, target, path);			
		}
		// After all adjacent nodes have been tried, delete the current node from the list (backtracking)
		path.remove(path.size()-1);
	}
	
	/**
	 * Finds all cliques in the network.
	 */
	private void findCliques() {
		// reset the cliques
		cliques = new LinkedList<List<String>>();
		
		// find cliques in each component
		for (Integer componentID: componentIDs.keySet()) {
			List<String> R = new ArrayList<String>();
			List<String> P = new ArrayList<String>(componentIDs.get(componentID));
			List<String> X = new ArrayList<String>();
			findCliquesInComponent(R, P, X);
		}
	}

	/**
	 * Prints every clique in a connected component using Bron Kerbosch algorithm.
	 * @param component list of connected people
	 */
	private void findCliquesInComponent(List<String> R, List<String> P, List<String> X) {
		if (P.isEmpty() && X.isEmpty() &&  R.size() > 4) cliques.add(R);
		
		while (!P.isEmpty()) {
			String p = P.get(0);
			// add the neighbour to R
			List<String> newR = new ArrayList<String>(R);
			newR.add(p);
			
			// Find neighbours of current adj person
			List<String> adjNeighbours = relations.get(p);
			
			// find common neighbours of v that are on P
			List<String> commonP = findCommonNeighbours(P, adjNeighbours);
			
			
			// find common neighbours of v that are on P
			List<String> commonX = findCommonNeighbours(X, adjNeighbours);
			// Recursive call
			findCliquesInComponent(newR, commonP, commonX);
			
			// Move adj from P to X, as it has been used
			P.remove(p);
			X.add(p);
		}
	}
	
	/**
	 * Finds common adjacent vertices between 2 group of vertices
	 * @return
	 */
	private List<String> findCommonNeighbours(List<String> list1, List<String> list2) {
		boolean found;
		int j;
		List<String> result = new ArrayList<String>();
		
		for (int i = 0; i < list1.size(); i++) {
			found = false;
			j = 0;
			while (!found && j < list2.size()) {
				if (list1.get(i).equals(list2.get(j))) {
					found = true;
					result.add(list1.get(i));			
				}
				j++;
			}
		}
		
		return result;
	}
	
	/**
	 * Prints all cliques to the stdout.
	 */
	public void printCliques() {
		// obtain the cliques
		findCliques();
		
		// print
		for (List<String> clique: cliques) {
			System.out.print("Clique: ");
			for (String p: clique)
				System.out.print(p + " ");
			System.out.println();
		}
	}
	
	
	/**
	 * Print out to a file people's data.
	 * @param peopleIDs people id's to get printed out
	 * @param attributes the attributes that are printed out
	 * @param f output file
	 * @throws IOException when the file to write is not found
	 */
	public void printPeople(LinkedList<String> peopleIDs, int[] attributes, File f) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write(peopleIDs.toString());
		writer.close();
	}
	
	/**
	 * Prints in the standard input all the people inside the network.
	 */
	public void printNetwork() {
		for (Person p: people.values()) 
			System.out.println(p.toString());
	}
	
	/**
	 * Prints out to the file passed by parameter all the people inside the network.
	 * @param f output file
	 * @throws IOException when the target file can not be found
	 */
	public void printNetwork(File f) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write("idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode\n");
		for (Person p: people.values()) 
			writer.write(p.toString() + "\n");
		
		writer.close();
	}
	
	/**
	 * Returns a string representation with the list of people passed
	 * by parameter. Only the attributes passed by parameter are printed.
	 * @param peopleIDs list of id's of people inside the network
	 * @param attributes attributes of the Person object to be printed
	 * @return an string representation of people passed
	 * by parameter. Only the attributes passed by parameter are printed.
	 */
	public String toString(LinkedList<String> peopleIDs, int[] attributes) {
		String result = "";
		for (String id: peopleIDs) {
			Person p = people.get(id);
			result += p.toString(attributes) + "\n";
		}
		
		return result;
	}
	
	/**
	 * Prints all the users as well as their relationships.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String p: relations.keySet()) 
			System.out.println(p + ": " + String.join(", ", relations.get(p)));	
		
		return str.toString();
	}
}