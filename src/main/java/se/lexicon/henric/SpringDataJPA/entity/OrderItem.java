package se.lexicon.henric.SpringDataJPA.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class OrderItem {

	//**************Fields ****************************************************/
	private int orderItemId;
	private int quantity;
	//OrderItem has an eager @ManyToOne relationship with Product
	@ManyToOne(
				fetch = FetchType.EAGER,
				cascade= {
							CascadeType.PERSIST,
							CascadeType.MERGE,
							CascadeType.DETACH,
							CascadeType.REFRESH
						}			
			)
	@JoinColumn(name="product_id")
	private Product product;	
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade= {
						CascadeType.PERSIST,
						CascadeType.MERGE,
						CascadeType.DETACH,
						CascadeType.REFRESH
					}			
		)
	@JoinColumn(name="order_id")
	private ProductOrder order;
		
	
	//************** Constructors ****************************************************/
	
	/**
	 * Constructor
	 * @param int id
	 * @param int quantity
	 * @param Product product
	 * @param ProductOrder productOrder
	 * */
	public OrderItem(int id,int quantity, Product product, ProductOrder productOrder) {
		if (quantity >0 && product != null && productOrder != null) {
			setQuantity(quantity);
			setProduct(product);
			setProductOrder(productOrder);
			this.orderItemId =id;
		} 
		else {
		throw new IllegalArgumentException("Parameters can't be null");
		}
	}
	/**
	 * Constructor
	 * @param int quantity
	 * @param Product product
	 * @param ProductOrder productOrder
	 * */
	public OrderItem(int quantity, Product product, ProductOrder productOrder) {
		this(0,quantity,product,productOrder);
	}
	
	
	/**
	 * Default Constructor
	 * 
	 * */
	
	public OrderItem() {
		//default constructor
	}
	
	
	
	/************** Methods ****************************************************/
	
	/**
	 * Calculates the quantity multiplied by the price of the product
	 * @RETURN Return BigDecimal 
	 * */
	public BigDecimal calculatePrice() {
		return BigDecimal
						.valueOf(quantity).
						multiply(product.getPrice())
						.setScale(2);
	}

	/**************Getters & Setters ****************************************************/
	
	/**Getter for field int quantity
	 * @Return int quantity
	 * */
	public int getQuantity() {
		return quantity;
	}
	
	/**Setter for field int quantity
	 * @Return void
	 * */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**Getter for field Product product
	 * @Return Product product
	 * */
	public Product getProduct() {
		return product;
	}
	/**Setter for field Product product
	 * @Return Product product
	 * */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**Getter for field ProductOrder productorder
	 * @Return ProductOrder productOrder
	 * */
	public ProductOrder getProductOrder() {
		return order;
	}
	/**Setter for field ProductOrder productorder
	 * @Return void
	 * */
	public void setProductOrder(ProductOrder productOrder) {
		this.order = productOrder;
	}
	
	/**Setter for field int id
	 * @Return int id
	 * */
	public int getId() {
		return orderItemId;
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
		result = prime * result + orderItemId;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + quantity;
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
		OrderItem other = (OrderItem) obj;
		if (orderItemId != other.orderItemId)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
}
