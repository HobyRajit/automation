/**
 * 
 */
package com.ibm.aa.adapter.dto;

/**
 * DTO for Skill info
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillInfoDTO {

	private String skillId;
	private String skillName;
	private String skillDesc;
	private String folderName;

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/**
	 * @return the skillDesc
	 */
	public String getSkillDesc() {
		return skillDesc;
	}

	/**
	 * @param skillDesc the skillDesc to set
	 */
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	/**
	 * @return the skillId
	 */
	public String getSkillId() {
		return skillId;
	}

	/**
	 * @param skillId the skillId to set
	 */
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
