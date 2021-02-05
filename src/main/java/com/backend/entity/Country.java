package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "countries")
public class Country implements Serializable {

    private static final long serialVersionUID = 995876463200746259L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(max = 60, message = "el tamaño tiene que ser menor a 60 caracteres")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinces_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Provinces provinces_id;

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

    public Provinces getProvinces_id() {
        return provinces_id;
    }

    public void setProvinces_id(Provinces provinces_id) {
        this.provinces_id = provinces_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((provinces_id == null) ? 0 : provinces_id.hashCode());
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
        Country other = (Country) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (provinces_id == null) {
            return other.provinces_id == null;
        } else return provinces_id.equals(other.provinces_id);
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", name=" + name + ", provinces_id=" + provinces_id + "]";
    }

    public Country(Long id, @Size(max = 60, message = "el tamaño tiene que ser menor a 60 caracteres") String name,
                   Provinces provinces_id) {
        this.id = id;
        this.name = name;
        this.provinces_id = provinces_id;
    }

    public Country() {
    }


}
