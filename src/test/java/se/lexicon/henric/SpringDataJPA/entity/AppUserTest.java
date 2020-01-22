package se.lexicon.henric.SpringDataJPA.entity;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AppUserTest {

	String firstName = "test";
	String LastName ="testsson";
	String email= "test.testsson@test.com";
			
	@Test
	public void initization_with_correct_params_no_exc_thrown() {
		AppUser u = new AppUser(firstName, LastName, email);
		assertTrue(u.getFirstname() == firstName
				&& u.getLastname() == LastName
				&& u.getEmail() == email);
	}
	
	@Test
	public void initization_with_null_params_throws_exception() {
		try{
			AppUser u = new AppUser(null, null,null);
		}
		catch(IllegalArgumentException e) {
			//good, exception is thrown
		}
		
		
	}
	
	@Test
	public void id_Counter_adds_up_and_getID_returns_ID() {
		int expected = 1;
		AppUser u = new AppUser(firstName, LastName, email);
		
		assertTrue(u.getId()==expected);
		
	}
	
	
	
}
