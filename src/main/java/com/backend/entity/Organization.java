package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Organizations")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 60, message = "el tamaño tiene que estar entre 2 y 60")
    @NotEmpty(message = "No puede estar vacio")
    private String orgname;
    @Column(nullable = false)
    @NotNull(message = "no puede estar vacio")
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Company company_id;

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @PrePersist
    public void prePersist() {
        created_at = new Date();
        updated_at = new Date();
    }

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

    public Company getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Company company_id) {
        this.company_id = company_id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
        result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((orgname == null) ? 0 : orgname.hashCode());
        result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Organization other = (Organization) obj;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        if (company_id == null) {
            if (other.company_id != null)
                return false;
        } else if (!company_id.equals(other.company_id))
            return false;
        if (created_at == null) {
            if (other.created_at != null)
                return false;
        } else if (!created_at.equals(other.created_at))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (orgname == null) {
            if (other.orgname != null)
                return false;
        } else if (!orgname.equals(other.orgname))
            return false;
        if (updated_at == null) {
            return other.updated_at == null;
        } else return updated_at.equals(other.updated_at);
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", orgname=" + orgname + ", active=" + active + ", company_id=" + company_id
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

    public Organization(Long id,
                        @Size(min = 2, max = 60, message = "el tamaño tiene que estar entre 2 y 60") @NotEmpty(message = "No puede estar vacio") String orgname,
                        @NotNull(message = "no puede estar vacio") Boolean active, Company company_id, Date created_at,
                        Date updated_at) {
        this.id = id;
        this.orgname = orgname;
        this.active = active;
        this.company_id = company_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Organization() {
    }

}
