package com.ibm.aa.adapter.model;

public class SkillConfigRequest {

	
	private String crUserName;
	private String crPassword;
	private String connectorName;
	
	
	public String getCrUserName() {
		return crUserName;
	}
	public void setCrUserName(String crUserName) {
		this.crUserName = crUserName;
	}
	public String getCrPassword() {
		return crPassword;
	}
	public void setCrPassword(String crPassword) {
		this.crPassword = crPassword;
	}
	public String getConnectorName() {
		return connectorName;
	}
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}
	
}
