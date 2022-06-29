package com.ibm.aa.adapter.dto;

/**
 * DTO for Skill run request
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillRunRequestDTO {
	
	private String accessToken;
	private String skillId;
	private String userId;
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
