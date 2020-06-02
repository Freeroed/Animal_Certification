package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import ru.vlsu.animalcertification.domain.enumeration.Gender;

import ru.vlsu.animalcertification.domain.enumeration.AnimalStatus;

/**
 * A Animal.
 */
@Entity
@Table(name = "animal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @NotNull
    @Column(name = "birthdate", nullable = false)
    private Instant birthdate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "chip")
    private String chip;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "tnved_code")
    private String tnvedCode;

    @NotNull
    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "color_eng")
    private String colorEng;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AnimalStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "animals", allowSetters = true)
    private Breed breed;

    @ManyToOne
    @JsonIgnoreProperties(value = "animals", allowSetters = true)
    private User master;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public Animal nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public Animal birthdate(Instant birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public Animal gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChip() {
        return chip;
    }

    public Animal chip(String chip) {
        this.chip = chip;
        return this;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Animal birthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getTnvedCode() {
        return tnvedCode;
    }

    public Animal tnvedCode(String tnvedCode) {
        this.tnvedCode = tnvedCode;
        return this;
    }

    public void setTnvedCode(String tnvedCode) {
        this.tnvedCode = tnvedCode;
    }

    public String getColor() {
        return color;
    }

    public Animal color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorEng() {
        return colorEng;
    }

    public Animal colorEng(String colorEng) {
        this.colorEng = colorEng;
        return this;
    }

    public void setColorEng(String colorEng) {
        this.colorEng = colorEng;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public Animal status(AnimalStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public Breed getBreed() {
        return breed;
    }

    public Animal breed(Breed breed) {
        this.breed = breed;
        return this;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public User getMaster() {
        return master;
    }

    public Animal master(User user) {
        this.master = user;
        return this;
    }

    public void setMaster(User user) {
        this.master = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Animal)) {
            return false;
        }
        return id != null && id.equals(((Animal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Animal{" +
            "id=" + getId() +
            ", nickname='" + getNickname() + "'" +
            ", birthdate='" + getBirthdate() + "'" +
            ", gender='" + getGender() + "'" +
            ", chip='" + getChip() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", tnvedCode='" + getTnvedCode() + "'" +
            ", color='" + getColor() + "'" +
            ", colorEng='" + getColorEng() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
