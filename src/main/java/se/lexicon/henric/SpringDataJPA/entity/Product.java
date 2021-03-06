package se.lexicon.henric.SpringDataJPA.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	//************** Fields ****************************************************/
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int productId;
	private String name;
	private BigDecimal price;
	
	
	//************** Constructors ****************************************************/
	/**
	 * Constructor
	 * @param int id
	 * @param String name
	 * @param BigDecimal price
	 * */
	public Product(int id,String name, BigDecimal price) {
		if (name !=null && price != BigDecimal.ZERO && price != null) {
			setName(name);
			setPrice(price);
			this.productId =id;
		} 
		else {
		throw new IllegalArgumentException("Parameters can't be null");
		}
	}
	
	/**
	 * Constructor
	 * @param String name
	 * @param BigDecimal price
	 * */
	public Product(String name, BigDecimal price) {
		this(0, name,price);
	}
	
	/**
	 * Default Constructor
	 * 
	 * */
	public Product() {
		//Default constructor
	}
	
	
	/**************Getters & Setters ****************************************************/
	
	
	/**Getter for field String name
	 * @Return String name
	 * */
	public String getName() {
		return name;
	}
	
	/**Setter for field String name
	 * @Return void
	 * */
	public void setName(String name) {
		this.name = name;
	}
	
	/**Getter for field BigDecimal price
	 * @Return BigDecimal price
	 * */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**Setter for field BigDecimal price
	 * @Return void
	 * */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**Getter for field int id
	 * @Return String name
	 * */
	public int getId() {
		return productId;
	}

	/**************Equals & hashcode ****************************************************/

	/**
	 * Overrides: hashCode() in Object
	 *Returns a hash code value for the object. This method is
	 *supported for the benefit of hash tables such as those provided by HashMap. 
	 *@return int result
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}


	/**
	 * Overrides: equals(...) in Object
	 * Indicates whether some other object is "equal to" this one. 
	 * public boolean equals(Object obj)
	*@param Object obj
	*@return boolean
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
}
