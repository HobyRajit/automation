package com.ibm.aa.adapter.dto;

/**
 * DTO for Skill run response
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillRunResponseDTO {
	
	private String result;
	
	private SkillErrorDTO error;

	/**
	 * @return the error
	 */
	public SkillErrorDTO getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(SkillErrorDTO error) {
		this.error = error;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
