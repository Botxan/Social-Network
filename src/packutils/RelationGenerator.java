package packutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class RelationGenerator {
	
	static Random randnum; // Random seed
	
	public static void main(String[] args) throws IOException {
		PeopleGenerator pplgenerator = PeopleGenerator.getInstance();
		pplgenerator.generateRandomPeopleFile(5);
		generateRandomRelationshipsFile(new File("randomPeople.txt"));
		// Initialise random seed
	}
	
	/**
	 * Given a file with people, creates a file with random relations between them
	 * @param people
	 * @throws IOException 
	 */
	public static void generateRandomRelationshipsFile(File people) throws IOException {
		generateRandomRelationshipsFile(people, "randomFriends.txt");
	}
	
	/**
	 * Given a file with people, creates a file with random relations between them
	 * @param people
	 * @param output the output file name
	 * @throws IOException 
	 */
	public static void generateRandomRelationshipsFile(File people, String output) throws IOException {
		randnum = new Random();
		List<String> ids = new ArrayList<String>();
		
		// read people file
		Scanner sc = new Scanner(people).useDelimiter(",");
		// skip header
		sc.nextLine();
	    while (sc.hasNext()) {
	    	// obtain each id
	    	ids.add(sc.next());
	    	sc.nextLine();
	    }
	    System.out.println("Done");
	    sc.close();
	    
	    // Create a set with all the relations
	    Set<Relation> relations = new HashSet<Relation>();
	    int addRelation;
	    for (int i = 0; i < ids.size(); i++) {
	    	for (int j = i+1; j < ids.size(); j++) {
	    		addRelation = randInt(0, 1);
	    		if (addRelation == 1) relations.add(new Relation(ids.get(i), ids.get(j)));
	    	}
	    }
	    
	    // write relationships to a file
	    BufferedWriter wr = new BufferedWriter(new FileWriter(output));
	    wr.write("friend1,friend2\n");
	    for (Relation relation: relations)
	    	wr.write(relation.p1 + "," + relation.p2 + "\n");
	    wr.close();   
	}
	
	/**
	 * Returns a random integer within the given bounds.
	 * @param min lower bound.
	 * @param max upper bound.
	 * @return the random number.
	 */
	private static int randInt(int min, int max) {
		return randnum.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Represents a relation between two people
	 * @author Oihan and Eneko
	 */
	static class Relation {
	    protected String p1;
	    protected String p2;

	    public Relation(String p1, String p2) {
	        this.p1 = p1;
	        this.p2 = p2;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) return false;
	        if (getClass() != obj.getClass()) return false;
	        
	        final Relation otherRelation = (Relation) obj;
	        
	        return this.p1.equals(otherRelation.p1) && this.p1.equals(otherRelation.p2) &&
	        	this.p2.equals(otherRelation.p1) && this.p2.equals(otherRelation.p2);
	    }
	    
	    @Override
	    public int hashCode() {
	    	if (p1.compareTo(p2) > 0) return Objects.hash(p1, p2);
	    	else return Objects.hash(p2, p1);	
	    }
	}
}
