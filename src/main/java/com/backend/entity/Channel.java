package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "channels")
public class Channel implements Serializable {

    private static final long serialVersionUID = 9210869251554293311L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name no puede estar vacio")
    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    @NotEmpty(message = "configuration a no puede estar vacio")
    private String configuration;

    //@NotNull(message = "no puede estar vacio")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //@NotNull(message = "no puede estar vacio")
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;


    public Channel(String name, String configuration) {
        this.name = name;
        this.configuration = configuration;

    }

    public Channel() {

    }


    @PrePersist
    public void prePersist() {
        createdAt = new Date();
       updateAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Channel other = (Channel) obj;
        if (configuration == null) {
            if (other.configuration != null)
                return false;
        } else if (!configuration.equals(other.configuration))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Channel [id=" + id + ", name=" + name + ", configuration=" + configuration + "]";
    }


}
