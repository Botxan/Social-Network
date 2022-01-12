package packnetworktests;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import packnetwork.Person;

@DisplayName("MAIN CLASS for testing the person object that conforms the social network")
public class PersonTests {
	private static Person p1;
	private static Person p2;
	
	@BeforeAll
	@DisplayName("Initialization of the Person")
	public static void initPerson() {
		p1 = new Person("Ed63", "Eddard", "Stark", "17-4-1959", "male", "Winterfell",
				"Winterfell",new ArrayList<String>(Arrays.asList("sheffield")), new ArrayList<String>(Arrays.asList("winterfell")),
				new ArrayList<String>(Arrays.asList("the empire strikes back", "independence day")), "G61267");
		p2 = new Person("Rob83"); 
	}
	
	@AfterEach
	public void done() {
		System.out.println("done!");
	}
	
	@AfterAll
	@DisplayName("Delete the Person for tests")
	public static void clearPerson() {
		p1 = null;
		p2 = null;
	}
	
	/* ----------- TESTS ----------- */
	
	@Nested
	@DisplayName("GETTERS. Nested class for testing the getters")
	class PersonGetters {		
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for id")
		public void	testGetId() {
			assertEquals("Ed63", p1.getID());
			assertEquals("Rob83", p2.getID());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for first name")
		public void	testGetFirstName() {
			assertEquals("Eddard", p1.getFirstName());
			assertEquals("", p2.getFirstName());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for last name")
		public void	testGetLastName() {
			assertEquals("Stark", p1.getLastName());
			assertEquals("", p2.getLastName());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for birthdate")
		public void	testGetBirthdate() {
			assertEquals("17-4-1959", p1.getBirthdate());
			assertEquals("", p2.getBirthdate());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for gender")
		public void	testGetGender() {
			assertEquals("male", p1.getGender());
			assertEquals("", p2.getGender());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for birthplace")
		public void	testGetBirthplace() {
			assertEquals("Winterfell", p1.getBirthplace());
			assertEquals("", p2.getBirthplace());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for home")
		public void	testGetHome() {
			assertEquals("Winterfell", p1.getHome());
			assertEquals("", p2.getHome());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for studiedat")
		public void	testGetStudiedat() {
			assertEquals(new ArrayList<String>(Arrays.asList("sheffield")), p1.getStudiedat());
			assertEquals(new ArrayList<String>(), p2.getStudiedat());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for workedat")
		public void	testGetWorkedat() {
			assertEquals(new ArrayList<String>(Arrays.asList("winterfell")), p1.getWorkedat());
			assertEquals(new ArrayList<String>(), p2.getWorkedat());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for movies")
		public void	testGetMovies() {
			assertEquals(new ArrayList<String>(Arrays.asList("the empire strikes back", "independence day")), p1.getMovies());
			assertEquals(new ArrayList<String>(), p2.getMovies());
		}
		
		@Test
		@Tag("getter")
		@DisplayName("Test getter for groupcode")
		public void	testGetGroupCode() {
			assertEquals("G61267", p1.getGroupcode());
			assertEquals("", p2.getGroupcode());
		}
	}
	
	
	@Nested
	@DisplayName("SETTERS. Nested class for testing the setters")
	class PersonSetters {
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for first name")
		public void testSetFirstName() {
			String firstName = "test";
			p1.setFirstName(firstName);
			assertEquals(firstName, p1.getFirstName());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for last name")
		public void testSetLastName() {
			String lastName = "test";
			p1.setLastName(lastName);
			assertEquals(lastName, p1.getLastName());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for birthdate")
		public void testSetBirthdate() {
			String birthdate = "test";
			p1.setBirthdate(birthdate);
			assertEquals(birthdate, p1.getBirthdate());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for gender")
		public void testSetGender() {
			String gender = "test";
			p1.setGender(gender);
			assertEquals(gender, p1.getGender());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for birthplace")
		public void testSetBirthplace() {
			String birthplace = "test";
			p1.setBirthplace(birthplace);
			assertEquals(birthplace, p1.getBirthplace());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for home")
		public void testSetHome() {
			String home = "test";
			p1.setHome(home);
			assertEquals(home, p1.getHome());
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for adding studiedat")
		public void testAddStudiedat() {
			int i = p1.getStudiedat().size();
			String studiedat = "test";
			p1.addStudiedat(studiedat);
			assertEquals(studiedat, p1.getStudiedat().get(i));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a studiedat by studiedat name")
		public void testRemoveStudiedat1() {
			int oldSize = p1.getStudiedat().size();
			String lastStudiedat = p1.getStudiedat().get(oldSize-1);
			p1.removeStudiedat(lastStudiedat);
			int newSize = p1.getStudiedat().size();
			// Check that one element has been deleted
			assertEquals(oldSize-1, newSize);
			// Check the the last one has been deleted
			if (newSize > 0) assertNotEquals(lastStudiedat, p1.getStudiedat().get(newSize-1));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing an empty studiedat by studiedat name")
		public void testRemoveEmptyStudiedat1() {
			assertFalse(p2.removeStudiedat("test"));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a studiedat by index")
		public void testRemoveStudiedat2() {
			int oldSize = p1.getStudiedat().size();
			String lastStudiedat = p1.getStudiedat().get(oldSize-1);
			p1.removeStudiedat(oldSize-1);
			int newSize = p1.getStudiedat().size();
			// Check that one element has been deleted
			assertEquals(oldSize-1, newSize);
			// Check the the last one has been deleted
			if (newSize > 0) assertNotEquals(lastStudiedat, p1.getStudiedat().get(newSize-1));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing an empty studiedat by index")
		public void testRemoveEmptyStudiedat2() {
			assertThrows(Exception.class, () -> p2.removeStudiedat(0));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for adding workedat")
		public void testAddWorkedat() {
			String workedat = "test";
			p1.addWorkedat(workedat);
			assertEquals(workedat, p1.getWorkedat().get(0));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a workedat by workedat name")
		public void testRemoveWorkedat1() {
			int oldSize = p1.getWorkedat().size();
			String lastWorkedat = p1.getWorkedat().get(oldSize-1);
			p1.removeWorkedat(lastWorkedat);
			int newSize = p1.getWorkedat().size();
			// Check that one element has been deleted
			assertEquals(oldSize-1, newSize);
			// Check the the last one has been deleted
			if (newSize > 0) assertNotEquals(lastWorkedat, p1.getWorkedat().get(newSize-1));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing an empty workedat by workedat name")
		public void testRemoveEmptyWorkedat1() {
			assertFalse(p2.removeWorkedat("test"));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a workedat by index")
		public void testRemoveWorkedat2() {
			int oldSize = p1.getWorkedat().size();
			String lastWorkedat = p1.getWorkedat().get(oldSize-1);
			p1.removeWorkedat(oldSize-1);
			int newSize = p1.getWorkedat().size();
			// Check that one element has been deleted
			assertEquals(oldSize-1, newSize);
			// Check the the last one has been deleted
			if (newSize > 0) assertNotEquals(lastWorkedat, p1.getWorkedat().get(newSize-1));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing an empty workedat by index")
		public void testRemoveEmptyWorkedat2() {
			assertThrows(Exception.class, () -> p2.removeWorkedat(0));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for adding a movie")
		public void testAddMovie() {
			String movie = "test";
			p1.addMovie(movie);
			assertEquals(movie, p1.getMovies().get(0));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a movie by movie name")
		public void testRemoveMovie1() {
			try {
				int oldSize = p1.getMovies().size();
				String lastWorkedat = p1.getMovies().get(oldSize-1);
				p1.removeMovie(lastWorkedat);
				int newSize = p1.getMovies().size();
				// Check that one element has been deleted
				assertEquals(oldSize-1, newSize);
				// Check the the last one has been deleted
				if (newSize > 0) assertNotEquals(lastWorkedat, p1.getMovies().get(newSize-1));
			} catch (IndexOutOfBoundsException e) {
				fail(System.err.toString());
			}
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a movie by movie name")
		public void testRemoveEmptyMovie1() {
			assertFalse(p2.removeMovie("test"));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing a movie by index")
		public void testRemoveMovie2() {
			int oldSize = p1.getMovies().size();
			String lastWorkedat = p1.getMovies().get(oldSize-1);
			p1.removeMovie(oldSize-1);
			int newSize = p1.getMovies().size();
			// Check that one element has been deleted
			assertEquals(oldSize-1, newSize);
			// Check the the last one has been deleted
			if (newSize > 0) assertNotEquals(lastWorkedat, p1.getMovies().get(newSize-1));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test for removing an empty movie by index")
		public void testRemoveEmptyMovie2() {
			assertThrows(Exception.class, () -> p2.removeMovie(0));
		}
		
		@Test
		@Tag("setter")
		@DisplayName("Test setter for groupcode")
		public void testSetGroupcode() {
			String groupcode = "test";
			p1.setGroupcode(groupcode);
			assertEquals(groupcode, p1.getGroupcode());
		}
	}
	
	@Test
	@DisplayName("Test equals override")
	public void testEquals() {
		assertTrue(new Person("Ed63").equals(p1));
	}
		
	@Test
	@DisplayName("Test hashCode override")
	public void testHashCode() {
		assertEquals(17*31+p1.getID().hashCode(), p1.hashCode());
	}
}