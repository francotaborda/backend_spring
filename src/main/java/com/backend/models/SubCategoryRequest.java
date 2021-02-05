package com.backend.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SubCategoryRequest {
	@NotBlank
    @Size(min = 3, max = 255)
	private String summary;
	private Long category_id;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
}
