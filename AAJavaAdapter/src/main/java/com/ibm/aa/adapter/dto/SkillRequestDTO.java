package com.ibm.aa.adapter.dto;

/**
 * DTO for Skill request
 * 
 * @author Rakesh R Pai
 *
 */
public class SkillRequestDTO {
	
	private String accessToken;
	private String workspaceType;
	
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
	 * @return the workspaceType
	 */
	public String getWorkspaceType() {
		return workspaceType;
	}
	/**
	 * @param workspaceType the workspaceType to set
	 */
	public void setWorkspaceType(String workspaceType) {
		this.workspaceType = workspaceType;
	}
}