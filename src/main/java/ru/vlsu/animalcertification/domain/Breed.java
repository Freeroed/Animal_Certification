package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Breed.
 */
@Entity
@Table(name = "breed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Breed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "breed_name", nullable = false)
    private String breedName;

    @NotNull
    @Column(name = "breed_name_eng", nullable = false)
    private String breedNameEng;

    @ManyToOne
    @JsonIgnoreProperties(value = "breeds", allowSetters = true)
    private AnimalType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public Breed breedName(String breedName) {
        this.breedName = breedName;
        return this;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedNameEng() {
        return breedNameEng;
    }

    public Breed breedNameEng(String breedNameEng) {
        this.breedNameEng = breedNameEng;
        return this;
    }

    public void setBreedNameEng(String breedNameEng) {
        this.breedNameEng = breedNameEng;
    }

    public AnimalType getType() {
        return type;
    }

    public Breed type(AnimalType animalType) {
        this.type = animalType;
        return this;
    }

    public void setType(AnimalType animalType) {
        this.type = animalType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Breed)) {
            return false;
        }
        return id != null && id.equals(((Breed) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Breed{" +
            "id=" + getId() +
            ", breedName='" + getBreedName() + "'" +
            ", breedNameEng='" + getBreedNameEng() + "'" +
            "}";
    }
}
