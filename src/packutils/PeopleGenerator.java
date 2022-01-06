package packutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import packnetwork.Person;

/**
 * This class is used to generate random data for the Social Network
 * @author Oihan and Eneko
 *
 */
public class PeopleGenerator {
	
	// Singleton instance
	public static PeopleGenerator instance;
	
	// database paths
	String[] names;
	String[] surnames;
	String[] places;
	String[] movies;
	
	static Random randnum; // Random seed
	
	/**
	 * Reads all files containing the random data and loads them into the instance.
	 * @throws IOException BufferedReader/FileReader exception handling.
	 */
	private PeopleGenerator() throws IOException {
		BufferedReader br;

		// read names db
		br = new BufferedReader(new FileReader("db/names.txt"));
		// get number of elements (first line)
		names = new String[Integer.parseInt(br.readLine())];
		for (int i = 0; i < names.length; i++) 
			names[i] = br.readLine();
		br.close();
		
		
		// Read surnames db
		br = new BufferedReader(new FileReader("db/surnames.txt"));
		// get number of elements (first line)
		surnames = new String[Integer.parseInt(br.readLine())];
		for (int i = 0; i < surnames.length; i++) 
			surnames[i] = br.readLine();
		br.close();
		
		// Read places
		br = new BufferedReader(new FileReader("db/places.txt"));
		// get number of elements (first line)
		places = new String[Integer.parseInt(br.readLine())];
		for (int i = 0; i < places.length; i++) 
			places[i] = br.readLine();
		br.close();
		
		// Read movies
		br = new BufferedReader(new FileReader("db/movies.txt"));
		// get number of elements (first line)
		movies = new String[Integer.parseInt(br.readLine())];
		for (int i = 0; i < movies.length; i++) 
			movies[i] = br.readLine();
		br.close();
		
		// Initialise random seed
		randnum = new Random();
	}
		
	/**
	 * Returns the unique instance of peopleGenerator.
	 * @return the unique instance of the people generator.
	 * @throws IOException.
	 */
	public static PeopleGenerator getInstance() throws IOException {
		if (instance == null) instance = new PeopleGenerator();
		return instance;
	}
	
	/**
	 * Generates a random Person object using the data obtained from the databases.
	 * @return a random Person object.
	 */
	public Person generateRandomPerson() {
		String name = names[randInt(0, names.length-1)];
		String id = name + randInt(0, 999);
		String surname = surnames[randInt(0, surnames.length-1)];
		String birthdate = String.format("%d-%02d-%02d", randInt(1820, 2005), randInt(1, 12), randInt(1, 28));
		int genderSelector = randInt(0, 1);
		String gender = genderSelector == 0 ? "male" : "female";
		String birthplace = places[randInt(0, places.length-1)];
		String home = places[randInt(0, places.length-1)];
		Set<String> studiedat = new HashSet<String>();
		for (int i = 0; i < randInt(0, 5); i++) 
			studiedat.add(places[randInt(0, this.places.length-1)]);
		Set<String> workplaces = new HashSet<String>();
		for (int i = 0; i < randInt(0, 5); i++) 
			workplaces.add(places[randInt(0, this.places.length-1)]);
		Set<String> movies = new HashSet<String>();
		for (int i = 0; i < randInt(0, 5); i++) 
			movies.add(this.movies[randInt(0, this.movies.length-1)]);
		String groupcode = "G" + randInt(1000, 9999);
		
		
		return new Person(id, name, surname, birthdate, gender, birthplace, home, 
				new ArrayList<String>(studiedat), 
				new ArrayList<String>(workplaces), 
				new ArrayList<String>(movies), groupcode);
	}
	
	/**
	 * Generates a file with random people data.
	 * @param n the number of people.
	 * @throws IOException BufferedWriter/FileWriter exception handling.
	 */
	public void generateRandomPeopleFile(int n) throws IOException {
		this.generateRandomPeopleFile(n, "randomPeople.txt");
	}
	
	public void generateRandomPeopleFile(int n, String fname) throws IOException {
		BufferedWriter wr = new BufferedWriter(new FileWriter(fname));
		wr.write("idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode\n");
		for (int i = 0; i < n; i++)
			wr.write(generateRandomPerson().toString() + "\n");
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
}
