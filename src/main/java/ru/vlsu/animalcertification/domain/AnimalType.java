package ru.vlsu.animalcertification.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AnimalType.
 */
@Entity
@Table(name = "animal_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnimalType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type_name", nullable = false)
    private String typeName;

    @NotNull
    @Column(name = "type_name_eng", nullable = false)
    private String typeNameEng;

    @NotNull
    @Column(name = "scientific_name_eng", nullable = false)
    private String scientificNameENG;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public AnimalType typeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameEng() {
        return typeNameEng;
    }

    public AnimalType typeNameEng(String typeNameEng) {
        this.typeNameEng = typeNameEng;
        return this;
    }

    public void setTypeNameEng(String typeNameEng) {
        this.typeNameEng = typeNameEng;
    }

    public String getScientificNameENG() {
        return scientificNameENG;
    }

    public AnimalType scientificNameENG(String scientificNameENG) {
        this.scientificNameENG = scientificNameENG;
        return this;
    }

    public void setScientificNameENG(String scientificNameENG) {
        this.scientificNameENG = scientificNameENG;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimalType)) {
            return false;
        }
        return id != null && id.equals(((AnimalType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnimalType{" +
            "id=" + getId() +
            ", typeName='" + getTypeName() + "'" +
            ", typeNameEng='" + getTypeNameEng() + "'" +
            ", scientificNameENG='" + getScientificNameENG() + "'" +
            "}";
    }
}
