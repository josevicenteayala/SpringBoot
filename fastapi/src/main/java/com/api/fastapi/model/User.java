/**
 * 
 */
package com.api.fastapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author vin00
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long identification;
	private String name;
	
	@Column(name="LASTNAME")
	private String lastName;
	/**
	 * @return the identification
	 */
	public Long getIdentification() {
		return identification;
	}
	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(Long identification) {
		this.identification = identification;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
