package com.ibm.aa.adapter.model;

import java.util.List;

public class SkillResponse {

	private String workspaceType;
	private String connectorName;
	private List<SkillInfo>  skills;
	private SkillError error;
	
	public String getWorkspaceType() {
		return workspaceType;
	}
	public void setWorkspaceType(String workspaceType) {
		this.workspaceType = workspaceType;
	}
	public String getConnectorName() {
		return connectorName;
	}
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}
	public List<SkillInfo> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillInfo> skills) {
		this.skills = skills;
	}
	public SkillError getError() {
		return error;
	}
	public void setError(SkillError error) {
		this.error = error;
	}
}
