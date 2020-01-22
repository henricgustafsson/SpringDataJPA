package se.lexicon.henric.SpringDataJPA.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

String name = "test";
BigDecimal priceCorrect = BigDecimal.valueOf(10);
BigDecimal priceZero = BigDecimal.ZERO;
	
	@Test
	public void initization_with_correct_params_no_exc_thrown() {
		Product p = new Product(name, priceCorrect);
	
		assertTrue(p.getName() == name
				&& p.getPrice()== priceCorrect
				);
	}
	
	@Test
	public void initization_with_price_param_ZERO_throws_exception() {
		try{
			Product p = new Product(name, priceZero);
		}
		catch(IllegalArgumentException e) {
			//good, exception is thrown
		}
		
		
	}
	
	@Test
	public void initization_with_null_params_throws_exception() {
		try{
			Product p = new Product(null, null);
		}
		catch(IllegalArgumentException e) {
			//good, exception is thrown
		}
		
		
	}
	
	@Test
	public void id_Counter_adds_up_and_getID_returns_ID() {
		int expected = 1;
		Product p = new Product(name, priceCorrect);
		
		assertTrue(p.getId()==expected);
		
	}
}


	