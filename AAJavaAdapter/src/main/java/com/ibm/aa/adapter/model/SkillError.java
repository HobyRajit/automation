package com.ibm.aa.adapter.model;

/**
 * Model for Skill Error
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillError {

	private long code;
	
	private String description;

	/**
	 * @return the code
	 */
	public long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
