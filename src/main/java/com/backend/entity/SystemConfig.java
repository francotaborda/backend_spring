package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "system_conf")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "conf_name no puede estar vacio")
    @Size(min = 2, max = 255, message = "el tamaño tiene que estar entre 2 y 255")
    @Column(nullable = false)
    private String conf_name;

    @Column
    private String summary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_system_conf",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "system_conf_id"))
    private Set<User> users;


    public SystemConfig() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConf_name() {
        return conf_name;
    }

    public void setConf_name(String name) {
        this.conf_name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String description) {
        this.summary = description;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((conf_name == null) ? 0 : conf_name.hashCode());
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
        SystemConfig other = (SystemConfig) obj;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (conf_name == null) {
            return other.conf_name == null;
        } else return conf_name.equals(other.conf_name);
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + conf_name + ", description=" + summary + "]";
    }


}
