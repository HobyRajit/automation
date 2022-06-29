package com.ibm.aa.adapter.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Placeholder for Skill
 * 
 * @author Rakesh R Pai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Skill {
	
	private Page page;

	private List<SkillDetail> value;

	/**
	 * @return the value
	 */
	@JsonProperty("list")
	public List<SkillDetail> getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(List<SkillDetail> value) {
		this.value = value;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	
}
