/**
 * 
 */
package com.ibm.aa.adapter.model;

/**
 * Model for Skill info
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillInfo {

		
	private String skillId;
	

	private String folderName;
	
	private String skillName;
	
	private String skillDesc;


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
	
	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
}
