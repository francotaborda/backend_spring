package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "provinces")
public class Provinces implements Serializable {

    private static final long serialVersionUID = -5337793454005727900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cities_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City cities_id;
    @Column(nullable = false)
    @Size(max = 60, message = "el tamaño tiene que ser menor a 60 caracteres")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCities_id() {
        return cities_id;
    }

    public void setCities_id(City cities_id) {
        this.cities_id = cities_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cities_id == null) ? 0 : cities_id.hashCode());
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
        Provinces other = (Provinces) obj;
        if (cities_id == null) {
            if (other.cities_id != null)
                return false;
        } else if (!cities_id.equals(other.cities_id))
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
        return "Provinces [id=" + id + ", cities_id=" + cities_id + ", name=" + name + "]";
    }

    public Provinces(Long id, City cities_id,
                     @Size(max = 60, message = "el tamaño tiene que ser menor a 60 caracteres") String name) {
        this.id = id;
        this.cities_id = cities_id;
        this.name = name;
    }

    public Provinces() {
    }


}
