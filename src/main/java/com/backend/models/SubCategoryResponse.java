package com.backend.models;

import java.util.Date;

public class SubCategoryResponse {
	
	private Long id;
	private String summary;
	private Long categoryId;
	private Date created_at;
	private Date updated_at;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public SubCategoryResponse(Long id, String summary, Long categoryId, Date created_at, Date updated_at) {
		this.id = id;
		this.summary = summary;
		this.categoryId = categoryId;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
	
	

}
