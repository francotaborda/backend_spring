package com.backend.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientRequest {

    private Long client_type;
    @NotNull
    private Long document_type;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    private String document;

    @Size(min = 3, max = 100)
    private String cuit_cuil;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 60)
    private String firstname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 60)
    private String lastname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 60)
    private String social_reason;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String address;

    @Size(min = 3, max = 255)
    private String alternative_adress;

    @NotNull
    private Long city;

    @NotNull
    private long country;

    @Size(max = 10)
    private String zipcode;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String area_code;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 13)
    private String phone;

    @Size(min = 3, max = 13)
    private String alternative_phone;

    @Size(max = 255)
    private String product_service;

    @NotNull
    private Boolean active;

    @NotNull
    private Long gender_id;


    public Long getClient_type() {
        return client_type;
    }

    public void setClient_type(Long client_type) {
        this.client_type = client_type;
    }

    public Long getDocument_type() {
        return document_type;
    }

    public void setDocument_type(Long document_type) {
        this.document_type = document_type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCuit_cuil() {
        return cuit_cuil;
    }

    public void setCuit_cuil(String cuit_cuil) {
        this.cuit_cuil = cuit_cuil;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSocial_reason() {
        return social_reason;
    }

    public void setSocial_reason(String social_reason) {
        this.social_reason = social_reason;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlternative_adress() {
        return alternative_adress;
    }

    public void setAlternative_adress(String alternative_adress) {
        this.alternative_adress = alternative_adress;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternative_phone() {
        return alternative_phone;
    }

    public void setAlternative_phone(String alternative_phone) {
        this.alternative_phone = alternative_phone;
    }

    public String getProduct_service() {
        return product_service;
    }

    public void setProduct_service(String product_service) {
        this.product_service = product_service;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getGender_id() {
        return gender_id;
    }

    public void setGender_id(Long gender_id) {
        this.gender_id = gender_id;
    }

}
