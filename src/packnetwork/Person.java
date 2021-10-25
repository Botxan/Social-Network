package packnetwork;
import exceptions.InvalidPersonIdException;

import java.util.ArrayList;

/**
 * Represents a person than can be added to a social network.
 * @author Oihan and Eneko
 *
 */
public class Person {
	private final String ID;
	private String firstName, lastName, birthdate, gender, birthplace, home, groupcode;
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
	 * @param ID person identifier
	 * @param firstName first name
	 * @param lastName last name
	 * @param birthdate birth date
	 * @param gender gender
	 * @param birthplace birth place
	 * @param home home
	 * @param studiedat places where the person has studied
	 * @param workedat places where the person has worked
	 * @param movies movies that the person has seen
	 * @param groupcode group code
	 * @throws InvalidPersonIdException when the id is empty
	 */
	public Person(String ID, String firstName, String lastName, String birthdate, String gender,
			String birthplace, String home, ArrayList<String> studiedat, ArrayList<String> workedat,
			ArrayList<String> movies, String groupcode) throws InvalidPersonIdException {
		if (ID.equals("")) throw new InvalidPersonIdException();
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.birthplace = birthplace;
		this.home = home;
		
		this.studiedat = new ArrayList<String>();
		if (studiedat != null) 
			for (String item: studiedat) this.studiedat.add(item);
		
		this.workedat = new ArrayList<String>();
		if (workedat != null) 
			for (String item: workedat) this.workedat.add(item);
		
		this.movies = new ArrayList<String>();
		if (movies != null)
			for (String item: movies) this.movies.add(item);
		
		this.groupcode = groupcode;
	}

	
	/* ******* Getters and Setters ******* */
	
	/**
	 * Getter for the identifier.
	 * @return the identifier
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Getter for the first name.
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the first name.
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name.
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the last name.
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the birth date.
	 * @return the birth date
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Setter for the birth date.
	 * @param birthdate the birth date
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Getter for the gender.
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for the gender.
	 * @param gender the gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Getter for the birth place.
	 * @return the birth place
	 */
	public String getBirthplace() {
		return birthplace;
	}

	
	/**
	 * Setter for the birth place.
	 * @param birthplace the birth place
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * Getter for the home.
	 * @return the home
	 */
	public String getHome() {
		return home;
	}

	/**
	 * Setter for the home.
	 * @param home the home
	 */
	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * Getter for places where the person has studied.
	 * @return places where the person has studied
	 */
	public ArrayList<String> getStudiedat() {
		return studiedat;
	}

	/**
	 * Adds a place where the person has studied.
	 * @param studiedat the place where the person has studied
	 */
	public void addStudiedat(String studiedat) {
		this.studiedat.add(studiedat);
	}
	
	/**
	 * Removes a place where the person has studied.
	 * @param studiedat the place where the person has studied
	 * @return true iff the place has been removed
	 */
	public boolean removeStudiedat(String studiedat) {
		return this.studiedat.remove(studiedat);
	}
	
	/**
	 * Removes a place where the person has studied.
	 * @param index the index of the place to be removed
	 */
	public void removeStudiedat(int index) {
		studiedat.remove(index);
	}

	/**
	 * Getter for places where the person has worked.
	 * @return the places where the person has worked
	 */
	public ArrayList<String> getWorkedat() {
		return workedat;
	}

	/**
	 * Adds a place where the person has worked.
	 * @param workedat the place where the person has worked
	 */
	public void addWorkedat(String workedat) {
		this.workedat.add(workedat);
	}
	
	/**
	 * Removes a place where person has worked. 
	 * @param workedat the place where the person has worked
	 * @return true if the place has been removed
	 */
	public boolean removeWorkedat(String workedat) {
		return this.workedat.remove(workedat);
	}
	
	/**
	 * Removes a place where person has worked.
	 * @param index the index of the place to be removed
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
	 * @param movies the movie that the person has seen
	 */
	public void addMovie(String movies) {
		this.movies.add(movies);
	}
	
	/**
	 * Removes a movie that the person has seen.
	 * @param movie the movie that the person has seen
	 * @return true if the movie has been removed
	 */
	public boolean removeMovie(String movie) {
		return this.movies.remove(movie);
	}
	
	/**
	 * Removes a movie that the person has seen.
	 * @param index the index of the movie to be removed
	 */
	public void removeMovie(int index) {
		this.movies.remove(index);
	}

	/**
	 * Getter for the group code.
	 * @return the group code
	 */
	public String getGroupcode() {
		return groupcode;
	}

	/**
	 * Setter for the group code.
	 * @param groupcode the group code
	 */
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	
	/* ******* Getters and Setters ******* */
	
	@Override
	public String toString() {
		return ID + "," + firstName + "," + lastName + "," + birthdate + "," + home + "," + String.join(";", studiedat) + "," + String.join(";", workedat) + "," + String.join(";", movies) + "," + groupcode;		
	}
	
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
}