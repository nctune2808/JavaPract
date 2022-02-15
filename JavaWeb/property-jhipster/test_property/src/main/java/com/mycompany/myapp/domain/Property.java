package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.PropertyStatus;
import com.mycompany.myapp.domain.enumeration.PropertyType;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Property.
 */
@Entity
@Table(name = "property")
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @NotNull
    @Column(name = "image_content_type", nullable = false)
    private String imageContentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PropertyStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PropertyType type;

    @NotNull
    @Column(name = "acreage", nullable = false)
    private Double acreage;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @JsonIgnoreProperties(value = { "customers" }, allowSetters = true)
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address address;

    @JsonIgnoreProperties(value = { "rooms" }, allowSetters = true)
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Accommodation accommodation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Property id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Property title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return this.image;
    }

    public Property image(byte[] image) {
        this.setImage(image);
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Property imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public PropertyStatus getStatus() {
        return this.status;
    }

    public Property status(PropertyStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public PropertyType getType() {
        return this.type;
    }

    public Property type(PropertyType type) {
        this.setType(type);
        return this;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Double getAcreage() {
        return this.acreage;
    }

    public Property acreage(Double acreage) {
        this.setAcreage(acreage);
        return this;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public Double getPrice() {
        return this.price;
    }

    public Property price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Property address(Address address) {
        this.setAddress(address);
        return this;
    }

    public Accommodation getAccommodation() {
        return this.accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Property accommodation(Accommodation accommodation) {
        this.setAccommodation(accommodation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Property)) {
            return false;
        }
        return id != null && id.equals(((Property) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Property{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", acreage=" + getAcreage() +
            ", price=" + getPrice() +
            "}";
    }
}
