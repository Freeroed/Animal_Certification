package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.enumeration.AnimalStatus;
import ru.vlsu.animalcertification.domain.enumeration.Gender;

import java.io.Serializable;
import java.time.Instant;

public class AnimalDTO implements Serializable {

    private Long id;

    private String nickname;

    private Instant birthdate;

    private Gender gender;

    private String chip;

    private String birthPlace;

    private String tnvedCode;

    private String color;

    private String colorEng;

    private AnimalStatus status;

    //private Breed breed;

    //private User master;

    //TODO DTO
}
