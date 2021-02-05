package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="sub_categories")
public class SubCategory implements Serializable{
	
	private static final long serialVersionUID = 253465114469967004L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max=255, message="maximo 255 caracteres")
	@Column(nullable = false)
	private String summary;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category categoryId;
		
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	
	@PrePersist 
	public void prePersist() { 
		createdAt = new Date();
		updatedAt = new Date();
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

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category category_id) {
		this.categoryId = category_id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date created_at) {
		this.createdAt = created_at;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updated_at) {
		this.updatedAt = updated_at;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		SubCategory other = (SubCategory) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
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
		if (updatedAt == null) {
            return other.updatedAt == null;
		} else return updatedAt.equals(other.updatedAt);
    }

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", summary=" + summary + ", category_id=" + categoryId + ", created_at="
				+ createdAt + ", updated_at=" + updatedAt + "]";
	}

	public SubCategory(Long id, String summary, Category categoryId, Date createdAt, Date updatedAt) {
		this.id = id;
		this.summary = summary;
		this.categoryId = categoryId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public SubCategory() {
	}


}
