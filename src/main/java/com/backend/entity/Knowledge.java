package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "knowledges")
public class Knowledge implements Serializable {

    private static final long serialVersionUID = -8191317067212653861L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 2, max = 60, message = "el tamaño tiene que estar entre 2 y 255")
    @NotEmpty(message = "title a no puede estar vacio")
    @Column(nullable = false, unique = true)
    private String title;

    @NotEmpty(message = "summary no puede estar vacio")
    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    private String summary;

    @NotEmpty(message = "files no puede estar vacio")
    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    private String files;


    //@NotNull(message = "no puede estar vacio")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Knowledge(String summary, String title) {
        this.summary = summary;
        this.title = title;

    }

    public Knowledge() {
    }


    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        // updateAt = createdAt;
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

    public String getConfiguration() {
        return title;
    }


    public Date getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
        Knowledge other = (Knowledge) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (summary == null) {
            return other.summary == null;
        } else return summary.equals(other.summary);
    }

    @Override
    public String toString() {
        return "Channel [id=" + id + ", name=" + summary + ", title=" + title + "]";
    }


}
