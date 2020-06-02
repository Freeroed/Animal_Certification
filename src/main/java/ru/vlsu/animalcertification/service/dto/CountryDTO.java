package ru.vlsu.animalcertification.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vlsu.animalcertification.domain.Region;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CountryDTO implements Serializable {

    private Long id;

    private String name;

    private String nameEng;

    private String isoCode;

    //private Region region;
    //TODO DTO

}
