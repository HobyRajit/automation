package com.ibm.aa.adapter.dto;

/**
 * DTO for Skill config response
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillConfigResponseDTO {
	
	private String accessToken;
	private String userId;
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
