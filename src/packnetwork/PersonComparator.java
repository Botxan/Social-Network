package packnetwork;

import java.util.Comparator;

/**
 * Set of comparators used to compare People by different criteria.
 * @author Oihan and Eneko
 *
 */
public class PersonComparator {

	/**
	 * Compares 2 people by surname
	 */
	public static final Comparator<Person> bySurname = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			return p1.getLastName().compareTo(p2.getLastName());
		}
	};
	
	/**
	 * Compares 2 people by birthplace
	 */
	public static final Comparator<Person> byBirthplace = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			return p1.getBirthplace().compareTo(p2.getBirthplace());
		}
	};
	
	/**
	 * Compares 2 people by birtplace > surname > name (lexicographic order)
	 */
	public static final Comparator<Person> byBSN = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			int flag = p1.getBirthplace().compareTo(p2.getBirthplace());
			if (flag == 0) {
				flag = p1.getLastName().compareTo(p2.getLastName());
				if (flag == 0) flag = p1.getFirstName().compareTo(p2.getFirstName());
			}
			return flag;
		}
	};
	
	/**
	 * Compares 2 people, the first one by home, the second one by birthplace
	 */
	public static final Comparator<Person> byBirthplaceAndHome = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			return p1.getBirthplace().compareTo(p2.getHome());
		}
	};
}
