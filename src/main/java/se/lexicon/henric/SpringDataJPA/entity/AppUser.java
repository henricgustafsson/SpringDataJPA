package se.lexicon.henric.SpringDataJPA.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {

	//**************Fields ****************************************************/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	
		
	/**
	 * Constructor
	 * @Id
	 * @param String firstName
	 * @param String lasttName
	 *  @param String email
	 * */
	
	public AppUser(int id, String firstName, String lastName, String email) {
		if (firstName != null && lastName !=null & email !=null) {
			setFirstname(firstName);
			setLastname(lastName);
			setEmail(email);
			this.id = id;
		} 
		else {
		throw new IllegalArgumentException("Parameters can't be null");
		}
	}
	
	
	/**
	 * Constructor
	 * @param String firstName
	 * @param String lasttName
	 *  @param String email
	 * */
	public AppUser(String firstName, String LastName, String email) {
		this(0,firstName,LastName,email);
	}
	
	/**
	 * Default Constructor
	 * 
	 * */
	public AppUser() {
		//default constructor
	}

	
	
	/**************Getters & Setters ****************************************************/

	/**Getter for field String firstName
	 * @Return String firstName
	 * */
	public String getFirstname() {
		return firstName;
	}
	
	
	/**Setter for field String firstName
	 * @Return void
	 * */
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	
	/**Getter for field String LasttName
	 * @Return String LastName
	 * */
	public String getLastname() {
		return lastName;
	}
	
	/**Setter for field String lastName
	 * @Return String void
	 * */
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}
	
	/**Getter for field String email
	 * @Return String email
	 * */
	public String getEmail() {
		return email;
	}
	
	/**Setter for field String email
	 * @Return void
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**Getter for field int id
	 * @Return int id
	 * */
	public int getId() {
		return id;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * Overrides: equals(...) in Object
	 * Indicates whether some other object is "equal to" this one. 
	 * public boolean equals(Object obj)
	*@param Object obj
	*@return boolean
	**/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	
}
