package com.ibm.aa.adapter.dto;

import java.util.List;

/**
 * DTO for Skill response
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillResponseDTO {
	
	private List<SkillInfoDTO> skills;
	
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
	 * @return the skills
	 */
	public List<SkillInfoDTO> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<SkillInfoDTO> skills) {
		this.skills = skills;
	}

}
