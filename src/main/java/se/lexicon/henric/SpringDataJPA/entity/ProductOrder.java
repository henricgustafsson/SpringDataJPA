package se.lexicon.henric.SpringDataJPA.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class ProductOrder {
//id, orderDateTime, some collection of OrderItem, customer (AppUser
	
	
	//**************Fields ****************************************************/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private LocalDate OrderDateTime;
	
	/*ProductOrder has a @OneToMany relationship with OrderItem. 
	 * Set orphanremoval set to true
	 */
	@OneToMany(
				fetch = FetchType.EAGER,
				mappedBy = "order",
				cascade= {
							CascadeType.PERSIST,
							CascadeType.MERGE,
							CascadeType.DETACH,
							CascadeType.REFRESH
						},
				orphanRemoval = true
			)
	private List<OrderItem> orderItems;	
	
	//@ManyToOne relationship with AppUser
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade= {
						CascadeType.PERSIST,
						CascadeType.MERGE,
						CascadeType.DETACH,
						CascadeType.REFRESH
					}			
		)
	@JoinColumn(name="user_id")
	private AppUser customer;
		
	
	
	//************** Constructors ****************************************************/

		/**
		 * Constructor
		 * @param int id
		 * @param LocalDate OrderDateTime
		 * @param List<OrderItem> orderItems
		 * @param AppUser customer
		 */
		
		public ProductOrder(int id,LocalDate orderDateTime, List<OrderItem> orderItems, AppUser customer) {
			
			if (orderItems != null && orderDateTime !=null && customer != null) {
				
				setOrderDateTime(orderDateTime);		
				//Set each orderItems productOrder to this instance of productOrder				
				orderItems.forEach(orderItem->orderItem.setProductOrder(this));
				this.customer = customer;
				this.orderId =id;
					
			}
			else {
				throw new IllegalArgumentException("Parameters can't be null");
			}
		
			
		}
		
		
		/**
		 * Constructor
		 * @param int id
		 * @param LocalDate OrderDateTime
		 * @param AppUser customer
		 */
		public ProductOrder(LocalDate orderDateTime,AppUser customer) {
		
				this(0,orderDateTime, new ArrayList<OrderItem>(), customer);
			
		}
		
		/**
		 * Default Constructor
		 * 
		 * */
		public ProductOrder() {
			
			//Default constructor
		
		}
		
		
		
		/************** Methods ****************************************************/
		
		/**
		 * Method to bi-directionally remove orderItem 
		 * @return Boolean		
		 **/
		public boolean removeOrderItem(OrderItem orderItem) {
			
			//NULL-check on collection OrderItems
			if(orderItems == null) orderItems = new ArrayList<>();
			//check if orderItem exists in List<OrderItem> orderItems
			if(orderItems.contains(orderItem)) {
				//remove productOrder from orderItem.productOrder
				orderItem.setProductOrder(null);
				//remove from collection return Boolean indicating if successful
				return orderItems.remove(orderItem);
			}
			else {		
				return false;
			}
		}
		
		
		/**
		 * Method to bi-directionally add orderItem 
		 * @return Boolean		
		 **/
		public boolean addOrderItem(OrderItem orderItem) {
			
			//NULL-check on collection OrderItems
			if(orderItems == null) orderItems = new ArrayList<>();
			//check if orderItem exists in List<OrderItem> orderItems
			if(!orderItems.contains(orderItem)) {
				//add to collection
				boolean added= orderItems.add(orderItem);
				//add productOrder from orderItem.productOrder if item added successfully
				if(added) orderItem.setProductOrder(this);
				//return Boolean indicating if successful
				return added;
			}
			else {		
				return false;
			}
		}
		
		
		/**
		 * Method to calculate total prize
		 * @return BigDecimal total		
		 **/
		public BigDecimal calculateTotalPrice() {
			BigDecimal total = BigDecimal.ZERO;
			//for each item, calculate price by calling calculatePrice() and add to total
			orderItems.forEach(orderItem ->total.add(orderItem.calculatePrice()));
			return total;
		}
		
				
		
		/**************Getters & Setters ****************************************************/

		/**Getter for field LocalDate orderDateTime
		 * @Return LocalDate orderDateTime
		 * */
		
		public LocalDate getOrderDateTime() {
			return OrderDateTime;
		}
		
		/**Setter for field LocalDate orderDateTime
		 * @Return void
		 * */
		
		public void setOrderDateTime(LocalDate orderDateTime) {
			OrderDateTime = orderDateTime;
		}
		
		/**Getter for field List<OrderItem> orderItems(
		 * @Return orderItems
		 * */
		
		public List<OrderItem> getOrderItems() {
			return orderItems;
		}
		
		/**Setter for field List<OrderItem> orderItems(
		 * @Return void
		 * */
		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}
		
		/**Getter for field AppUser customer
		 * @Return AppUser customer
		 * */
		public AppUser getCustomer() {
			return customer;
		}
		
		/**Setter for field AppUser customer
		 * @Return void
		 * */
		public void setCustomer(AppUser customer) {
			this.customer = customer;
		}
		
		/**Getter for field int id
		 * @Return int id
		 * */
		public int getId() {
			return orderId;
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
			result = prime * result + ((OrderDateTime == null) ? 0 : OrderDateTime.hashCode());
			result = prime * result + ((customer == null) ? 0 : customer.hashCode());
			result = prime * result + orderId;
			result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
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
			ProductOrder other = (ProductOrder) obj;
			if (OrderDateTime == null) {
				if (other.OrderDateTime != null)
					return false;
			} else if (!OrderDateTime.equals(other.OrderDateTime))
				return false;
			if (customer == null) {
				if (other.customer != null)
					return false;
			} else if (!customer.equals(other.customer))
				return false;
			if (orderId != other.orderId)
				return false;
			if (orderItems == null) {
				if (other.orderItems != null)
					return false;
			} else if (!orderItems.equals(other.orderItems))
				return false;
			return true;
		}

		
		
		
		
		
}
