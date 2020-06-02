package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class CountryDTO implements Serializable {

    private Long id;

    private String name;

    private String nameEng;

    private String isoCode;

    private RegionDTO region;

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

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public CountryDTO() {
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", nameEng='" + nameEng + '\'' +
            ", isoCode='" + isoCode + '\'' +
            ", region=" + region +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDTO that = (CountryDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(nameEng, that.nameEng) &&
            Objects.equals(isoCode, that.isoCode) &&
            Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameEng, isoCode, region);
    }
}
