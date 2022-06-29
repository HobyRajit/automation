package com.ibm.aa.adapter.model;

public class SkillConfigResponse {

	private String userId;
	private String workspaceType;
	private String accessToken;
	private String connectorName;
	private SkillError error;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWorkspaceType() {
		return workspaceType;
	}
	public void setWorkspaceType(String workspaceType) {
		this.workspaceType = workspaceType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getConnectorName() {
		return connectorName;
	}
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}
	public SkillError getError() {
		return error;
	}
	public void setError(SkillError error) {
		this.error = error;
	}
}
