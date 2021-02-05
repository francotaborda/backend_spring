package com.backend.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationRequest {
	
	@NotNull
	@NotBlank
    @Size(min = 3, max = 100)
    private String orgname;
	
    @NotNull(message = "no puede estar vacio")
	private Boolean active;
	
    @NotNull
	private Long company_id;

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

}
