package packnetwork;
import exceptions.InvalidAttributeException;
import exceptions.InvalidPersonIdException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a person than can be added to a social network.
 * @author Oihan and Eneko
 *
 */
public class Person implements Comparable<Person> {
	private final String ID;
	private String firstName, lastName, birthdate, gender, birthplace, home, groupcode;
	private int componentID;
	private ArrayList<String> studiedat, workedat, movies;
	
	/**
	 * Constructor for a person where only is passed his/her id.
	 * @param ID the person identifier
	 */
	public Person(String ID) {
		this(ID, "", "" , "", "", "", "", null, null, null, "");
	}
	
	/**
	 * Main constructor for a person.
	 * @param ID person identifier.
	 * @param firstName first name.
	 * @param lastName last name.
	 * @param birthdate birth date.
	 * @param gender gender.
	 * @param birthplace birth place.
	 * @param home home.
	 * @param studiedat places where the person has studied.
	 * @param workedat places where the person has worked.
	 * @param movies movies that the person has seen.
	 * @param groupcode group code.
	 * @throws InvalidPersonIdException when the id is empty.
	 */
	public Person(String ID, String firstName, String lastName, String birthdate,
			String gender, String birthplace, String home, ArrayList<String> studiedat,
			ArrayList<String> workedat, ArrayList<String> movies, String groupcode) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.birthplace = birthplace;
		this.home = home;
		
		this.studiedat = new ArrayList<String>();
		if (studiedat != null) {
			for (String item: studiedat) this.studiedat.add(item.toLowerCase());
			Collections.sort(studiedat);			
		}
		
		this.workedat = new ArrayList<String>();
		if (workedat != null) {
			for (String item: workedat) this.workedat.add(item.toLowerCase());
			Collections.sort(workedat);
		}
		
		this.movies = new ArrayList<String>();
		if (movies != null) {			
			for (String item: movies) this.movies.add(item.toLowerCase());
			Collections.sort(movies);
		}
		
		this.groupcode = groupcode;
	}

	
	/* ******* Getters and Setters ******* */
	
	/**
	 * Getter for the identifier.
	 * @return the identifier.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Getter for the first name.
	 * @return the first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the first name.
	 * @param firstName the first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name.
	 * @return the last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the last name.
	 * @param lastName the last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the birth date.
	 * @return the birth date.
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Setter for the birth date.
	 * @param birthdate the birth date.
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Getter for the gender.
	 * @return the gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for the gender.
	 * @param gender the gender.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Getter for the birth place.
	 * @return the birth place.
	 */
	public String getBirthplace() {
		return birthplace;
	}

	
	/**
	 * Setter for the birth place.
	 * @param birthplace the birth place.
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * Getter for the home.
	 * @return the home.
	 */
	public String getHome() {
		return home;
	}

	/**
	 * Setter for the home.
	 * @param home the home.
	 */
	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * Getter for places where the person has studied.
	 * @return places where the person has studied.
	 */
	public ArrayList<String> getStudiedat() {
		return studiedat;
	}

	/**
	 * Adds a place where the person has studied.
	 * @param studiedat the place where the person has studied.
	 */
	public void addStudiedat(String studiedat) {
		int pos = Collections.binarySearch(this.studiedat, studiedat);
		if (pos < 0) this.studiedat.add(-pos-1, studiedat.toLowerCase());
	}
	
	/**
	 * Removes a place where the person has studied.
	 * @param studiedat the place where the person has studied.
	 * @return true iff the place has been removed.
	 */
	public boolean removeStudiedat(String studiedat) {
		return this.studiedat.remove(studiedat);
	}
	
	/**
	 * Removes a place where the person has studied.
	 * @param index the index of the place to be removed.
	 */
	public void removeStudiedat(int index) {
		studiedat.remove(index);
	}

	/**
	 * Getter for places where the person has worked.
	 * @return the places where the person has worked.
	 */
	public ArrayList<String> getWorkedat() {
		return workedat;
	}

	/**
	 * Adds a place where the person has worked.
	 * @param workedat the place where the person has worked.
	 */
	public void addWorkedat(String workedat) {
		int pos = Collections.binarySearch(this.workedat, workedat);
		if (pos < 0) this.workedat.add(-pos-1, workedat.toLowerCase());
	}
	
	/**
	 * Removes a place where person has worked. 
	 * @param workedat the place where the person has worked.
	 * @return true if the place has been removed.
	 */
	public boolean removeWorkedat(String workedat) {
		return this.workedat.remove(workedat);
	}
	
	/**
	 * Removes a place where person has worked.
	 * @param index the index of the place to be removed.
	 */
	public void removeWorkedat(int index) {
		this.workedat.remove(index);
	}

	/**
	 * Getter for the movies that the person has seen.
	 * @return the movies that the person has seen.
	 */
	public ArrayList<String> getMovies() {
		return movies;
	}

	/**
	 * Adds a movie that the person has seen.
	 * @param movies the movie that the person has seen.
	 */
	public void addMovie(String movies) {
		int pos = Collections.binarySearch(this.movies, movies);
		if (pos < 0) this.movies.add(-pos-1, movies.toLowerCase());
	}
	
	/**
	 * Removes a movie that the person has seen.
	 * @param movie the movie that the person has seen.
	 * @return true if the movie has been removed.
	 */
	public boolean removeMovie(String movie) {
		return this.movies.remove(movie);
	}
	
	/**
	 * Removes a movie that the person has seen.
	 * @param index the index of the movie to be removed.
	 */
	public void removeMovie(int index) {
		this.movies.remove(index);
	}

	/**
	 * Getter for the group code.
	 * @return the group code.
	 */
	public String getGroupcode() {
		return groupcode;
	}

	/**
	 * Setter for the group code.
	 * @param groupcode the group code.
	 */
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	
	/**
	 * Getter for the component.
	 * @return the component id.
	 */
	public int getComponentID() {
		return componentID;
	}
	
	/**
	 * Setter for the component.
	 * @param componentID the component ID.
	 */
	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	
	/* ******* Getters and Setters ******* */
	
	/**
	 * Given an integer, returns the attribute:
	 * 0  => idperson
	 * 1  => name
	 * 2  => lastname
	 * 4  => birthdate
	 * 5  => gender
	 * 6  => birthplace
	 * 7  => home
	 * 8  => studiedat
	 * 9  => workplaces
	 * 10 => films
	 * 11 => groupcode
	 * @param i The attribute number equivalent.
	 * @return The attribute value.
	 */
	public String getAttribute(int i) {
		String result = "";
		switch(i) {
			case 0:
				result = getID(); 
				break;
			case 1:
				result = getFirstName();
				break;
			case 2:
				result = getLastName();
				break;
			case 3:
				result = getBirthdate();
				break;
			case 4:
				result = getGender();
				break;
			case 5:
				result = getBirthplace();
				break;
			case 6:
				result = getHome();
				break;
			case 7:
				result = String.join(";", getStudiedat()); 
				break;
			case 8:
				result = String.join(";", getWorkedat());
				break;	
			case 9:
				result = String.join(result, getMovies());
				break;
			case 10:
				result = getGroupcode();
				break;
			default:
				throw new InvalidAttributeException();
		}
		
		return result;
	}
	
	/**
	 * Returns a string representation of a Person, with just the attributes
	 * passed by parameter.
	 * @param attributes the attributes to be printed.
	 * @return an string representation of all the attributes passed by
	 * parameter.
	 */
	public String toString(int[] attributes) {
		String result = "";
		for (int i = 0; i < attributes.length; i++) // iterate over selected attributes
			result += getAttribute(attributes[i]) + ", ";
		
		return result.replaceAll(", $", "");
	}
	
	@Override
	public String toString() {
		return ID + "," + firstName + "," + lastName + "," + birthdate + "," + gender + "," + birthplace + "," + home + "," + String.join(";", studiedat) + "," + String.join(";", workedat) + "," + String.join(";", movies) + "," + groupcode;		
	}
	
	/**
	 * 2 people are the same if they have the same id.
	 */
	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true
		if (o == this) return true;
		
		// Check if o is an instance of Person (null = false)
		if (!(o instanceof Person)) return false;
		
		// typecast to Person so we can compare ids
		Person p = (Person) o;
		
		// Compare person ids
		return ID.equals(p.ID);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + ID.hashCode();
		return result;
	}
	
	/**
	 * Default ordering by id (ascending).
	 * @param o the person to get compared.
	 * @return greater than 0 if instantiated person has greater id.
	 * less than 0 if instantiated person has smaller id.
	 * Otherwise return 0.
	 */
	@Override
	public int compareTo(Person o) {
		return getID().compareTo(o.getID());
	}
}