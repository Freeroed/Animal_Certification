package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class DocumentTypeDTO implements Serializable {

    private Long id;

    private String name;

    private String nameEng;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    @Override
    public String toString() {
        return "DocumentTypeDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", nameEng='" + nameEng + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentTypeDTO that = (DocumentTypeDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(nameEng, that.nameEng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameEng);
    }
}
