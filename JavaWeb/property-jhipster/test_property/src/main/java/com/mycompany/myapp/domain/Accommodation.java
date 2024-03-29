package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.AccommodationStatus;
import com.mycompany.myapp.domain.enumeration.AccommodationType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Accommodation.
 */
@Entity
@Table(name = "accommodation")
public class Accommodation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccommodationType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccommodationStatus status;

    @OneToMany(mappedBy = "accommodation")
    @JsonIgnoreProperties(value = { "accommodation" }, allowSetters = true)
    private Set<Room> rooms = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Accommodation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Accommodation title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AccommodationType getType() {
        return this.type;
    }

    public Accommodation type(AccommodationType type) {
        this.setType(type);
        return this;
    }

    public void setType(AccommodationType type) {
        this.type = type;
    }

    public AccommodationStatus getStatus() {
        return this.status;
    }

    public Accommodation status(AccommodationStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(AccommodationStatus status) {
        this.status = status;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        if (this.rooms != null) {
            this.rooms.forEach(i -> i.setAccommodation(null));
        }
        if (rooms != null) {
            rooms.forEach(i -> i.setAccommodation(this));
        }
        this.rooms = rooms;
    }

    public Accommodation rooms(Set<Room> rooms) {
        this.setRooms(rooms);
        return this;
    }

    public Accommodation addRoom(Room room) {
        this.rooms.add(room);
        room.setAccommodation(this);
        return this;
    }

    public Accommodation removeRoom(Room room) {
        this.rooms.remove(room);
        room.setAccommodation(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accommodation)) {
            return false;
        }
        return id != null && id.equals(((Accommodation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accommodation{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
