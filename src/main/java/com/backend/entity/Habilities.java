package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "habilities")
public class Habilities implements Serializable {

    /**
     *
     */
    public static final long serialVersionUID = 7556812891602259155L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotEmpty(message = "name no puede estar vacio")
    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    @Column(nullable = false)
    private String name;

    @Column
    private String experience;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Habilities(String name, String experience) {
        this.name = name;
        this.experience = experience;
    }

    public Habilities() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habilities hability = (Habilities) o;
        return id.equals(hability.id) &&
                name.equals(hability.name) &&
                experience.equals(hability.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience);
    }

    @Override
    public String toString() {
        return "Habilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
