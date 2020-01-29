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
	
	
	/**
	 * Saves a given entity.
	 * Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * Specified by: save(...) in CrudRepository
	 * Type Parameters:<S> Parameters:entity must not be null.Returns:the saved entity; will never be null.
	 * */
	@Override
	public <S extends Product> S save(S entity) {
		
		//if entity is not null save it
		if(entity!=null) entityManager.persist(entity);
		//return the product entity
		return entity;
	}

	/**
	 * Saves all given entities.
	 * Specified by: saveAll(...) in CrudRepository
	 * Type Parameters:<S> Parameters:entities must not be null nor must it contain null.
	 * Returns:the saved entities; will never be null.
	 *  The returned Iterable will have the same sizeas the Iterable passed as an argument.
	 * */
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

	/**Retrieves an entity by its id.
	 * Specified by: findById(...) in CrudRepository
	 * Parameters:id must not be null.Returns:the entity with the given id or Optional#empty() if none found.**/
	
	@Override
	public Optional<Product> findById(Integer id) {
		
		//if id exists, find product otherwise set to NULL
		Product result = existsById(id) ? entityManager.find(Product.class, id) : null;
		//if result is null return Optional.empty() otherwise return optional of the product
		return result == null ? Optional.empty() : Optional.of(result);
	}

	/**Returns whether an entity with the given id exists.
	 * Specified by: existsById(...) in CrudRepository
	 * Parameters:id must not be null.Returns:true if an entity with the given id exists, false otherwise.
	 **/
	@Override
	public boolean existsById(Integer id) {
		
		//find product by id
		//If the id exists, find the product otherwise set product to NULL
		Product result = existsById(id) ? entityManager.find(Product.class, id) :null;
		//return boolean indicating if result == null
		return (result == null);
	}

	/**Returns all instances of the type.
	 * Specified by: findAll() in CrudRepository
	 * Returns:all entities 
	**/
	@Override
	public Iterable<Product> findAll() {
		
		//init a list to contain search results
		List<Product> result = new ArrayList<Product>();
		//Create select query
		TypedQuery<Product> query =entityManager.createQuery("select * FROM product",Product.class);
		// return result
		return query.getResultList();
	}

	/**
	 * Returns all instances of the type T with the given IDs. 
	 * If some or all ids are not found, no entities are returned for these IDs.
	 * Note that the order of elements in the result is not guaranteed.
	 * Specified by: findAllById(...) in CrudRepository
	 * Parameters:ids must not be null nor contain any null values.Returns:guaranteed to be not null.
	 * The size can be equal or less than the number of givenids.
	 * */
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

	/**
	 * Returns the number of entities available.
	 * Specified by: count() in CrudRepository
	 * Returns:the number of entities.
	 **/
	@Override
	public long count() {
		
		//create query for COUNT operation
		TypedQuery<Product> query = entityManager.createQuery("select COUNT(*) FROM product",Product.class);
		//execute and return result
		return query.getFirstResult();
	}

	/**Deletes the entity with the given id.
	 * Specified by: deleteById(...) in CrudRepository
	 * Parameters:id must not be null.
	 **/
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

	/**Deletes a given entity.
	 * Specified by: delete(...) in CrudRepository
	 * Parameters:entity must not be null.
	 * */
	@Override
	public void delete(Product entity) {
		if(entity!=null)entityManager.remove(entity);
		
	}

	/**Deletes the given entities.
	 * Specified by: deleteAll(...) in CrudRepository
	 * Parameters:entities must not be null. Must not contain null elements.
	 * */
	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		entities.forEach((entity)->{
			delete(entity);
		});
		
	}

	/**
	 * Deletes all entities managed by the repository.
	 * Specified by: deleteAll() in CrudRepository
	 * */
	@Override
	public void deleteAll() {
		TypedQuery<Product> query = entityManager.createQuery("DELETE FROM product",Product.class);
		query.executeUpdate();
		
	}

	/**
	 * find product by name*
	 * @param String name
	 * @return Product*/
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
