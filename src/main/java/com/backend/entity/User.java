package com.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -60081457787530345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column(name = "access_token")
    private String accessToken;

    @Column
    private String picture;

    @Column
    private String internal;

    @Column(name = "personal_phone")
    private String personalPhone;

    @Column(name = "status_id")
    private Long statusId;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

//	@Transient
//	private Long permissionId;

    @Transient
    private String confirmPassword;


    public User(String firstname, String lastname) {
    }

    //introduce fecha automaticamente
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date();
        deletedAt = new Date();
    }

    // User_Roles Relation Table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<Role> roles;

    // User_Habilities Relation Table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_habilities",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "hability_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<Habilities> habilities;

    // User_Teams Relation Table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_teams",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<Team> teams;

    // User_Organizations Relation Table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_organization",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<Organization> organizations;

    // User_Channels Relation Table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_channel",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<Channel> channels;

    public User() {
        super();
    }

    public User(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
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

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Habilities> getHabilities() {
        return habilities;
    }

    public void setHabilities(Set<Habilities> habilities) {
        this.habilities = habilities;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email);
    }


	/*	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(address, user.address) &&
				Objects.equals(email, user.email) &&
				Objects.equals(password, user.password) &&
				Objects.equals(accessToken, user.accessToken) &&
				Objects.equals(picture, user.picture) &&
				Objects.equals(internal, user.internal) &&
				Objects.equals(personalPhone, user.personalPhone) &&
				Objects.equals(statusId, user.statusId) &&
				Objects.equals(active, user.active) &&
				Objects.equals(createdBy, user.createdBy) &&
				Objects.equals(updatedBy, user.updatedBy) &&
				Objects.equals(createdAt, user.createdAt) &&
				Objects.equals(updatedAt, user.updatedAt) &&
				Objects.equals(deletedAt, user.deletedAt) &&
				Objects.equals(confirmPassword, user.confirmPassword) &&
				Objects.equals(roles, user.roles) &&
				Objects.equals(teams, user.teams) &&
				Objects.equals(habilities, user.habilities) &&
				Objects.equals(organizations, user.organizations) &&
				Objects.equals(channels, user.channels);
	}*/


    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, email, password, accessToken, picture, internal,
                personalPhone, statusId, active, createdBy, updatedBy, createdAt, updatedAt, deletedAt,
                teams, confirmPassword, roles, habilities, organizations, channels);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", picture='" + picture + '\'' +
                ", internal='" + internal + '\'' +
                ", personalPhone='" + personalPhone + '\'' +
                ", statusId='" + statusId + '\'' +
                ", active='" + active + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles +
                ", habilities=" + habilities +
                ", teams=" + teams +
                ", organizations=" + organizations +
                ", channels=" + channels +
                '}';
    }

}
