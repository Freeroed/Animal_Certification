package ru.vlsu.animalcertification.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class PersonalDataDTO implements Serializable {

    private Long id;

    private String name;

    private String surname;

    private String nameEng;

    private String surnameEng;

    private String patronymic;

    private String phone;

    private String inn;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getSurnameEng() {
        return surnameEng;
    }

    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public PersonalDataDTO() {
    }

    @Override
    public String toString() {
        return "PersonDataDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", nameEng='" + nameEng + '\'' +
            ", surnameEng='" + surnameEng + '\'' +
            ", patronymic='" + patronymic + '\'' +
            ", phone='" + phone + '\'' +
            ", inn='" + inn + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDataDTO that = (PersonalDataDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(nameEng, that.nameEng) &&
            Objects.equals(surnameEng, that.surnameEng) &&
            Objects.equals(patronymic, that.patronymic) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, nameEng, surnameEng, patronymic, phone, inn);
    }
}
