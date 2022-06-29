package com.ibm.aa.adapter.model;

public class SkillRunRequest {
	
	private String userId;
	private String skillId;
	private String connectorName;
	private String accessToken;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String botId) {
		this.skillId = botId;
	}
	public String getConnectorName() {
		return connectorName;
	}
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
