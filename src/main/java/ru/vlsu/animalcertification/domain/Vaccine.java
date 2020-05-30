package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import ru.vlsu.animalcertification.domain.enumeration.VaccineType;

/**
 * A Vaccine.
 */
@Entity
@Table(name = "vaccine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vaccine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @Column(name = "batch_number", nullable = false)
    private String batchNumber;

    @NotNull
    @Column(name = "name_and_manufacturer", nullable = false)
    private String nameAndManufacturer;

    @NotNull
    @Column(name = "valid_util", nullable = false)
    private Instant validUtil;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VaccineType type;

    @ManyToOne
    @JsonIgnoreProperties(value = "vaccines", allowSetters = true)
    private Animal animal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Vaccine title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getDate() {
        return date;
    }

    public Vaccine date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Vaccine batchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
        return this;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getNameAndManufacturer() {
        return nameAndManufacturer;
    }

    public Vaccine nameAndManufacturer(String nameAndManufacturer) {
        this.nameAndManufacturer = nameAndManufacturer;
        return this;
    }

    public void setNameAndManufacturer(String nameAndManufacturer) {
        this.nameAndManufacturer = nameAndManufacturer;
    }

    public Instant getValidUtil() {
        return validUtil;
    }

    public Vaccine validUtil(Instant validUtil) {
        this.validUtil = validUtil;
        return this;
    }

    public void setValidUtil(Instant validUtil) {
        this.validUtil = validUtil;
    }

    public VaccineType getType() {
        return type;
    }

    public Vaccine type(VaccineType type) {
        this.type = type;
        return this;
    }

    public void setType(VaccineType type) {
        this.type = type;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Vaccine animal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vaccine)) {
            return false;
        }
        return id != null && id.equals(((Vaccine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vaccine{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", date='" + getDate() + "'" +
            ", batchNumber='" + getBatchNumber() + "'" +
            ", nameAndManufacturer='" + getNameAndManufacturer() + "'" +
            ", validUtil='" + getValidUtil() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
