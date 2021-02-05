package com.backend.models;

import java.util.Date;

import com.backend.entity.Client;

public class ClientResponse {

    private Long id;
    private Long clientType;
    private Long document_type;
    private String document;
    private String cuit_cuil;
    private String firstName;
    private String lastname;
    private String social_reasons;
    private String email;
    private String address;
    private String alternative_adress;
    private Long city;
    private Long country;
    private String zipcode;
    private String area_code;
    private String phone;
    private String alternative_phone;
    private String product_service;
    private Boolean active;
    private Long gender_id;
    private Date created_at;
    private Date updated_at;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getClientType() {
        return clientType;
    }


    public void setClientType(Long clientType) {
        this.clientType = clientType;
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


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getSocial_reasons() {
        return social_reasons;
    }


    public void setSocial_reasons(String social_reasons) {
        this.social_reasons = social_reasons;
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

    public Long getCountry() {
        return country;
    }


    public void setCountry(Long country) {
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


    public Date getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }


    public Date getUpdated_at() {
        return updated_at;
    }


    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

	public ClientResponse(Client client){
    	if (client!=null){
			this.id = client.getId();
			this.clientType = client.getClientType();
			this.document_type = document_type;
			this.document = client.getIDnumber();
			this.cuit_cuil = client.getCuitCuil();
			this.firstName = client.getFirstName();
			this.lastname = client.getLastName();
			this.social_reasons = client.getSocialReason();
			this.email = client.getEmail();
			this.address = client.getAddress();
			this.alternative_adress = client.getAlternativeAddress();
			this.city = client.getCity();
			this.country = client.getCountry();
			this.zipcode = client.getZipCode();
			this.area_code = client.getAreaCode();
			this.phone = client.getPhone();
			this.alternative_phone = client.getAlternativePhone();
			this.product_service = client.getProductService();
			this.active = client.getActive();
			this.gender_id = (client.getGenderId()).getId();
			this.created_at = client.getCreatedAt();
			this.updated_at = client.getUpdateAt();

		}



	}



/*
    public ClientResponse(Long id, Long clientType, Long document_type, String document, String cuit_cuil,
						  String firstName, String lastname, String social_reasons, String email, String address,
						  String alternative_adress, Long city, Long country, String zipcode, String area_code, String phone,
						  String alternative_phone, String product_service, Boolean active, Long gender_id, Date created_at,
						  Date updated_at) {
        this.id = id;
        this.clientType = clientType;
        this.document_type = document_type;
        this.document = document;
        this.cuit_cuil = cuit_cuil;
        this.firstName = firstName;
        this.lastname = lastname;
        this.social_reasons = social_reasons;
        this.email = email;
        this.address = address;
        this.alternative_adress = alternative_adress;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.area_code = area_code;
        this.phone = phone;
        this.alternative_phone = alternative_phone;
        this.product_service = product_service;
        this.active = active;
        this.gender_id = gender_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
*/



    public ClientResponse() {
    }


}
