package packnetwork;
import exceptions.InvalidPersonId;

import java.util.ArrayList;

public class Person {
	private final String ID;
	private String firstName, lastName, birthdate, gender, birthplace, home, groupcode;
	private ArrayList<String> studiedat, workedat, movies;
	
	public Person(String ID) {
		this(ID, "", "" , "", "", "", "", null, null, null, "");
	}
	
	public Person(String ID, String firstName, String lastName, String birthdate, String gender,
			String birthplace, String home, ArrayList<String> studiedat, ArrayList<String> workedat,
			ArrayList<String> movies, String groupcode) throws InvalidPersonId {
		if (ID.equals("")) throw new InvalidPersonId();
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
	
	public String getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public ArrayList<String> getStudiedat() {
		return studiedat;
	}

	public void addStudiedat(String studiedat) {
		this.studiedat.add(studiedat);
	}
	
	public boolean removeStudiedat(String studiedat) {
		return this.studiedat.remove(studiedat);
	}
	
	public void removeStudiedat(int index) {
		studiedat.remove(index);
	}

	public ArrayList<String> getWorkedat() {
		return workedat;
	}

	public void addWorkedat(String workedat) {
		this.workedat.add(workedat);
	}
	
	public boolean removeWorkedat(String workedat) {
		return this.workedat.remove(workedat);
	}
	
	public void removeWorkedat(int index) {
		this.workedat.remove(index);
	}

	public ArrayList<String> getMovies() {
		return movies;
	}

	public void addMovie(String movies) {
		this.movies.add(movies);
	}
	
	public boolean removeMovie(String movie) {
		return this.movies.remove(movie);
	}
	
	public void removeMovie(int index) {
		this.movies.remove(index);
	}

	public String getGroupcode() {
		return groupcode;
	}

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