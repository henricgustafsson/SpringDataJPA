package se.lexicon.henric.SpringDataJPA.entity;

import java.time.LocalDate;
import java.util.List;

public class ProductOrder {
//id, orderDateTime, some collection of OrderItem, customer (AppUser
	
	
	//**************Fields ****************************************************/
		static int _COUNTER =0;
		int id;
		LocalDate OrderDateTime;
		List<OrderItem> orderItems;
		AppUser customer;
		
		
		
		

		/**
		 * Constructor
		 * @param int id
		 * @param LocalDate OrderDateTime
		 * @param List<OrderItem> orderItems
		 * @param AppUser customer
		 */
		
		public ProductOrder(LocalDate orderDateTime, List<OrderItem> orderItems, AppUser customer) {
			
			//TODO: implement ProductOrder
			setOrderDateTime(orderDateTime);
			this.orderItems = orderItems;
			this.customer = customer;
			this.id = ++_COUNTER;
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
			return id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((OrderDateTime == null) ? 0 : OrderDateTime.hashCode());
			result = prime * result + ((customer == null) ? 0 : customer.hashCode());
			result = prime * result + id;
			result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
			return result;
		}

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
			if (id != other.id)
				return false;
			if (orderItems == null) {
				if (other.orderItems != null)
					return false;
			} else if (!orderItems.equals(other.orderItems))
				return false;
			return true;
		}

		
		
		
		
		
}
