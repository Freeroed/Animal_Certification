package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class BreedDTO implements Serializable {

    private Long id;

    private String breedName;

    private String breedNameEng;

    private AnimalTypeDTO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedNameEng() {
        return breedNameEng;
    }

    public void setBreedNameEng(String breedNameEng) {
        this.breedNameEng = breedNameEng;
    }

    public AnimalTypeDTO getType() {
        return type;
    }

    public void setType(AnimalTypeDTO type) {
        this.type = type;
    }

    public BreedDTO() {
    }

    @Override
    public String toString() {
        return "BreedDTO{" +
            "id=" + id +
            ", breedName='" + breedName + '\'' +
            ", breedNameEng='" + breedNameEng + '\'' +
            ", type=" + type +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreedDTO breedDTO = (BreedDTO) o;
        return Objects.equals(id, breedDTO.id) &&
            Objects.equals(breedName, breedDTO.breedName) &&
            Objects.equals(breedNameEng, breedDTO.breedNameEng) &&
            Objects.equals(type, breedDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breedName, breedNameEng, type);
    }
}
