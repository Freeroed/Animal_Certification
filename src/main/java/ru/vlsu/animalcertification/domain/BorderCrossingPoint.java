package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BorderCrossingPoint.
 */
@Entity
@Table(name = "border_crossing_point")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BorderCrossingPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @NotNull
    @Column(name = "adjacent_point", nullable = false)
    private String adjacentPoint;

    @NotNull
    @Column(name = "classification", nullable = false)
    private String classification;

    @NotNull
    @Column(name = "schedule", nullable = false)
    private String schedule;

    @NotNull
    @Column(name = "schedule_of_officals", nullable = false)
    private String scheduleOfOfficals;

    @ManyToOne
    @JsonIgnoreProperties(value = "borderCrossingPoints", allowSetters = true)
    private Country firstCountry;

    @ManyToOne
    @JsonIgnoreProperties(value = "borderCrossingPoints", allowSetters = true)
    private Country secondCountry;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public BorderCrossingPoint location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdjacentPoint() {
        return adjacentPoint;
    }

    public BorderCrossingPoint adjacentPoint(String adjacentPoint) {
        this.adjacentPoint = adjacentPoint;
        return this;
    }

    public void setAdjacentPoint(String adjacentPoint) {
        this.adjacentPoint = adjacentPoint;
    }

    public String getClassification() {
        return classification;
    }

    public BorderCrossingPoint classification(String classification) {
        this.classification = classification;
        return this;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSchedule() {
        return schedule;
    }

    public BorderCrossingPoint schedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getScheduleOfOfficals() {
        return scheduleOfOfficals;
    }

    public BorderCrossingPoint scheduleOfOfficals(String scheduleOfOfficals) {
        this.scheduleOfOfficals = scheduleOfOfficals;
        return this;
    }

    public void setScheduleOfOfficals(String scheduleOfOfficals) {
        this.scheduleOfOfficals = scheduleOfOfficals;
    }

    public Country getFirstCountry() {
        return firstCountry;
    }

    public BorderCrossingPoint firstCountry(Country country) {
        this.firstCountry = country;
        return this;
    }

    public void setFirstCountry(Country country) {
        this.firstCountry = country;
    }

    public Country getSecondCountry() {
        return secondCountry;
    }

    public BorderCrossingPoint secondCountry(Country country) {
        this.secondCountry = country;
        return this;
    }

    public void setSecondCountry(Country country) {
        this.secondCountry = country;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BorderCrossingPoint)) {
            return false;
        }
        return id != null && id.equals(((BorderCrossingPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BorderCrossingPoint{" +
            "id=" + getId() +
            ", location='" + getLocation() + "'" +
            ", adjacentPoint='" + getAdjacentPoint() + "'" +
            ", classification='" + getClassification() + "'" +
            ", schedule='" + getSchedule() + "'" +
            ", scheduleOfOfficals='" + getScheduleOfOfficals() + "'" +
            "}";
    }
}
