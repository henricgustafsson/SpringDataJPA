package data;



import org.springframework.data.repository.CrudRepository;

import se.lexicon.henric.SpringDataJPA.entity.Product;

public interface ProductDao extends CrudRepository<Product, Integer>{
 	 Product findByName(String name);
	
}
