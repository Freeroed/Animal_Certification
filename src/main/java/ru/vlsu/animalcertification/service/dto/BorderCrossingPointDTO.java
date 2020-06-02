package ru.vlsu.animalcertification.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vlsu.animalcertification.domain.Country;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BorderCrossingPointDTO implements Serializable {

    private Long id;

    private String location;

    private String adjacentPoint;

    private String classification;

    private String schedule;

    private String scheduleOfOfficals;

    private String coordinates;

   // private Country firstCountry;

    //private Country secondCountry;
    //TODO DTO
}
