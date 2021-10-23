package packnetworktests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import exceptions.ElementNotFoundException;
import exceptions.InvalidRelationshipException;
import packnetwork.Network;
import packnetwork.Person;

@DisplayName("MAIN CLASS for testing the Social Network class")
public class networkTests {
	private static Network network;
	
	@BeforeEach
	@DisplayName("Creation of the Social Network instance")
	void initNetwork() {
		network = Network.getInstance();
	}
	
	@AfterEach
	@DisplayName("Reset of the social network, since it uses Singleton pattern.")
	void resetNetwork() {
		network.reset();
	}
	
	@AfterAll
	@DisplayName("Delete the social network")
	public static void clearNetwork() {
		network = null;
		System.out.println("done");
	}
	
	/* ----------- TESTS ----------- */
	
	@Nested
	@DisplayName("ADD PERSON. Nested class for testing adding persons to the network")
	class AddPerson {
		
		@Test
		@DisplayName("Test adding that is not inside the network yet")
		public void testAddNonExistingPerson() {
			Person p1 = new Person("Ed63");
			network.addPerson(p1);
			assertEquals(1, network.getNumberOfPeople());
			assertTrue(network.hasPerson(p1));
		}
		
		@Test
		@DisplayName("Test adding person is already inside the network")
		public void testAddExistingPerson() {
			Person p1 = new Person("Ed63");
			Person p2 = new Person("Ed63");
			network.addPerson(p1);
			assertThrows(Exception.class, () -> network.addPerson(p2));
		}		
	}

	@Nested
	@DisplayName("ADD RELATIONSHIPS. Nested class for testing adding relationships")
	class AddRelationship {
		
		@Test
		@DisplayName("Test adding non existing relationship between added people")
		public void testAddNonExistingRelationshipBetweenAddedPeople() {
			try {				
				Person p1 = new Person("Ed63");
				Person p2 = new Person("Rob83");
				network.addPerson(p1);
				network.addPerson(p2);
				network.addRelationship(p1, p2);
				assertTrue(network.hasRelationship(p1, p2));
			} catch (Exception e) {
				fail(System.err.toString());
			}
		}
		
		@Test
		@DisplayName("Testing adding existing relationship between added people")
		public void testAddExistingRelationshipBetweenAddedPeople() {			
				Person p1 = new Person("Ed63");
				Person p2 = new Person("Rob83");
				network.addPerson(p1);
				network.addPerson(p2);
				network.addRelationship(p1, p2);
				assertThrows(InvalidRelationshipException.class, () -> network.addRelationship(p1, p2));
		}
		
		@Test
		@DisplayName("Testing adding non existing relationship between non-added people")
		public void testAddNonExistingRelationshipBetweenNonAddedPeople() {
			Person p1 = new Person("Ed63");
			Person p2 = new Person("Rob83");
			assertThrows(ElementNotFoundException.class, () -> network.addRelationship(p1, p2));
		}
		
		@Test
		@DisplayName("Testing adding relationship between same person")
		public void testAddNonExistingRelationshipBetweenSamePerson() {
			Person p1 = new Person("Ed63");
			network.addPerson(p1);
			assertThrows(InvalidRelationshipException.class, () -> network.addRelationship(p1, p1));
		}
	}	
}
