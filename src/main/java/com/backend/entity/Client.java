package com.backend.entity;

import com.backend.models.ClientRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = -8356024405562606023L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long clientType;
    @Column
    private Long documentType;
    @Size(max = 60, message = "el tamaño no puede ser mayor a 60 caracteres")
    @Column(name = "ID_number", nullable = false, unique = true)
    private String IDnumber;
    @Size(max = 13, message = "el tamaño no puede ser mayor a 13 caracteres")
    @Column(unique = true)
    private String cuitCuil;
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 2, max = 60, message = "el tamaño tiene que se mayor que 2 y menor a 60")
    @Column(nullable = false)
    private String firstName;
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 2, max = 60, message = "el tamaño tiene que se mayor que 2 y mayor a 60")
    @Column(nullable = false)
    private String lastName;
    @Size(max = 20, message = "el tamaño puede ser hasta 10 caracteres")
    @Column()
    private String socialReason;
    @NotEmpty(message = "No puede estar vacio")
    @Email(message = " Formato de Email incorrecto")
    @Column(nullable = false, unique = true)
    @Size(max = 60, message = "el tamaño puede mayor a 60 caracteres")
    private String email;
    @Column(nullable = false)
    @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres")
    private String address;
    @Column()
    @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres")
    private String alternativeAddress;
    @Column(nullable = false)
    private Long city;
    @Column(nullable = false)
    private Long country;
    @Column(nullable = false)
    @Size(max = 10, message = "el tamaño puede mayor a 10 caracteres")
    private String zipCode;
    @Column(nullable = false)
    private String areaCode;
    @Column(nullable = false)
    @Size(max = 13, message = "el tamaño puede mayor a 13 caracteres")
    private String phone;
    @Column()
    @Size(max = 13, message = "el tamaño puede mayor a 13 caracteres")
    private String alternativePhone;
    @Column
    @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres")
    private String productService;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Gender genderId;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updateAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientType() {
        return clientType;
    }

    public void setClientType(Long cliente_type) {
        this.clientType = cliente_type;
    }

    public Long getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Long document_type) {
        this.documentType = document_type;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String document) {
        this.IDnumber = document;
    }

    public String getCuitCuil() {
        return cuitCuil;
    }

    public void setCuitCuil(String cuit_cuil) {
        this.cuitCuil = cuit_cuil;
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

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String social_reason) {
        this.socialReason = social_reason;
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

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getAlternativeAddress() {
        return alternativeAddress;
    }

    public void setAlternativeAddress(String alternative_adress) {
        this.alternativeAddress = alternative_adress;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String area_code) {
        this.areaCode = area_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternative_phone) {
        this.alternativePhone = alternative_phone;
    }

    public String getProductService() {
        return productService;
    }

    public void setProductService(String product_service) {
        this.productService = product_service;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Gender getGenderId() {
        return genderId;
    }


    public void setGenderId(Gender gender_id) {
        this.genderId = gender_id;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", cliente_type=" + clientType + ", document_type=" + documentType
                + ", document=" + IDnumber + ", cuit_cuil=" + cuitCuil + ", firstName=" + firstName + ", lastname="
                + lastName + ", social_reason=" + socialReason + ", email=" + email + ", adress=" + address
                + ", alternative_adress=" + alternativeAddress + ", city=" + city + ", country=" + country
                + ", zipcode=" + zipCode + ", area_code=" + areaCode + ", phone=" + phone + ", alternative_phone="
                + alternativePhone + ", product_service=" + productService + ", active=" + active + ", createdAt="
                + createdAt + ", updateAt=" + updateAt + ", genders_id=" + genderId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((alternativeAddress == null) ? 0 : alternativeAddress.hashCode());
        result = prime * result + ((alternativePhone == null) ? 0 : alternativePhone.hashCode());
        result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((clientType == null) ? 0 : clientType.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((cuitCuil == null) ? 0 : cuitCuil.hashCode());
        result = prime * result + ((IDnumber == null) ? 0 : IDnumber.hashCode());
        result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((genderId == null) ? 0 : genderId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((productService == null) ? 0 : productService.hashCode());
        result = prime * result + ((socialReason == null) ? 0 : socialReason.hashCode());
        result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) &&
                email.equals(client.email);
    }

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (alternativeAddress == null) {
			if (other.alternativeAddress != null)
				return false;
		} else if (!alternativeAddress.equals(other.alternativeAddress))
			return false;
		if (alternativePhone == null) {
			if (other.alternativePhone != null)
				return false;
		} else if (!alternativePhone.equals(other.alternativePhone))
			return false;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (clientType == null) {
			if (other.clientType != null)
				return false;
		} else if (!clientType.equals(other.clientType))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (cuitCuil == null) {
			if (other.cuitCuil != null)
				return false;
		} else if (!cuitCuil.equals(other.cuitCuil))
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (documentType == null) {
			if (other.documentType != null)
				return false;
		} else if (!documentType.equals(other.documentType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (genderId == null) {
			if (other.genderId != null)
				return false;
		} else if (!genderId.equals(other.genderId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (productService == null) {
			if (other.productService != null)
				return false;
		} else if (!productService.equals(other.productService))
			return false;
		if (socialReason == null) {
			if (other.socialReason != null)
				return false;
		} else if (!socialReason.equals(other.socialReason))
			return false;
		if (updateAt == null) {
			if (other.updateAt != null)
				return false;
		} else if (!updateAt.equals(other.updateAt))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}*/

    public Client(Long id, Long clientType, Long documentType,
                  @Size(max = 60, message = "el tamaño no puede ser mayor a 60 caracteres") String IDnumber,
                  @Size(max = 13, message = "el tamaño no puede ser mayor a 13 caracteres") String cuitCuil,
                  @NotEmpty(message = "No puede estar vacio") @Size(min = 2, max = 60, message = "el tamaño tiene que se mayor que 2 y menor a 60") String firstName,
                  @NotEmpty(message = "No puede estar vacio") @Size(min = 2, max = 60, message = "el tamaño tiene que se mayor que 2 y mayor a 60") String lastName,
                  @Size(max = 20, message = "el tamaño puede ser hasta 10 caracteres") String socialReason,
                  @NotEmpty(message = "No puede estar vacio") @Email(message = " Formato de Email incorrecto") @Size(max = 60, message = "el tamaño puede mayor a 60 caracteres") String email,
                  @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres") String address,
                  @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres") String alternativeAddress, Long city,
                  Long country, @Size(max = 10, message = "el tamaño puede mayor a 10 caracteres") String zipCode,
                  String areaCode, @Size(max = 13, message = "el tamaño puede mayor a 13 caracteres") String phone,
                  @Size(max = 13, message = "el tamaño puede mayor a 13 caracteres") String alternativePhone,
                  @Size(max = 255, message = "el tamaño puede mayor a 255 caracteres") String productService, Boolean active,
                  Date createdAt, Date updateAt, Gender genderId) {
        this.id = id;
        this.clientType = clientType;
        this.documentType = documentType;
        this.IDnumber = IDnumber;
        this.cuitCuil = cuitCuil;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialReason = socialReason;
        this.email = email;
        this.address = address;
        this.alternativeAddress = alternativeAddress;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.areaCode = areaCode;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.productService = productService;
        this.active = active;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.genderId = genderId;
    }

    public Client() {
    }


    public Client(ClientRequest clientRequest, Gender gender) {
        this.active = clientRequest.getActive();
        this.address = clientRequest.getAddress();
        this.alternativeAddress = clientRequest.getAlternative_adress();
        this.phone = clientRequest.getPhone();
        this.alternativePhone = clientRequest.getAlternative_phone();
        this.areaCode = clientRequest.getArea_code();
        this.email = clientRequest.getEmail();
        this.clientType = clientRequest.getClient_type();
        this.cuitCuil = clientRequest.getCuit_cuil();
        this.documentType = clientRequest.getDocument_type();
        this.IDnumber = clientRequest.getDocument();
        this.firstName = clientRequest.getFirstname();
        this.lastName = clientRequest.getLastname();
        this.genderId = gender;
        this.socialReason = clientRequest.getSocial_reason();
        this.zipCode = clientRequest.getZipcode();
        this.productService = clientRequest.getProduct_service();
        this.city = clientRequest.getCity();
        this.country = clientRequest.getCity();



    }


}
