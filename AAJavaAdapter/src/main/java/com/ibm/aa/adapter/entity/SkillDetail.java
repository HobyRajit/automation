package com.ibm.aa.adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Placeholder for Process Detail
 * 
 * @author Rakesh R Pai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDetail {

	private String id;
	private String parentId;
	private String name;
	private String path;
	private String description;
	private String type;
	private String folderCount;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the folderCount
	 */
	public String getFolderCount() {
		return folderCount;
	}
	/**
	 * @param folderCount the folderCount to set
	 */
	public void setFolderCount(String folderCount) {
		this.folderCount = folderCount;
	}
}
