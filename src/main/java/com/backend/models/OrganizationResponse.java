package com.backend.models;

import java.util.Date;

public class OrganizationResponse {
	
	private Long id;
    private String orgname;
    private Boolean active;
    private Long companyid;
    private Date create_at;
    private Date updated_at;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public OrganizationResponse(Long id, String orgname, Boolean active, Long companyid, Date create_at,
			Date updated_at) {
		this.id = id;
		this.orgname = orgname;
		this.active = active;
		this.companyid = companyid;
		this.create_at = create_at;
		this.updated_at = updated_at;
	}
	public OrganizationResponse() {
	}
	
	
	
	
    

}
