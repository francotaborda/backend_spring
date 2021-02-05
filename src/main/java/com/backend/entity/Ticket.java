package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String summary;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_ticket",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false, updatable = false)
            })
    private Set<User> userid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client clientId;

    /*
     * @OneToMany(fetch = FetchType.LAZY)
     *
     * @JoinTable(name = "user_tickets", joinColumns = @JoinColumn(name = "id"),
     * inverseJoinColumns = @JoinColumn(name = "user_id")) private User users;
     */

    @Column(name = "expiration_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_history", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<TicketHistory> ticketHistoryId;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ticket_category",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @Column
    private Long associatedWith;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Priority priorityId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Stage stage;

    @Column
    private String files;

    @Column
    private String contactName;

    @Column
    private String reportedBy;

    @Column
    private Long createdBy;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "date_Closed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClosed;

    //introduce fecha automaticamente
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


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<User> getUserid() {
        return userid;
    }


    public void setUserid(Set<User> userid) {
        this.userid = userid;
    }


    public Client getClientId() {
        return clientId;
    }


    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<TicketHistory> getTicketHistoryId() {
        return ticketHistoryId;
    }

    public void setTicketHistoryId(List<TicketHistory> ticketHistoryId) {
        this.ticketHistoryId = ticketHistoryId;
    }

    public Set<Category> getCategories() {
        return categories;
    }


    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    public Long getAssociatedWith() {
        return associatedWith;
    }


    public void setAssociatedWith(Long associatedWith) {
        this.associatedWith = associatedWith;
    }

    public Priority getPriorityId() {
        return priorityId;
    }


    public void setPriorityId(Priority priorityId) {
        this.priorityId = priorityId;
    }


    public String getFiles() {
        return files;
    }


    public void setFiles(String files) {
        this.files = files;
    }


    public String getContactName() {
        return contactName;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public String getReportedBy() {
        return reportedBy;
    }


    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }


    public Long getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

}
