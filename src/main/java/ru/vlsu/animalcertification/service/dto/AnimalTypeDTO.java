package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class AnimalTypeDTO implements Serializable {

    private Long id;

    private String typeName;

    private String typeNameEng;

    private String scientificNameENG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameEng() {
        return typeNameEng;
    }

    public void setTypeNameEng(String typeNameEng) {
        this.typeNameEng = typeNameEng;
    }

    public String getScientificNameENG() {
        return scientificNameENG;
    }

    public void setScientificNameENG(String scientificNameENG) {
        this.scientificNameENG = scientificNameENG;
    }

    public AnimalTypeDTO() {
    }

    @Override
    public String toString() {
        return "AnimalTypeDTO{" +
            "id=" + id +
            ", typeName='" + typeName + '\'' +
            ", typeNameEng='" + typeNameEng + '\'' +
            ", scientificNameENG='" + scientificNameENG + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalTypeDTO that = (AnimalTypeDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(typeName, that.typeName) &&
            Objects.equals(typeNameEng, that.typeNameEng) &&
            Objects.equals(scientificNameENG, that.scientificNameENG);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, typeNameEng, scientificNameENG);
    }
}
