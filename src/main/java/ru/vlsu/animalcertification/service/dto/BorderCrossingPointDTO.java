package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class BorderCrossingPointDTO implements Serializable {

    private Long id;

    private String location;

    private String adjacentPoint;

    private String classification;

    private String schedule;

    private String scheduleOfOfficals;

    private String coordinates;

    private CountryDTO firstCountry;

    private CountryDTO secondCountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdjacentPoint() {
        return adjacentPoint;
    }

    public void setAdjacentPoint(String adjacentPoint) {
        this.adjacentPoint = adjacentPoint;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getScheduleOfOfficals() {
        return scheduleOfOfficals;
    }

    public void setScheduleOfOfficals(String scheduleOfOfficals) {
        this.scheduleOfOfficals = scheduleOfOfficals;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public CountryDTO getFirstCountry() {
        return firstCountry;
    }

    public void setFirstCountry(CountryDTO firstCountry) {
        this.firstCountry = firstCountry;
    }

    public CountryDTO getSecondCountry() {
        return secondCountry;
    }

    public void setSecondCountry(CountryDTO secondCountry) {
        this.secondCountry = secondCountry;
    }

    public BorderCrossingPointDTO() {
    }

    @Override
    public String toString() {
        return "BorderCrossingPointDTO{" +
            "id=" + id +
            ", location='" + location + '\'' +
            ", adjacentPoint='" + adjacentPoint + '\'' +
            ", classification='" + classification + '\'' +
            ", schedule='" + schedule + '\'' +
            ", scheduleOfOfficals='" + scheduleOfOfficals + '\'' +
            ", coordinates='" + coordinates + '\'' +
            ", firstCountry=" + firstCountry +
            ", secondCountry=" + secondCountry +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorderCrossingPointDTO that = (BorderCrossingPointDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(location, that.location) &&
            Objects.equals(adjacentPoint, that.adjacentPoint) &&
            Objects.equals(classification, that.classification) &&
            Objects.equals(schedule, that.schedule) &&
            Objects.equals(scheduleOfOfficals, that.scheduleOfOfficals) &&
            Objects.equals(coordinates, that.coordinates) &&
            Objects.equals(firstCountry, that.firstCountry) &&
            Objects.equals(secondCountry, that.secondCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, adjacentPoint, classification, schedule, scheduleOfOfficals, coordinates, firstCountry, secondCountry);
    }
}
