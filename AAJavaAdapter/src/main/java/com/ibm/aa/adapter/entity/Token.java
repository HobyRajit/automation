/**
 * 
 */
package com.ibm.aa.adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Placeholder for token
 * 
 * @author Rakesh R Pai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

	private String token;
	private User user;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
