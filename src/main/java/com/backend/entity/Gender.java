package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Genders")
public class Gender implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7186518284140433381L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 60, message = "el tamaño tiene que estar entre 2 y 60")
    @NotEmpty(message = "No puede estar vacio")
    private String summary;
    @Column()
    private Boolean active;

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
        Gender other = (Gender) obj;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
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
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (updated_at == null) {
            return other.updated_at == null;
        } else return updated_at.equals(other.updated_at);
    }

    @Override
    public String toString() {
        return "Gender [id=" + id + ", summary=" + summary + ", active=" + active + ", updated_at=" + updated_at
                + ", created_at=" + created_at + "]";
    }

    public Gender(Long id,
                  @Size(min = 2, max = 60, message = "el tamaño tiene que estar entre 2 y 60") @NotEmpty(message = "No puede estar vacio") String summary,
                  Boolean active, Date updated_at, Date created_at) {
        this.id = id;
        this.summary = summary;
        this.active = active;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Gender() {
    }

    public Gender(Long id) {

    }

}
