package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ru.vlsu.animalcertification.domain.enumeration.LaboratoryTestResult;

/**
 * A LaboratoryResearch.
 */
@Entity
@Table(name = "laboratory_research")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LaboratoryResearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "laboratoty", nullable = false)
    private String laboratoty;

    @NotNull
    @Column(name = "indicator", nullable = false)
    private String indicator;

    @NotNull
    @Column(name = "result_receipt_date", nullable = false)
    private String resultReceiptDate;

    @NotNull
    @Column(name = "resurch_method", nullable = false)
    private String resurchMethod;

    @NotNull
    @Column(name = "examination_number", nullable = false)
    private String examinationNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private LaboratoryTestResult result;

    @ManyToOne
    @JsonIgnoreProperties(value = "laboratoryResearches", allowSetters = true)
    private Animal animal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoty() {
        return laboratoty;
    }

    public LaboratoryResearch laboratoty(String laboratoty) {
        this.laboratoty = laboratoty;
        return this;
    }

    public void setLaboratoty(String laboratoty) {
        this.laboratoty = laboratoty;
    }

    public String getIndicator() {
        return indicator;
    }

    public LaboratoryResearch indicator(String indicator) {
        this.indicator = indicator;
        return this;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getResultReceiptDate() {
        return resultReceiptDate;
    }

    public LaboratoryResearch resultReceiptDate(String resultReceiptDate) {
        this.resultReceiptDate = resultReceiptDate;
        return this;
    }

    public void setResultReceiptDate(String resultReceiptDate) {
        this.resultReceiptDate = resultReceiptDate;
    }

    public String getResurchMethod() {
        return resurchMethod;
    }

    public LaboratoryResearch resurchMethod(String resurchMethod) {
        this.resurchMethod = resurchMethod;
        return this;
    }

    public void setResurchMethod(String resurchMethod) {
        this.resurchMethod = resurchMethod;
    }

    public String getExaminationNumber() {
        return examinationNumber;
    }

    public LaboratoryResearch examinationNumber(String examinationNumber) {
        this.examinationNumber = examinationNumber;
        return this;
    }

    public void setExaminationNumber(String examinationNumber) {
        this.examinationNumber = examinationNumber;
    }

    public LaboratoryTestResult getResult() {
        return result;
    }

    public LaboratoryResearch result(LaboratoryTestResult result) {
        this.result = result;
        return this;
    }

    public void setResult(LaboratoryTestResult result) {
        this.result = result;
    }

    public Animal getAnimal() {
        return animal;
    }

    public LaboratoryResearch animal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LaboratoryResearch)) {
            return false;
        }
        return id != null && id.equals(((LaboratoryResearch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LaboratoryResearch{" +
            "id=" + getId() +
            ", laboratoty='" + getLaboratoty() + "'" +
            ", indicator='" + getIndicator() + "'" +
            ", resultReceiptDate='" + getResultReceiptDate() + "'" +
            ", resurchMethod='" + getResurchMethod() + "'" +
            ", examinationNumber='" + getExaminationNumber() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
