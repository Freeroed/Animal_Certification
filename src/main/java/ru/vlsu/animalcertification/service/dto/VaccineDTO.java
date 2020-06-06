package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.Animal;
import ru.vlsu.animalcertification.domain.enumeration.VaccineType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class VaccineDTO implements Serializable {

    private Long id;

    private String title;

    private Instant date;

    private String batchNumber;

    private String nameAndManufacturer;

    private Instant validUtil;

    private VaccineType type;

    private AnimalDTO animal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getNameAndManufacturer() {
        return nameAndManufacturer;
    }

    public void setNameAndManufacturer(String nameAndManufacturer) {
        this.nameAndManufacturer = nameAndManufacturer;
    }

    public Instant getValidUtil() {
        return validUtil;
    }

    public void setValidUtil(Instant validUtil) {
        this.validUtil = validUtil;
    }

    public VaccineType getType() {
        return type;
    }

    public void setType(VaccineType type) {
        this.type = type;
    }

    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }

    public VaccineDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineDTO that = (VaccineDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(date, that.date) &&
            Objects.equals(batchNumber, that.batchNumber) &&
            Objects.equals(nameAndManufacturer, that.nameAndManufacturer) &&
            Objects.equals(validUtil, that.validUtil) &&
            type == that.type &&
            Objects.equals(animal, that.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, batchNumber, nameAndManufacturer, validUtil, type, animal);
    }

    @Override
    public String toString() {
        return "VaccineDTO{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", date=" + date +
            ", batchNumber='" + batchNumber + '\'' +
            ", nameAndManufacturer='" + nameAndManufacturer + '\'' +
            ", validUtil=" + validUtil +
            ", type=" + type +
            ", animal=" + animal +
            '}';
    }
}
