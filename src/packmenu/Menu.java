package packmenu;
import packnetwork.Network;
import packnetwork.Person;
import packnetwork.PersonComparator;
import packutils.PeopleGenerator;
import packutils.RelationGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import exceptions.ElementNotFoundException;
import exceptions.InvalidPersonIdException;

public class Menu {
	// Network
	private static Network network = Network.getInstance();
	// File chooser
	private static JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	// Stdin reader
	private static Scanner sc;
	
	
	/* ***** MAIN MENU ***** */
	
	/**
	 * Invokes the main menu.
	 * @param args no args required.
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		// User selection
		MainMenuOption option = MainMenuOption.READY;
		
		while (option != MainMenuOption.QUIT) {
			System.out.println("+-------------- MENU --------------+");
			System.out.println("| 1 | Load people into the network  |");
			System.out.println("|-----------------------------------|");
			System.out.println("| 2 | Load relationships.           |");
			System.out.println("|-----------------------------------|");
			System.out.println("| 3 | Print out people.             |");
			System.out.println("|-----------------------------------|");
			System.out.println("| 4 | Print relationships           |");
			System.out.println("|-----------------------------------|");
			System.out.println("| 5 | Search                        |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 6 | Group by movies               |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 7 | Shortest path                 |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 8 | Longest path                  |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 9 | Cliques                       |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 10| Extras                        |");		
			System.out.println("|-----------------------------------|");
			System.out.println("| 11| Quit                          |");
			System.out.println("+-----------------------------------+");
			
			try {
				option = MainMenuOption.values()[sc.nextInt()];
				switch(option) {
					case LD_PEOPLE:
						loadPeople();	
						break;
						
					case LD_RELATIONS:
						loadRelations();
						break;
						
					case PRINT_PEOPLE:
						printPeople();
						break;
						
					case PRINT_RELATIONS:
						printRelations();
						break;
						
					case SEARCH:
						searchMenu();
						break;
									
					case GROUP_BY_MOVIES:
						groupByMovies();
						break;
					
					case SHORTEST_PATH:
						shortestChain();
						break;
						
					case LONGEST_PATH:
						longestChain();
						break;
						
					case CLIQUES:
						printCliques();
						break;
						
					case EXTRAS:
						extrasMenu();
						break;
						
					case QUIT: // Quit
						break;
						
					default:
						System.out.println("Invalid selection.");
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
	
	/* ***** </> MAIN MENU ***** */
	
	/* ***** SEARCH MENU ***** */
	
	/**
	 * Secondary menu for searching functions
	 * @param sc scanner
	 * @param network the social network
	 * @throws IOException if scanner input format is unexpected
	 */
	public static void searchMenu() throws IOException {
		SearchMenuOption option = SearchMenuOption.READY;
		String output = "";
		
		while(option != SearchMenuOption.BACK_TO_MAIN_MENU) {
			
			System.out.println("+---------- SEARCH MENU -----------+");
			System.out.println("|1 | Friends by last name          |");
			System.out.println("|----------------------------------|");
			System.out.println("|2 | By city                       |");
			System.out.println("|----------------------------------|");
			System.out.println("|3 | Between two dates             |");
			System.out.println("|----------------------------------|");
			System.out.println("|4 | By residence file             |");
			System.out.println("|----------------------------------|");
			System.out.println("|5 | Back to main menu             |");
			System.out.println("+----------------------------------+");
			
			option = SearchMenuOption.values()[sc.nextInt()];
			
			switch(option) {
				case BY_LAST_NAME:
					output = findFriendsByLastName();
					break;
					
				case BY_CITY: // By city
					output = findPeopleByCity();
					break;
					
				case BETWEEN_TWO_DATES: // Between dates
					output = findPeopleBetweenDates();
					break;
					
				case BY_RESIDENCE_FILE: // By residence file
					output = findPeopleByResidenceFile();
					break;
					
				case BACK_TO_MAIN_MENU: // Back to main menu
					break;
				
				default:
					System.out.println("Invalid selection.");
					break;
			}
			
			if (option != SearchMenuOption.BACK_TO_MAIN_MENU) {				
				if (output.isEmpty()) System.out.println("The search did not retrieve any result");
				else displayPrintOptions(output);	
			}
		}
	}
	
	/* ***** </> SEARCH MENU ***** */
	
	
	/* ***** EXTRAS MENU ***** */
	
	/**
	 * Secondary menu for social network extra add-ons.
	 * @throws IOException file read/write exception handling.
	 */
	public static void extrasMenu() throws IOException {
		ExtrasMenuOption option = ExtrasMenuOption.READY;
		
		while(option != ExtrasMenuOption.BACK_TO_MAIN_MENU) {
			
			System.out.println("+---------- EXTRAS MENU -----------+");
			System.out.println("|1 | Generate random people        |");
			System.out.println("|----------------------------------|");
			System.out.println("|2 | Generate random relationships |");
			System.out.println("|----------------------------------|");
			System.out.println("|3 | Back to main menu             |");
			System.out.println("+----------------------------------+");
			
			option = ExtrasMenuOption.values()[sc.nextInt()];
			
			switch(option) {
				case GENERATE_RANDOM_PEOPLE:
					generateRandomPeople();
					break;
				case GENERATE_RANDOM_RELATIONS:
					generateRandomRelations();
					break;
				case BACK_TO_MAIN_MENU: // Back to main menu
					break;
				default:
					System.out.println("Invalid selection.");
					break;
			}
		}
	}
	
	/* ***** </> EXTRAS MENU ***** */
	
	
	/* ***** MAIN MENU FUNCTIONS ***** */
	
	/**
	 * Loads people to the network from a file
	 */
	public static void loadPeople() {
		// Allow multi-file selection
		jfc.setMultiSelectionEnabled(true);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			for (int i = 0; i < files.length; i++)
				try {
					// Load each file to the network
					network.loadPeople(files[i]);
					System.out.println("People uploaded to the network successfully.");
				} catch (IOException e) {e.printStackTrace();}
		}		
	}
	
	/**
	 * Loads relations to the network from a file
	 */
	public static void loadRelations() {
		// Allow multi-file selection
		jfc.setMultiSelectionEnabled(true);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			for (int i = 0; i < files.length; i++)
				try {
					network.loadRelationships(files[i]);
					System.out.println("Relationships updated on the network successfully");
				} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	/**
	 * Print out to a file all people inside the network
	 */
	public static void printPeople() {
		try {
			network.printNetwork(new File("./people.txt"));
			System.out.println("People printed out to people.txt file");
		} catch (IOException e) {e.printStackTrace();}
	}
	
	/**
	 * Print relations in the stdin
	 */
	public static void printRelations() {
		System.out.println(network);
	}
	
	/**
	 * Prints groups of people with same profile
	 * Two users have the same profile if they match the same collection of favourite movies
	 */
	public static void groupByMovies() {
		String output = "";
		HashMap<String, LinkedList<String>> classes = network.groupByMovies();
		
		if (classes.isEmpty()) System.out.println("Network is empty");
		else {
			// Print each profile
			for (String classID: classes.keySet()) {
				output += "Class: " + classID + "\n";
				// Print each person on the profile
				for (String personID: classes.get(classID))
					output += personID + ", ";
				output = output.replaceAll(", $", "");
				output += "\n";
			}
		}
		
		displayPrintOptions(output);
	}
	
	/**
	 * Retrieves the shortest chain of friends between two given users
	 */
	public static void shortestChain() {
		System.out.print("Introduce the first user: ");
		String source = sc.next();
		
		System.out.print("Introduce the second user: ");
		String target = sc.next();
		
		try {
			network.shortestPath(source, target);
		} catch (InvalidPersonIdException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the longest chain of friends between two given users
	 */
	public static void longestChain() {
		System.out.print("Introduce the first user: ");
		String source = sc.next();
		
		System.out.print("Introduce the second user: ");
		String target = sc.next();
		
		try {
			network.longestPath(source, target);
		} catch (InvalidPersonIdException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void printCliques() {
		network.printCliques();
	}
	
	/* ***** </> MAIN MENU FUNCTIONS ***** */
	
	
	/* ***** SEARCH MENU FUNCTIONS ***** */
	
	/**
	 * Retrieves an string that contains all friends of people whose last name
	 * matches the one passed by stdin
	 * @return string with people with the selected surname and their friends
	 */
	public static String findFriendsByLastName() {
		String output = "";
		String lastName;
		Person p;
		LinkedList<String> peopleIDs;
		
		System.out.print("Insert a surname to search: ");
		lastName = sc.next();
		
		// Create an empty person and get all the people with same surname
		p = new Person("_");
		p.setLastName(lastName);
		peopleIDs = network.findPeople(p, PersonComparator.bySurname);
		
		if (!peopleIDs.isEmpty()) {	
			// Find friends
			for (String id: peopleIDs) {
				p = network.getPerson(id);
				output += "\nFriends of " + "@" + id + " (" + p.getFirstName() + " " + p.getLastName() + ")\n";
				LinkedList<String> friends = network.findFriends(id);
				output += network.toString(friends, new int[] {1, 2});
			}
		}

		return output;
	}
	
	/**
	 * Retrieves an string that contains people that its home
	 * matches the one passed by stdin
	 * @return people with the same home attribute as the one
	 * passed by parameter
	 */
	public static String findPeopleByCity() {
		String output = "";
		String city;
		Person p;
		LinkedList<String> peopleIDs;
		
		System.out.println("Insert a birthplace to search: ");
		city = sc.next();
		
		// Create an empty person and get all the people with the same home
		p = new Person("_");
		p.setBirthplace(city);
		peopleIDs = network.findPeople(p, PersonComparator.byBirthplace);
		
		if (!peopleIDs.isEmpty()) output = network.toString(peopleIDs, new int[] {0, 2});
		
		return output;
	}
	
	/**
	 * Given 2 dates, retrieves all people that its birthdate is between these the 2 dates
	 * passed by stdin
	 * @return a string representation of people that its birthdate is between the 2 dates
	 * passed by stdin
	 */
	public static String findPeopleBetweenDates() {
		String output = "";
		String date1, date2;
		LinkedList<String> peopleIDs;
		
		System.out.println("Insert the lowest date: ");
		date1 = sc.next();
		System.out.println("Insert the highest date: ");
		date2 = sc.next();
		
		peopleIDs = network.findSortedPeopleBetweenDates(date1, date2);
		
		if (!peopleIDs.isEmpty()) output = network.toString(peopleIDs, new int[] {0});
		
		return output;
	}
	
	public static String findPeopleByResidenceFile() {
		String output = "";
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				File file = jfc.getSelectedFile();
				output = network.findByResidenceFile(file);
			} catch (ElementNotFoundException e) { e.printStackTrace(); }
		}
		
		return output;
	}
	
	
	public static void displayPrintOptions(String output) {
		char printOption;
		String outFileName;
		BufferedWriter writer;
		
		// Print to stdout
		System.out.print("Print the result on the standard output? [Y/N]: ");
		printOption = sc.next().charAt(0);
		if (Character.toUpperCase(printOption) == 'Y') System.out.println(output);
		
		// Print out to a file
		System.out.print("\n\nPrint out the result to a file? [Y/N]: ");
		printOption = sc.next().charAt(0);
		if (Character.toUpperCase(printOption) == 'Y') {
			try {
				System.out.print("Select file name: ");
				outFileName = sc.next();
				writer = new BufferedWriter(new FileWriter(outFileName + ".txt"));
				writer.write(output);
				writer.close();
				System.out.println("Result saved in " + outFileName + " file.");
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	/* ***** </> SEARCH MENU FUNCTIONS ***** */
	
	
	/* ***** EXTRAS MENU FUNCTIONS ***** */
	
	/**
	 * Generates random people to an output file
	 * @throws IOException BufferedWriter/FileWriter exception handling.
	 */
	public static void generateRandomPeople() throws IOException {
		PeopleGenerator pplgenerator = PeopleGenerator.getInstance();
		System.out.print("How many people do you want to create? ");
		int n = sc.nextInt();
		System.out.print("Name for the output file: ");
		String fname = sc.next() + ".txt";
		pplgenerator.generateRandomPeopleFile(n, fname);
		System.out.println("File " + fname + " succesfully created with " +  (n == 1 ? "person" : "people") + "people data.");
	}
	
	/**
	 * Generates random relationships to an output file with the people
	 * given in a file
	 * @throws IOException
	 */
	public static void generateRandomRelations() throws IOException {
		PeopleGenerator pplgenerator = PeopleGenerator.getInstance();
		System.out.println("Select a file from which to generate the relations. ");
		
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			try {
				System.out.print("Name of the output file: ");
				String outputFName = sc.next() + ".txt";
				
				// Generate the random relationships
				RelationGenerator.generateRandomRelationshipsFile(file, outputFName);
				
				System.out.println("File " + outputFName + " with random relationships successfully created.");
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	/* ***** </> EXTRAS MENU FUNCTIONS ***** */
}