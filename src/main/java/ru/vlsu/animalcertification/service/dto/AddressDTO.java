package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddressDTO implements Serializable {

    private Long id;

    private String region;

    private String city;

    private String street;

    private String house;

    private Integer apartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public AddressDTO() {
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + id +
            ", region='" + region + '\'' +
            ", city='" + city + '\'' +
            ", street='" + street + '\'' +
            ", house='" + house + '\'' +
            ", apartment=" + apartment +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(region, that.region) &&
            Objects.equals(city, that.city) &&
            Objects.equals(street, that.street) &&
            Objects.equals(house, that.house) &&
            Objects.equals(apartment, that.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, city, street, house, apartment);
    }
}
