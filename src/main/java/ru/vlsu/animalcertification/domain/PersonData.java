package ru.vlsu.animalcertification.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PersonData.
 */
@Entity
@Table(name = "personal_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "surname_eng")
    private String surnameEng;

    @Column(name = "patronymic")
    private String patronymic;


    @Column(name = "phone")
    private String phone;

    @Column(name = "inn")
    private String inn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PersonData name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public PersonData surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameEng() {
        return nameEng;
    }

    public PersonData nameEng(String nameEng) {
        this.nameEng = nameEng;
        return this;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getSurnameEng() {
        return surnameEng;
    }

    public PersonData surnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
        return this;
    }

    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public PersonData patronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public PersonData phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getInn() {
        return inn;
    }

    public PersonData inn(String inn) {
        this.inn = inn;
        return this;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonData)) {
            return false;
        }
        return id != null && id.equals(((PersonData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonData{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", nameEng='" + getNameEng() + "'" +
            ", surnameEng='" + getSurnameEng() + "'" +
            ", patronymic='" + getPatronymic() + "'" +
            ", phone='" + getPhone() + "'" +
            ", inn='" + getInn() + "'" +
            "}";
    }
}
