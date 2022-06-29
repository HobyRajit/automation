package com.ibm.aa.adapter.model;

public class SkillRunResponse {
	
	private String result;
	private SkillError error;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public SkillError getError() {
		return error;
	}
	public void setError(SkillError error) {
		this.error = error;
	}

}
