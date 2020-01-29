package data;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import se.lexicon.henric.SpringDataJPA.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {


	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public <S extends Product> S save(S entity) {
		
		//if entity is not null save it
		if(entity!=null) entityManager.persist(entity);
		//return the product entity
		return entity;
	}

	@Override
	public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
		
		//iterate through product entities
		entities.forEach((entity)->{
			//if product is not null save
			save(entity);
						
		});
		//return them
		return entities;
	}

	@Override
	public Optional<Product> findById(Integer id) {
		
		//if id exists, find product otherwise set to NULL
		Product result = existsById(id) ? entityManager.find(Product.class, id) : null;
		//if result is null return Optional.empty() otherwise return optional of the product
		return result == null ? Optional.empty() : Optional.of(result);
	}

	@Override
	public boolean existsById(Integer id) {
		
		//find product by id
		//If the id exists, find the product otherwise set product to NULL
		Product result = existsById(id) ? entityManager.find(Product.class, id) :null;
		//return boolean indicating if result == null
		return (result == null);
	}

	@Override
	public Iterable<Product> findAll() {
		
		//init a list to contain search results
		List<Product> result = new ArrayList<Product>();
		//Create select query
		TypedQuery<Product> query =entityManager.createQuery("select * FROM product",Product.class);
		// return result
		return query.getResultList();
	}

	@Override
	public Iterable<Product> findAllById(Iterable<Integer> ids) {
		
		//init a list to contain search results
		List<Product> result = new ArrayList<Product>();
		
		//iterate through ids		
		ids.forEach((id)->{
				//if id exists call findbyID @return Optional or set to Optional.empty()
				Optional<Product> p = existsById(id) ? findById(id) : Optional.empty();
				//if optional contains value
				if(p.isPresent()) {
					//add to list
					result.add(p.get());
				} 
		});
		//return search results
		return result;
	}

	@Override
	public long count() {
		
		//create query for COUNT operation
		TypedQuery<Product> query = entityManager.createQuery("select COUNT(*) FROM product",Product.class);
		//execute and return result
		return query.getFirstResult();
	}

	@Override
	public void deleteById(Integer id) {
		
		//if id exists
		if(existsById(id)) {
			//call findbyId 
			Optional<Product> p = findById(id);
			// if optional contains value get value otherwise set to null
			Product product = p.isPresent() ? p.get() : null;
			// nullcheck for product if not NULL remove
			if(product!=null) {
				entityManager.remove(product);
			}
		}
		
	}

	@Override
	public void delete(Product entity) {
		if(entity!=null)entityManager.remove(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		entities.forEach((entity)->{
			delete(entity);
		});
		
	}

	@Override
	public void deleteAll() {
		TypedQuery<Product> query = entityManager.createQuery("DELETE FROM product",Product.class);
		query.executeUpdate();
		
	}

	@Override
	public Product findByName(String name) {
		//SELECT p FROM product p WHERE name=?1
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM product p WHERE name=?1",Product.class);
		//set param on index 1 to name
		query.setParameter(1, name);
		//return result
		return query.getSingleResult();
	}

	
}
