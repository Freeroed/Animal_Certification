package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.enumeration.AnimalStatus;
import ru.vlsu.animalcertification.domain.enumeration.Gender;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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

    private BreedDTO breed;

    private UserDTO master;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getTnvedCode() {
        return tnvedCode;
    }

    public void setTnvedCode(String tnvedCode) {
        this.tnvedCode = tnvedCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorEng() {
        return colorEng;
    }

    public void setColorEng(String colorEng) {
        this.colorEng = colorEng;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public BreedDTO getBreed() {
        return breed;
    }

    public void setBreed(BreedDTO breed) {
        this.breed = breed;
    }

    public UserDTO getMaster() {
        return master;
    }

    public void setMaster(UserDTO master) {
        this.master = master;
    }

    public AnimalDTO() {
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", birthdate=" + birthdate +
            ", gender=" + gender +
            ", chip='" + chip + '\'' +
            ", birthPlace='" + birthPlace + '\'' +
            ", tnvedCode='" + tnvedCode + '\'' +
            ", color='" + color + '\'' +
            ", colorEng='" + colorEng + '\'' +
            ", status=" + status +
            ", breed=" + breed +
            ", master=" + master +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDTO animalDTO = (AnimalDTO) o;
        return Objects.equals(id, animalDTO.id) &&
            Objects.equals(nickname, animalDTO.nickname) &&
            Objects.equals(birthdate, animalDTO.birthdate) &&
            gender == animalDTO.gender &&
            Objects.equals(chip, animalDTO.chip) &&
            Objects.equals(birthPlace, animalDTO.birthPlace) &&
            Objects.equals(tnvedCode, animalDTO.tnvedCode) &&
            Objects.equals(color, animalDTO.color) &&
            Objects.equals(colorEng, animalDTO.colorEng) &&
            status == animalDTO.status &&
            Objects.equals(breed, animalDTO.breed) &&
            Objects.equals(master, animalDTO.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, birthdate, gender, chip, birthPlace, tnvedCode, color, colorEng, status, breed, master);
    }
}
