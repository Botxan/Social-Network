package packnetwork;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Menu {
	
	public static void main(String[] args) {
		// Multi-file chooser
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setMultiSelectionEnabled(true);
		
		// Stdin reader
		Scanner sc = new Scanner(System.in);
		
		// Network
		Network network = Network.getInstance();
		
		// User selection
		int action = -1;
		
		while (action != 6) {
			System.out.println("+-------------- MENU --------------+");
			System.out.println("|1 | Load people into the network  |");
			System.out.println("|----------------------------------|");
			System.out.println("|2 | Load relationships.           |");
			System.out.println("|----------------------------------|");
			System.out.println("|3 | Print out people.             |");
			System.out.println("|----------------------------------|");
			System.out.println("|4 | Search.                       |");
			System.out.println("|----------------------------------|");
			System.out.println("|5 | Print relationships           |");
			System.out.println("|----------------------------------|");
			System.out.println("|6 | Quit                          |");
			System.out.println("+----------------------------------+");
			
			try {
				action = sc.nextInt();
				int returnValue;
				switch(action) {
					case 1: // Load files
						returnValue = jfc.showOpenDialog(null);				
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File[] files = jfc.getSelectedFiles();
							for (int i = 0; i < files.length; i++) network.loadPeople(files[i]);
						}		
						System.out.println("People uploaded to the network successfully.");
						break;
						
					case 2: // Load relationships
						returnValue = jfc.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File[] files = jfc.getSelectedFiles();
							for (int i = 0; i < files.length; i++) network.loadRelationships(files[i]);
						}
						System.out.println("Relationships updated on the network successfully");
						break;
						
					case 3: // Print out to a file all people inside the network
						String path = "people.txt";
						network.printPeople(new File("./" + path));
						System.out.println("People printed out to '" + path + "' file");
						break;
						
					case 4:
						System.out.println("Search not implemented yet");
						break;
						
					case 5: // Print relationships on the terminal
						System.out.println(network);
						break;
						
					case 6: // Quit
						break;
						
					default:
						System.out.println("Invalid selection.");
						break;
				}
			} catch (Exception e) {}
		}
		sc.close();
	}
}
