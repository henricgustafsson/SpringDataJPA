package se.lexicon.henric.SpringDataJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 8.Turn Product, AppUser, OrderItem and ProductOrder into entitites.
 * 	a.Configure so that OrderItem has an eager @ManyToOne relationship with Product
 *  
 *  b.Configure so that OrderItem has a lazy @ManyToOne relationship with ProductOrder
 *  
 *  c.Configure so that ProductOrder has a @OneToMany relationshipwith OrderItem.
 *  	Set orphanremoval to true.ProductOrder should also have a @ManyToOne relationship with AppUser.
 *  
 *  d.Testrun your application.e.Commit
 * */
@SpringBootApplication
public class SpringDataJpa2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpa2Application.class, args);
	}

}
